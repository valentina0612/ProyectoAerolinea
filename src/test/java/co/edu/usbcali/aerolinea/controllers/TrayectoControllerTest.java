package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.services.TrayectoService;
import co.edu.usbcali.aerolinea.utility.TrayectoUtilityTest;
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
public class TrayectoControllerTest {
    @Autowired
    private TrayectoController trayectoController;

    @MockBean
    private TrayectoService trayectoService;

    @Test
    public void guardarTrayectoOk() throws Exception {
        when(trayectoService.guardarTrayecto(any())).thenReturn(TrayectoUtilityTest.TRAYECTODTO_UNO);

        ResponseEntity<TrayectoDTO> response = trayectoController.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO);

        verify(trayectoService).guardarTrayecto(eq(TrayectoUtilityTest.TRAYECTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarTrayectoNotOk() {
        try {
            trayectoController.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO_HORASALIDA_NULL);
        } catch (Exception e) {
            assertEquals(TrayectoUtilityTest.HORASALIDA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerTrayectosOk() {
        when(trayectoService.obtenerTrayectos()).thenReturn(TrayectoUtilityTest.TRAYECTOSDTO);

        ResponseEntity<List<TrayectoDTO>> response = trayectoController.obtenerTrayecto();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTrayectosNotOk() {
        when(trayectoService.obtenerTrayectos()).thenReturn(TrayectoUtilityTest.TRAYECTOSDTO_VACIO);

        ResponseEntity<List<TrayectoDTO>> response = trayectoController.obtenerTrayecto();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTrayectosActivosOk() {
        when(trayectoService.obtenerTrayectosActivos()).thenReturn(TrayectoUtilityTest.TRAYECTOSDTO);

        ResponseEntity<List<TrayectoDTO>> response = trayectoController.obtenerTrayectosActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTrayectosActivosNotOk() {
        when(trayectoService.obtenerTrayectosActivos()).thenReturn(TrayectoUtilityTest.TRAYECTOSDTO_VACIO);

        ResponseEntity<List<TrayectoDTO>> response = trayectoController.obtenerTrayectosActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerTrayectoOk() throws Exception {
        when(trayectoService.buscarPorId(any())).thenReturn(TrayectoUtilityTest.TRAYECTODTO_UNO);

        ResponseEntity<TrayectoDTO> response = trayectoController.buscarPorId(TrayectoUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerTrayectoNotOk() {
        try {
            trayectoController.buscarPorId(TrayectoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(TrayectoUtilityTest.ID_NOT_FOUND_MESSAGE, TrayectoUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarTrayectoOk() throws Exception {
        when(trayectoService.modificarTrayecto(any())).thenReturn(TrayectoUtilityTest.TRAYECTODTO_UNO);

        ResponseEntity<TrayectoDTO> response = trayectoController.modificarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO);

        verify(trayectoService).modificarTrayecto(eq(TrayectoUtilityTest.TRAYECTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarTrayectoNotOk() {
        try {
            trayectoController.modificarTrayecto(TrayectoUtilityTest.TRAYECTODTO_HORASALIDA_NULL);
        } catch (Exception e) {
            assertEquals(TrayectoUtilityTest.HORASALIDA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarTrayectoOk() throws Exception {
        when(trayectoService.eliminarTrayecto(any())).thenReturn(TrayectoUtilityTest.TRAYECTODTO_UNO);

        ResponseEntity<TrayectoDTO> response = trayectoController.eliminarTrayecto(TrayectoUtilityTest.ID_UNO);

        verify(trayectoService).eliminarTrayecto(eq(TrayectoUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarTrayectoNotOk() throws Exception {
        try {
            trayectoController.eliminarTrayecto(TrayectoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(TrayectoUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
