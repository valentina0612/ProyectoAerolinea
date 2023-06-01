package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AeropuertoControllerTest {
    @Autowired
    private AeropuertoController aeropuertoController;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @MockBean
    private AeropuertoService aeropuertoService;

    @Test
    void guardarAeropuerto_Ok_()throws Exception{
        when(aeropuertoService.guardarAeropuerto(any(AeropuertoDTO.class))).thenReturn(AeropuertoUtilityTest.AEROPUERTODTO_UNO);
        ResponseEntity<AeropuertoDTO> response = aeropuertoController.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO_UNO);
        verify(aeropuertoService).guardarAeropuerto(eq(AeropuertoUtilityTest.AEROPUERTODTO_UNO));
        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
    }

    @Test
    public void guardarAeropuertoNot_Ok_() {
        try {
            aeropuertoController.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO_NOMBRE_NULL);
        } catch (Exception e) {
            assertEquals(AeropuertoUtilityTest.NOMBRE_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void aeropuertosOk() {
        when(aeropuertoService.obtenerAeropuertos()).thenReturn(AeropuertoUtilityTest.AEROPUERTOSDTO);

        ResponseEntity<List<AeropuertoDTO>> response = aeropuertoController.aeropuertos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_SIZE, response.getBody().size());
    }

    @Test
    public void aeropuertosNotOk() {
        when(aeropuertoService.obtenerAeropuertos()).thenReturn(AeropuertoUtilityTest.AEROPUERTOSDTO_VACIO);

        ResponseEntity<List<AeropuertoDTO>> response = aeropuertoController.aeropuertos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAeropuertosActivosOk() {
        when(aeropuertoService.obtenerAeropuertosActivos()).thenReturn(AeropuertoUtilityTest.AEROPUERTOSDTO);

        ResponseEntity<List<AeropuertoDTO>> response = aeropuertoController.obtenerAeropuertosActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAeropuertosActivosNotOk() {
        when(aeropuertoService.obtenerAeropuertosActivos()).thenReturn(AeropuertoUtilityTest.AEROPUERTOSDTO_VACIO);

        ResponseEntity<List<AeropuertoDTO>> response = aeropuertoController.obtenerAeropuertosActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void buscarPorIdOk() throws Exception {
        when(aeropuertoService.buscarPorId(any())).thenReturn(AeropuertoUtilityTest.AEROPUERTODTO_UNO);

        ResponseEntity<AeropuertoDTO> response = aeropuertoController.buscarPorId(AeropuertoUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void buscarPorIdNotOk() {
        try {
            aeropuertoController.buscarPorId(AeropuertoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(AeropuertoUtilityTest.ID_NOT_FOUND_MESSAGE, AeropuertoUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarAeropuertoOk() throws Exception {
        when(aeropuertoService.modificarAeropuerto(any())).thenReturn(AeropuertoUtilityTest.AEROPUERTODTO_UNO);

        ResponseEntity<AeropuertoDTO> response = aeropuertoController.modificarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO_UNO);

        verify(aeropuertoService).modificarAeropuerto(eq(AeropuertoUtilityTest.AEROPUERTODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarAeropuertoNotOk() {
        try {
            aeropuertoController.modificarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO_NOMBRE_NULL);
        } catch (Exception e) {
            assertEquals(AeropuertoUtilityTest.NOMBRE_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarAeropuertoOk() throws Exception {
        when(aeropuertoService.eliminarAeropuerto(any())).thenReturn(AeropuertoUtilityTest.AEROPUERTODTO_UNO);

        ResponseEntity<AeropuertoDTO> response = aeropuertoController.eliminarAeropuerto(AeropuertoUtilityTest.ID_UNO);

        verify(aeropuertoService).eliminarAeropuerto(eq(AeropuertoUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarAeropuertoNotOk() throws Exception {
        try {
            aeropuertoController.eliminarAeropuerto(AeropuertoUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(AeropuertoUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
}}

