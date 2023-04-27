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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RolUsuarioServicelpmlTest {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    void obtenerAeropuertos_TestOK_(){
        //assertEquals(2,1);
        RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        List<RolUsuario> RolUsuarioRetorno = Arrays.asList(RolUsuario.builder()
                        .rousId(2)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build(),
                RolUsuario.builder()
                                .rousId(3)
                                .descripcion("asdsa")
                                .estado("activo")
                                .build());

        Mockito.when(rolUsuarioRepository.findAll()).thenReturn(RolUsuarioRetorno);

        List<RolUsuarioDTO> usuario = rolUsuarioService.obtenerRolesUsuario();

        assertEquals(2, usuario.size());

    }

    @Test
    public void obtenerRolesUsuario_TestNotOk_() {
        List<RolUsuario> rolUsuario = Arrays.asList();

        Mockito.when(rolUsuarioRepository.findAll()).thenReturn(rolUsuario);

        List<RolUsuarioDTO> rolUsuarioDTOS  = rolUsuarioService.obtenerRolesUsuario();

        assertEquals(0, rolUsuarioDTOS.size());
    }

    @Test
    void buscarPorId_TestOk_() throws Exception {
        RolUsuario RolUsuarioPrueba =
                RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        Mockito.when(rolUsuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(rolUsuarioRepository.getReferenceById(1)).thenReturn(RolUsuarioPrueba);

        // Llamado al método a probar
        RolUsuarioDTO rolUsuarioDTO = rolUsuarioService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(rolUsuarioDTO.getRousId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(rolUsuarioRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioService.buscarPorId(1));
    }

}
