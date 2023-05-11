package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.Usuario;

import java.util.Arrays;
import java.util.List;

public class UsuarioUtilityTest {
    public static Integer ID_UNO = 1;
    public static String CEDULA_UNO = "123456789";
    public static String NOMBRE_UNO = "Santiago";
    public static String APELLIDO_UNO = "García";
    public static String CORREO_UNO = "santiagogarcia@gmail.com";
    public static String ESTADO_UNO = "A";
    public static Integer USUARIOS_SIZE = 2;
    public static Integer USUARIOS_VACIO_SIZE = 0;

    public static Usuario USUARIO_UNO = Usuario.builder()
            .usuaId(1)
            .rolUsuario(RolUsuarioUtilityTest.ROLUSUARIO_UNO)
            .cedula("123456789")
            .nombre("Santiago")
            .apellido("García")
            .correo("santiagogarcia@gmail.com")
            .estado("A")
            .build();

    public static Usuario USUARIO_DOS = Usuario.builder()
            .usuaId(2)
            .rolUsuario(RolUsuarioUtilityTest.ROLUSUARIO_UNO)
            .cedula("987654321")
            .nombre("Juan")
            .apellido("Pérez")
            .correo("juanperez@gmail.com")
            .estado("A")
            .build();

    public static UsuarioDTO USUARIODTO = UsuarioDTO.builder()
            .usuaId(1)
            .rolUsuario_rousid(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getRousId())
            .cedula("123456789")
            .nombre("Santiago")
            .apellido("García")
            .correo("santiagogarcia@gmail.com")
            .estado("A")
            .build();

    public static List<Usuario> USUARIOS = Arrays.asList(USUARIO_UNO, USUARIO_DOS);

    public static List<Usuario> USUARIOS_VACIO = Arrays.asList();
}
