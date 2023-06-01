package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.services.VuelosService;
import co.edu.usbcali.aerolinea.utility.VuelosUtilityTest;
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
public class VueloControllerTest {
    @Autowired
    private VueloController vueloController;

    @MockBean
    private VuelosService vueloService;

    @Test
    public void guardarVueloOk() throws Exception {
        when(vueloService.guardarVuelo(any())).thenReturn(VuelosUtilityTest.VUELODTO_UNO);

        ResponseEntity<VueloDTO> response = vueloController.guardarVuelo(VuelosUtilityTest.VUELODTO_UNO);

        verify(vueloService).guardarVuelo(eq(VuelosUtilityTest.VUELODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarVueloNotOk() {
        try {
            vueloController.guardarVuelo(VuelosUtilityTest.VUELODTO_HORASALIDA_NULL);
        } catch (Exception e) {
            assertEquals(VuelosUtilityTest.HORASALIDA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerVuelosOk() {
        when(vueloService.obtenerVuelos()).thenReturn(VuelosUtilityTest.VUELOSDTO);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(VuelosUtilityTest.VUELOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerVuelosNotOk() {
        when(vueloService.obtenerVuelos()).thenReturn(VuelosUtilityTest.VUELOSDTO_VACIO);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(VuelosUtilityTest.VUELOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerVuelosActivosOk() {
        when(vueloService.obtenerVuelosActivos()).thenReturn(VuelosUtilityTest.VUELOSDTO);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelosActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(VuelosUtilityTest.VUELOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerVuelosActivosNotOk() {
        when(vueloService.obtenerVuelosActivos()).thenReturn(VuelosUtilityTest.VUELOSDTO_VACIO);

        ResponseEntity<List<VueloDTO>> response = vueloController.obtenerVuelosActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(VuelosUtilityTest.VUELOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerVueloOk() throws Exception {
        when(vueloService.buscarPorId(any())).thenReturn(VuelosUtilityTest.VUELODTO_UNO);

        ResponseEntity<VueloDTO> response = vueloController.buscarPorId(VuelosUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerVueloNotOk() {
        try {
            vueloController.buscarPorId(VuelosUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(VuelosUtilityTest.ID_NOT_FOUND_MESSAGE, VuelosUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarVueloOk() throws Exception {
        when(vueloService.modificarVuelo(any())).thenReturn(VuelosUtilityTest.VUELODTO_UNO);

        ResponseEntity<VueloDTO> response = vueloController.modificarVuelo(VuelosUtilityTest.VUELODTO_UNO);

        verify(vueloService).modificarVuelo(eq(VuelosUtilityTest.VUELODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarVueloNotOk() {
        try {
            vueloController.modificarVuelo(VuelosUtilityTest.VUELODTO_HORASALIDA_NULL);
        } catch (Exception e) {
            assertEquals(VuelosUtilityTest.HORASALIDA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarVueloOk() throws Exception {
        when(vueloService.eliminarVuelo(any())).thenReturn(VuelosUtilityTest.VUELODTO_UNO);

        ResponseEntity<VueloDTO> response = vueloController.eliminarVuelo(VuelosUtilityTest.ID_UNO);

        verify(vueloService).eliminarVuelo(eq(VuelosUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarVueloNotOk() throws Exception {
        try {
            vueloController.eliminarVuelo(VuelosUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(VuelosUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
