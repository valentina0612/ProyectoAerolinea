package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.model.Avion;

import java.util.Arrays;
import java.util.List;

public class AvionUtilityTest {

    public static Integer ID_UNO = 1;
    public static Integer ID_DOS = 2;

    public static String MODELO_UNO = "Boeing 737";
    public static String ESTADO_UNO = "Activo";
    public static Integer AVIONES_SIZE = 2;
    public static Integer AVIONES_VACIO_SIZE = 0;
    public static String MODELO_REQUIRED_MESSAGE = "El modelo del avión no puede ser nulo o vacío";
    public static String ID_NOT_FOUND_MESSAGE = "El avión con id %s no existe";

    public static Avion AVION_UNO = Avion.builder()
            .avioID(1)
            .modelo("Boeing 737")
            .estado("Activo")
            .build();

    public static Avion AVION_DOS = Avion.builder()
            .avioID(2)
            .modelo("Boeing 747")
            .estado("Activo")
            .build();

    public static AvionDTO AVIONDTO_UNO = AvionDTO.builder()
            .avioID(1)
            .modelo("Boeing 737")
            .estado("Activo")
            .build();

    public static AvionDTO AVIONDTO_DOS = AvionDTO.builder()
            .avioID(2)
            .modelo("Boeing 747")
            .estado("Activo")
            .build();

    public static AvionDTO AVIONDTO_MODELO_NULL = AvionDTO.builder()
            .avioID(1)
            .modelo(null)
            .estado("Activo")
            .build();
    public static List<Avion> AVIONES = Arrays.asList(AVION_UNO, AVION_DOS);

    public static List<AvionDTO> AVIONESDTO = Arrays.asList(AVIONDTO_UNO, AVIONDTO_DOS);

    public static List<Avion> AVIONES_VACIO = Arrays.asList();

    public static List<AvionDTO> AVIONESDTO_VACIO = Arrays.asList();
}

