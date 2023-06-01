package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Vuelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class VuelosUtilityTest {
    public static Integer ID_UNO = 1;
    public static Integer ID_DOS = 2;
    public static long PRECIO_UNO = 100000;
    public static Date HORA_SALIDA_UNO = new Date();
    public static Date HORA_LLEGADA_UNO = new Date();
    public static long PRECIO_ASIENTO_VIP_UNO = 50000;
    public static long PRECIO_ASIENTO_NORMAL_UNO = 30000;
    public static long PRECIO_ASIENTO_BASICO_UNO = 10000;
    public static String ESTADO_UNO = "A";
    public static Integer VUELOS_SIZE = 2;
    public static Integer VUELOS_VACIO_SIZE = 0;
    public static String FECHA_FUTURO = "2023-11-27 08:00";
    public static String FECHA_FUTURO_DOS = "2023-12-27 10:00";
    public static String PATTERN_FECHA = "yyyy-MM-dd HH:mm";
    public static Date FECHA_FUTURO_DATE;
    public static Date FECHA_FUTURO_DATE_DOS;
    public static String HORASALIDA_REQUIRED_MESSAGE = "La hora de salida del vuelo no puede ser nula";
    public static String ID_NOT_FOUND_MESSAGE = "El vuelo con id %s no existe";


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

    public static Vuelo VUELO_UNO = Vuelo.builder()
            .vueloId(1)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .precio(100000)
            .hora_salida(FECHA_FUTURO_DATE)
            .hora_llegada(FECHA_FUTURO_DATE_DOS)
            .precioAsientoVip(50000)
            .precioAsientoNormal(30000)
            .precioAsientoBasico(10000)
            .estado("A")
            .build();

    public static Vuelo VUELO_DOS = Vuelo.builder()
            .vueloId(2)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .precio(150000)
            .hora_salida(FECHA_FUTURO_DATE)
            .hora_salida(FECHA_FUTURO_DATE_DOS)
            .precioAsientoVip(80000)
            .precioAsientoNormal(50000)
            .precioAsientoBasico(30000)
            .estado("A")
            .build();

    public static VueloDTO VUELODTO_UNO = VueloDTO.builder()
            .vueloId(1)
            .aeropuerto_aeroIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aeropuerto_aeroIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .precio(100000)
            .hora_salida(FECHA_FUTURO_DATE)
            .hora_llegada(FECHA_FUTURO_DATE_DOS)
            .precioAsientoVip(50000)
            .precioAsientoNormal(30000)
            .precioAsientoBasico(10000)
            .estado("A")
            .build();
    public static VueloDTO VUELODTO_DOS = VueloDTO.builder()
            .vueloId(2)
            .aeropuerto_aeroIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aeropuerto_aeroIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .precio(150000)
            .hora_salida(FECHA_FUTURO_DATE)
            .hora_llegada(FECHA_FUTURO_DATE_DOS)
            .precioAsientoVip(80000)
            .precioAsientoNormal(50000)
            .precioAsientoBasico(30000)
            .estado("A")
            .build();

    public static VueloDTO VUELODTO_HORASALIDA_NULL = VueloDTO.builder()
            .vueloId(1)
            .aeropuerto_aeroIdOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getAeroId())
            .aeropuerto_aeroIdDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getAeroId())
            .precio(100000)
            .hora_salida(null)
            .hora_llegada(FECHA_FUTURO_DATE_DOS)
            .precioAsientoVip(50000)
            .precioAsientoNormal(30000)
            .precioAsientoBasico(10000)
            .estado("A")
            .build();

    public static List<Vuelo> VUELOS = Arrays.asList(VUELO_UNO, VUELO_DOS);
    public static List<VueloDTO> VUELOSDTO = Arrays.asList(VUELODTO_UNO, VUELODTO_DOS);

    public static List<Vuelo> VUELOS_VACIO = Arrays.asList();
    public static List<VueloDTO> VUELOSDTO_VACIO = Arrays.asList();

}
