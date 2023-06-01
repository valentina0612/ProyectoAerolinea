package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import co.edu.usbcali.aerolinea.utility.TipoAsientoUtilityTest;
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
public class TipoAsientoControllerTest {
    @Autowired
    private TipoAsientoController tipoAsientoController;

    @MockBean
    private TipoAsientoService tipoAsientoService;

    @Test
    public void guardarTipoAsientoOk() throws Exception {
        when(tipoAsientoService.guardarTipoAsiento(any())).thenReturn(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        verify(tipoAsientoService).guardarTipoAsiento(eq(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarTipoAsientoNotOk() {
        try {
            tipoAsientoController.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_DESCRIPCION_NULL);
        } catch (Exception e) {
            assertEquals(TipoAsientoUtilityTest.DESCRIPCION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerTipoAsientosOk() {
        when(tipoAsientoService.obtenerTipoAsiento()).thenReturn(TipoAsientoUtilityTest.TIPOASIENTOSDTO);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTipoAsientosNotOk() {
        when(tipoAsientoService.obtenerTipoAsiento()).thenReturn(TipoAsientoUtilityTest.TIPOASIENTOSDTO_VACIO);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTipoAsientosActivosOk() {
        when(tipoAsientoService.obtenerTipoAsientosActivos()).thenReturn(TipoAsientoUtilityTest.TIPOASIENTOSDTO);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientosActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTipoAsientosActivosNotOk() {
        when(tipoAsientoService.obtenerTipoAsientosActivos()).thenReturn(TipoAsientoUtilityTest.TIPOASIENTOSDTO_VACIO);

        ResponseEntity<List<TipoAsientoDTO>> response = tipoAsientoController.obtenerTipoAsientosActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTipoAsientoOk() throws Exception {
        when(tipoAsientoService.buscarPorId(any())).thenReturn(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.buscarPorId(TipoAsientoUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerTipoAsientoNotOk() {
        try {
            tipoAsientoController.buscarPorId(TipoAsientoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(TipoAsientoUtilityTest.ID_NOT_FOUND_MESSAGE, TipoAsientoUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarTipoAsientoOk() throws Exception {
        when(tipoAsientoService.modificarTipoAsiento(any())).thenReturn(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.modificarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        verify(tipoAsientoService).modificarTipoAsiento(eq(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarTipoAsientoNotOk() {
        try {
            tipoAsientoController.modificarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_DESCRIPCION_NULL);
        } catch (Exception e) {
            assertEquals(TipoAsientoUtilityTest.DESCRIPCION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarTipoAsientoOk() throws Exception {
        when(tipoAsientoService.eliminarTipoAsiento(any())).thenReturn(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        ResponseEntity<TipoAsientoDTO> response = tipoAsientoController.eliminarTipoAsiento(TipoAsientoUtilityTest.ID_UNO);

        verify(tipoAsientoService).eliminarTipoAsiento(eq(TipoAsientoUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarTipoAsientoNotOk() throws Exception {
        try {
            tipoAsientoController.eliminarTipoAsiento(TipoAsientoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(TipoAsientoUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
