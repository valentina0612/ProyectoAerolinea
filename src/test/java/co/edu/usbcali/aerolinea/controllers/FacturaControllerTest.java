package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.services.FacturaService;
import co.edu.usbcali.aerolinea.utility.FacturaUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest
public class FacturaControllerTest {

    @Autowired
    private FacturaController facturaController;

    @MockBean
    private FacturaService facturaService;

    @Test
    public void guardarFacturaOk() throws Exception {
        when(facturaService.guardarFactura(any())).thenReturn(FacturaUtilityTest.FACTURADTO_UNO);

        ResponseEntity<FacturaDTO> response = facturaController.guardarFactura(FacturaUtilityTest.FACTURADTO_UNO);

        verify(facturaService).guardarFactura(eq(FacturaUtilityTest.FACTURADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarFacturaNotOk() {
        try {
            facturaController.guardarFactura(FacturaUtilityTest.FACTURADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(FacturaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerFacturasOk() {
        when(facturaService.obtenerFacturas()).thenReturn(FacturaUtilityTest.FACTURASDTO);

        ResponseEntity<List<FacturaDTO>> response = facturaController.obtenerFactura();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(FacturaUtilityTest.FACTURAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerFacturasNotOk() {
        when(facturaService.obtenerFacturas()).thenReturn(FacturaUtilityTest.FACTURASDTO_VACIO);

        ResponseEntity<List<FacturaDTO>> response = facturaController.obtenerFactura();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(FacturaUtilityTest.FACTURAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerFacturasActivasOk() {
        when(facturaService.obtenerFacturasActivas()).thenReturn(FacturaUtilityTest.FACTURASDTO);

        ResponseEntity<List<FacturaDTO>> response = facturaController.obtenerFacturasActivas();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(FacturaUtilityTest.FACTURAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerFacturasActivasNotOk() {
        when(facturaService.obtenerFacturasActivas()).thenReturn(FacturaUtilityTest.FACTURASDTO_VACIO);

        ResponseEntity<List<FacturaDTO>> response = facturaController.obtenerFacturasActivas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(FacturaUtilityTest.FACTURAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerFacturaOk() throws Exception {
        when(facturaService.buscarPorId(any())).thenReturn(FacturaUtilityTest.FACTURADTO_UNO);

        ResponseEntity<FacturaDTO> response = facturaController.buscarPorId(FacturaUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerFacturaNotOk() {
        try {
            facturaController.buscarPorId(FacturaUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(FacturaUtilityTest.ID_NOT_FOUND_MESSAGE, FacturaUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarFacturaOk() throws Exception {
        when(facturaService.modificarFactura(any())).thenReturn(FacturaUtilityTest.FACTURADTO_UNO);

        ResponseEntity<FacturaDTO> response = facturaController.modificarFactura(FacturaUtilityTest.FACTURADTO_UNO);

        verify(facturaService).modificarFactura(eq(FacturaUtilityTest.FACTURADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarFacturaNotOk() {
        try {
            facturaController.modificarFactura(FacturaUtilityTest.FACTURADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(FacturaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarFacturaOk() throws Exception {
        when(facturaService.eliminarFactura(any())).thenReturn(FacturaUtilityTest.FACTURADTO_UNO);

        ResponseEntity<FacturaDTO> response = facturaController.eliminarFactura(FacturaUtilityTest.ID_UNO);

        verify(facturaService).eliminarFactura(eq(FacturaUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarFacturaNotOk() throws Exception {
        try {
            facturaController.eliminarFactura(FacturaUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(FacturaUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
