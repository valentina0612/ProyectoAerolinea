package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.model.RolUsuario;

import java.util.Arrays;
import java.util.List;

public class RolUsuarioUtilityTest {
    public static Integer ID_UNO = 1;
    public static String DESCRIPCION_UNO = "Cliente";
    public static String ESTADO_UNO = "A";
    public static Integer ROLUSUARIOS_SIZE = 2;
    public static Integer ROLUSUARIOS_VACIO_SIZE = 0;

    public static RolUsuario ROLUSUARIO_UNO = RolUsuario.builder()
            .rousId(1)
            .descripcion("Cliente")
            .estado("A")
            .build();

    public static RolUsuario ROLUSUARIO_DOS = RolUsuario.builder()
            .rousId(2)
            .descripcion("Administrador")
            .estado("A")
            .build();

    public static RolUsuarioDTO ROLUSUARIODTO = RolUsuarioDTO.builder()
            .rousId(1)
            .descripcion("Cliente")
            .estado("A")
            .build();

    public static List<RolUsuario> ROLUSUARIOS = Arrays.asList(ROLUSUARIO_UNO, ROLUSUARIO_DOS);

    public static List<RolUsuario> ROLUSUARIOS_VACIO = Arrays.asList();
}
