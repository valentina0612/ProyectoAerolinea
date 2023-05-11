package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.model.Avion;

import java.util.Arrays;
import java.util.List;

public class AvionUtilityTest {public static Integer ID_UNO = 1;
    public static String MODELO_UNO = "Boeing 737";
    public static String AEROLINEA_UNO = "Avianca";
    public static String ESTADO_UNO = "A";
    public static Integer AVIONES_SIZE = 2;
    public static Integer AVIONES_VACIO_SIZE = 0;

    public static Avion AVION_UNO = Avion.builder()
            .avioID(1)
            .modelo("Boeing 737")
            .estado("A")
            .build();

    public static Avion AVION_DOS = Avion.builder()
            .avioID(2)
            .modelo("Boeing 747")
            .estado("A")
            .build();

    public static AvionDTO AVIONDTO = AvionDTO.builder()
            .avioID(2)
            .modelo("Boeing 737")
            .estado("A")
            .build();

    public static List<Avion> AVIONES = Arrays.asList(AVION_UNO, AVION_DOS);

    public static List<Avion> AVIONES_VACIO = Arrays.asList();
}
