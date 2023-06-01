package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.services.AsientoService;
import co.edu.usbcali.aerolinea.utility.AsientoUtilityTest;
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
public class AsientoControllerTest {
    @Autowired
    private AsientoController asientoController;

    @MockBean
    private AsientoService asientoService;

    @Test
    public void guardarAsientoOk() throws Exception {
        when(asientoService.guardarAsiento(any())).thenReturn(AsientoUtilityTest.ASIENTODTO_UNO);

        ResponseEntity<AsientoDTO> response = asientoController.guardarAsiento(AsientoUtilityTest.ASIENTODTO_UNO);

        verify(asientoService).guardarAsiento(eq(AsientoUtilityTest.ASIENTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarAsientoNotOk() {
        try {
            asientoController.guardarAsiento(AsientoUtilityTest.ASIENTODTO_UBICACION_NULL);
        } catch (Exception e) {
            assertEquals(AsientoUtilityTest.UBICACION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerAsientosOk() {
        when(asientoService.obtenerAsientos()).thenReturn(AsientoUtilityTest.ASIENTOSDTO);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AsientoUtilityTest.ASIENTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAsientosNotOk() {
        when(asientoService.obtenerAsientos()).thenReturn(AsientoUtilityTest.ASIENTOSDTO_VACIO);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AsientoUtilityTest.ASIENTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAsientosActivosOk() {
        when(asientoService.obtenerAsientosActivos()).thenReturn(AsientoUtilityTest.ASIENTOSDTO);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientosActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(AsientoUtilityTest.ASIENTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAsientosActivosNotOk() {
        when(asientoService.obtenerAsientosActivos()).thenReturn(AsientoUtilityTest.ASIENTOSDTO_VACIO);

        ResponseEntity<List<AsientoDTO>> response = asientoController.obtenerAsientosActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AsientoUtilityTest.ASIENTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void buscarPorIdOk() throws Exception {
        when(asientoService.buscarPorId(any())).thenReturn(AsientoUtilityTest.ASIENTODTO_UNO);

        ResponseEntity<AsientoDTO> response = asientoController.buscarPorId(AsientoUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void buscarPorIdNotOk() {
        try {
            asientoController.buscarPorId(AsientoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(AsientoUtilityTest.ID_NOT_FOUND_MESSAGE, AsientoUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarAsientoOk() throws Exception {
        when(asientoService.modificarAsiento(any())).thenReturn(AsientoUtilityTest.ASIENTODTO_UNO);

        ResponseEntity<AsientoDTO> response = asientoController.modificarAsiento(AsientoUtilityTest.ASIENTODTO_UNO);

        verify(asientoService).modificarAsiento(eq(AsientoUtilityTest.ASIENTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarAsientoNotOk() {
        try {
            asientoController.modificarAsiento(AsientoUtilityTest.ASIENTODTO_UBICACION_NULL);
        } catch (Exception e) {
            assertEquals(AsientoUtilityTest.UBICACION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarAsientoOk() throws Exception {
        when(asientoService.asientoOcupado(any())).thenReturn(AsientoUtilityTest.ASIENTODTO_UNO);

        ResponseEntity<AsientoDTO> response = asientoController.eliminarAsiento(AsientoUtilityTest.ID_UNO);

        verify(asientoService).asientoOcupado(eq(AsientoUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarAsientoNotOk() throws Exception {
        try {
            asientoController.eliminarAsiento(AsientoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(AsientoUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
