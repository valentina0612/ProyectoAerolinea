package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilityTest;
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
public class RolUsuarioControllerTest {
    @Autowired
    private RolUsuarioController rolUsuarioController;

    @MockBean
    private RolUsuarioService rolUsuarioService;

    @Test
    public void guardarRolUsuarioOk() throws Exception {
        when(rolUsuarioService.guardarRolUsuario(any())).thenReturn(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.guardarRol(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        verify(rolUsuarioService).guardarRolUsuario(eq(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarRolUsuarioNotOk() {
        try {
            rolUsuarioController.guardarRol(RolUsuarioUtilityTest.ROLUSUARIODTO_DESCRIPCION_NULL);
        } catch (Exception e) {
            assertEquals(RolUsuarioUtilityTest.DESCRIPTION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerRolUsuariosOk() {
        when(rolUsuarioService.obtenerRolesUsuario()).thenReturn(RolUsuarioUtilityTest.ROLUSUARIOSDTO);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.roles();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerRolUsuariosNotOk() {
        when(rolUsuarioService.obtenerRolesUsuario()).thenReturn(RolUsuarioUtilityTest.ROLUSUARIOSDTO_VACIO);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.roles();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerRolUsuariosActivosOk() {
        when(rolUsuarioService.obtenerRolesActivos()).thenReturn(RolUsuarioUtilityTest.ROLUSUARIOSDTO);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.obtenerRolesActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerRolUsuariosActivosNotOk() {
        when(rolUsuarioService.obtenerRolesUsuario()).thenReturn(RolUsuarioUtilityTest.ROLUSUARIOSDTO_VACIO);

        ResponseEntity<List<RolUsuarioDTO>> response = rolUsuarioController.obtenerRolesActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerRolUsuarioOk() throws Exception {
        when(rolUsuarioService.buscarPorId(any())).thenReturn(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.buscarPorId(RolUsuarioUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerRolUsuarioNotOk() {
        try {
            rolUsuarioController.buscarPorId(RolUsuarioUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(RolUsuarioUtilityTest.ID_NOT_FOUND_MESSAGE, RolUsuarioUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarRolUsuarioOk() throws Exception {
        when(rolUsuarioService.modificarRolUsuario(any())).thenReturn(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.modificarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        verify(rolUsuarioService).modificarRolUsuario(eq(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarRolUsuarioNotOk() {
        try {
            rolUsuarioController.modificarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO_DESCRIPCION_NULL);
        } catch (Exception e) {
            assertEquals(RolUsuarioUtilityTest.DESCRIPTION_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarRolUsuarioOk() throws Exception {
        when(rolUsuarioService.eliminarRolUsuario(any())).thenReturn(RolUsuarioUtilityTest.ROLUSUARIODTO_UNO);

        ResponseEntity<RolUsuarioDTO> response = rolUsuarioController.eliminarRol(RolUsuarioUtilityTest.ID_UNO);

        verify(rolUsuarioService).eliminarRolUsuario(eq(RolUsuarioUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarRolUsuarioNotOk() throws Exception {
        try {
            rolUsuarioController.eliminarRol(RolUsuarioUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(RolUsuarioUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
