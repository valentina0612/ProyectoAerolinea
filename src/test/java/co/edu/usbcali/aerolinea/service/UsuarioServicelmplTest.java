package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.AsientoService;
import co.edu.usbcali.aerolinea.services.UsuarioService;
import co.edu.usbcali.aerolinea.services.UsuarioServiceImpl;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilityTest;
import co.edu.usbcali.aerolinea.utility.UsuarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UsuarioServicelmplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void guardarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO_UNO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getUsuaId());
    }

    @Test
    public void guardarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO_UNO));
    }
    @Test
    public void obtenerUsuariosOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(UsuarioUtilityTest.USUARIOS_SIZE, usuariosSavedDTO.size());
        assertEquals(UsuarioUtilityTest.CEDULA_UNO, usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosNotOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(UsuarioUtilityTest.USUARIOS_VACIO_SIZE, usuariosSavedDTO.size());
    }

    @Test
    public void obtenerUsuariosActivosOk() {
        given(usuarioRepository.findByEstado("Activo")).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(UsuarioUtilityTest.USUARIOS_SIZE, usuariosSavedDTO.size());
        assertEquals(UsuarioUtilityTest.CEDULA_UNO, usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosActivosNotOk() {
        given(usuarioRepository.findByEstado("Activo")).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(UsuarioUtilityTest.USUARIOS_VACIO_SIZE, usuariosSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);

        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.ID_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.buscarPorId(UsuarioUtilityTest.ID_UNO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getUsuaId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.buscarPorId(UsuarioUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.modificarUsuario(UsuarioUtilityTest.USUARIODTO_UNO);

        assertEquals(UsuarioUtilityTest.ID_UNO, usuarioSavedDTO.getUsuaId());
    }

    @Test
    public void actualizarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.modificarUsuario(UsuarioUtilityTest.USUARIODTO_UNO));
    }

}
