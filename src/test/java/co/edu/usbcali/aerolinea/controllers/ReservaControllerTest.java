package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.services.ReservaService;
import co.edu.usbcali.aerolinea.utility.ReservaUtilityTest;
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
public class ReservaControllerTest {
    @Autowired
    private ReservaController reservaController;

    @MockBean
    private ReservaService reservaService;

    @Test
    public void guardarReservaOk() throws Exception {
        when(reservaService.guardarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.guardarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        verify(reservaService).guardarReserva(eq(ReservaUtilityTest.RESERVADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarReservaNotOk() {
        try {
            reservaController.guardarReserva(ReservaUtilityTest.RESERVADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerReservasOk() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilityTest.RESERVASDTO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReserva();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasNotOk() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilityTest.RESERVASDTO_VACIO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReserva();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasActivasOk() {
        when(reservaService.obtenerReservasActivas()).thenReturn(ReservaUtilityTest.RESERVASDTO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservaActivas();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasActivasNotOk() {
        when(reservaService.obtenerReservasActivas()).thenReturn(ReservaUtilityTest.RESERVASDTO_VACIO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservaActivas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservaOk() throws Exception {
        when(reservaService.buscarPorId(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.buscarPorId(ReservaUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerReservaNotOk() {
        try {
            reservaController.buscarPorId(ReservaUtilityTest.ID_UNO);
        } catch (Exception e) {
            assertEquals(String.format(ReservaUtilityTest.ID_NOT_FOUND_MESSAGE, ReservaUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarReservaOk() throws Exception {
        when(reservaService.modificarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.modificarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        verify(reservaService).modificarReserva(eq(ReservaUtilityTest.RESERVADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarReservaNotOk() {
        try {
            reservaController.modificarReserva(ReservaUtilityTest.RESERVADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarReservaOk() throws Exception {
        when(reservaService.eliminarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.eliminarReserva(ReservaUtilityTest.ID_UNO);

        verify(reservaService).eliminarReserva(eq(ReservaUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarReservaNotOk() throws Exception {
        try {
            reservaController.eliminarReserva(ReservaUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
