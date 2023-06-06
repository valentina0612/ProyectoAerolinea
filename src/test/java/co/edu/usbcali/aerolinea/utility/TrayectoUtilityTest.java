package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.model.Trayecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrayectoUtilityTest {
    public static Integer ID_UNO = 1;
    public static Date HORA_SALIDA_UNO = new Date();
    public static Date HORA_LLEGADA_UNO = new Date();
    public static String ESTADO_UNO = "Activo";
    public static Integer ID_DOS = 2;
    public static Integer TRAYECTOS_SIZE = 2;
    public static Integer TRAYECTOS_VACIO_SIZE = 0;
    public static String FECHA_FUTURO = "2023-11-27 08:00";
    public static String FECHA_FUTURO_DOS = "2023-12-27 10:00";
    public static String PATTERN_FECHA = "yyyy-MM-dd HH:mm";
    public static Date FECHA_FUTURO_DATE;
    public static Date FECHA_FUTURO_DATE_DOS;

    public static String HORASALIDA_REQUIRED_MESSAGE = "La hora de salida no puede ser nula";
    public static String ID_NOT_FOUND_MESSAGE = "El trayecto con id %s no existe";

    static {
        try {
            FECHA_FUTURO_DATE = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try {
            FECHA_FUTURO_DATE_DOS = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO_DOS);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Trayecto TRAYECTO_UNO = Trayecto.builder()
            .trayId(1)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuerto(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuerto2(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelo(VuelosUtilityTest.VUELO_UNO)
            .estado("Activo")
            .build();

    public static Trayecto TRAYECTO_DOS = Trayecto.builder()
            .trayId(2)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuerto(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuerto2(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelo(VuelosUtilityTest.VUELO_UNO)
            .estado("Activo")
            .build();

    public static TrayectoDTO TRAYECTODTO_UNO = TrayectoDTO.builder()
            .trayId(1)
            .avioId(AvionUtilityTest.AVION_UNO.getAvioID())
            .aereoIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aereoIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelId(VuelosUtilityTest.VUELO_UNO.getVueloId())
            .estado("Activo")
            .build();

    public static TrayectoDTO TRAYECTODTO_DOS = TrayectoDTO.builder()
            .trayId(2)
            .avioId(AvionUtilityTest.AVION_UNO.getAvioID())
            .aereoIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aereoIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelId(VuelosUtilityTest.VUELO_UNO.getVueloId())
            .estado("Activo")
            .build();

    public static TrayectoDTO TRAYECTODTO_HORASALIDA_NULL = TrayectoDTO.builder()
            .trayId(1)
            .trayId(2)
            .avioId(AvionUtilityTest.AVION_UNO.getAvioID())
            .aereoIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aereoIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .horaSalida(null)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelId(VuelosUtilityTest.VUELO_UNO.getVueloId())
            .estado("Activo")
            .build();

    public static List<Trayecto> TRAYECTOS = Arrays.asList(TRAYECTO_UNO, TRAYECTO_DOS);
    public static List<TrayectoDTO> TRAYECTOSDTO = Arrays.asList(TRAYECTODTO_UNO, TRAYECTODTO_DOS);
    public static List<Trayecto> TRAYECTOS_VACIO = Arrays.asList();
    public static List<TrayectoDTO> TRAYECTOSDTO_VACIO = Arrays.asList();

}
