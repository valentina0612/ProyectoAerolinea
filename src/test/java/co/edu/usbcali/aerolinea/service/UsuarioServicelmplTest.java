package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.AsientoService;
import co.edu.usbcali.aerolinea.services.UsuarioService;
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
public class UsuarioServicelmplTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;

    @MockBean
    UsuarioRepository usuarioRepository;


    @Test
    void obtenerUsuarios_TestOK_() throws Exception {
        RolUsuario rolUsuario =
                RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .descripcion("asdasd")
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        List<Usuario> usuariosRetorno = Arrays.asList(Usuario.builder()
                        .usuaId(1)
                        .rolUsuario(rolUsuario)
                        .descripcion("braud")
                        .cedula("121323")
                        .nombre("brayan")
                        .apellido("zamora")
                        .correo("brayanzamora@gmail.com")
                        .estado("activo")
                        .build(),
                Usuario.builder()
                                .usuaId(1)
                                .rolUsuario(rolUsuario)
                                .descripcion("dsad")
                                .cedula("121323")
                                .nombre("brayan")
                                .apellido("zamora")
                                .correo("brayanzamora@gmail.com")
                                .estado("activo")
                                .build());

        // Mock de la respuesta del repositorio
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuariosRetorno);

        List<UsuarioDTO> usuario = usuarioService.obtenerUsuarios();

        assertEquals(2, usuario.size());


    }

    @Test
    public void obtenerUsuarios_TestNotOk() {
        List<Usuario> usuarios = Arrays.asList();

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioDTO> usuarioDTOS = usuarioService.obtenerUsuarios();

        assertEquals(0, usuarioDTOS.size());
    }

    @Test
    void buscarPorId() throws Exception {
        RolUsuario rolUsuario =
                RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        Usuario usuarioPrueba = Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .descripcion("sdsa")
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        // Mock de la respuesta del repositorio
        Mockito.when(usuarioRepository.existsById(1)).thenReturn(true);
        Mockito.when(usuarioRepository.getReferenceById(1)).thenReturn(usuarioPrueba);

        // Llamado al método a probar
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(usuarioDTO.getUsuaId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(usuarioRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioService.buscarPorId(1));
    }
}
