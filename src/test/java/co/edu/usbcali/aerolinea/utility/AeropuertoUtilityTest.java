package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;

import java.util.Arrays;
import java.util.List;

public class AeropuertoUtilityTest {

    public static Integer ID_UNO = 1;
    public static String NOMBRE_UNO = "Aeropuerto Internacional El Dorado";
    public static String IATA_UNO = "BOG";
    public static String UBICACION_UNO = "Bogotá";
    public static String ESTADO_UNO = "Activo";
    public static Integer ID_DOS = 2;
    public static String NOMBRE_DOS = "Aeropuerto Internacional Alfonso Bonilla Aragón";
    public static String IATA_DOS = "COL";
    public static String UBICACION_DOS = "Santiago de Cali";
    public static String ESTADO_DOS = "Activo";
    public static Integer AEROPUERTOS_SIZE = 2;
    public static Integer AEROPUERTOS_VACIO_SIZE = 0;

    public static String NOMBRE_REQUIRED_MESSAGE = "El nombre del aeropuerto no puede ser nulo  o vacío";
    public static String ID_NOT_FOUND_MESSAGE = "El aeropuerto con id %s no existe";

    public static Aeropuerto AEROPUERTO_UNO = Aeropuerto.builder()
            .aeroId(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("Activo")
            .build();

    public static Aeropuerto AEROPUERTO_DOS = Aeropuerto.builder()
            .aeroId(2)
            .nombre("Aeropuerto Internacional Alfonso Bonilla Aragón")
            .iata("COL")
            .ubicacion("Santiago de Cali")
            .estado("Activo")
            .build();

    public static AeropuertoDTO AEROPUERTODTO_UNO = AeropuertoDTO.builder()
            .aeroId(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("Activo")
            .build();

    public static AeropuertoDTO AEROPUERTODTO_DOS = AeropuertoDTO.builder()
            .aeroId(2)
            .nombre("Aeropuerto Internacional Alfonso Bonilla Aragón")
            .iata("COL")
            .ubicacion("Santiago de Cali")
            .estado("Activo")
            .build();

    public static AeropuertoDTO AEROPUERTODTO_NOMBRE_NULL = AeropuertoDTO.builder()
            .aeroId(1)
            .nombre(null)
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("Activo")
            .build();

    public static List<Aeropuerto> AEROPUERTOS = Arrays.asList(AEROPUERTO_UNO, AEROPUERTO_DOS);
    public static List<Aeropuerto> AEROPUERTOS_VACIO = Arrays.asList();
    public static List<AeropuertoDTO> AEROPUERTOSDTO = Arrays.asList(AEROPUERTODTO_UNO, AEROPUERTODTO_DOS);
    public static List<AeropuertoDTO> AEROPUERTOSDTO_VACIO = Arrays.asList();
}
