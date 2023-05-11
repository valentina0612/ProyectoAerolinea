package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import co.edu.usbcali.aerolinea.services.RolUsuarioServiceImpl;
import co.edu.usbcali.aerolinea.utility.RolUsuarioUtilityTest;
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
public class RolUsuarioServicelpmlTest {

    @InjectMocks
    private RolUsuarioServiceImpl rolUsuarioServiceImpl;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;



    @Test
    public void obtenerRolUsuariosOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolesUsuario();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_SIZE, rolUsuariosSavedDTO.size());
        assertEquals(RolUsuarioUtilityTest.DESCRIPCION_UNO, rolUsuariosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerRolUsuariosNotOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolesUsuario();

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO_SIZE, rolUsuariosSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ID_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.buscarPorId(RolUsuarioUtilityTest.ID_UNO);

        assertEquals(RolUsuarioUtilityTest.ID_UNO, rolUsuarioSavedDTO.getRousId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.buscarPorId(RolUsuarioUtilityTest.ID_UNO));
    }

}
