package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.model.Asiento;

import java.util.Arrays;
import java.util.List;

public class AsientoUtilityTest {
    public static Integer ID_UNO = 1;
    public static String UBICACION_UNO = "a1";
    public static String ESTADO_UNO = "A";
    public static Integer ID_DOS = 2;
    public static Integer ASIENTOS_SIZE = 2;
    public static Integer ASIENTOS_VACIO_SIZE = 0;
    public static String UBICACION_REQUIRED_MESSAGE = "La ubicación del asiento no puede ser nula o vacía";
    public static String ID_NOT_FOUND_MESSAGE = "El asiento con id %s no existe";

    public static Asiento ASIENTO_UNO = Asiento.builder()
            .asieId(1)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .ubicacion("a1")
            .estado("A")
            .build();

    public static Asiento ASIENTO_DOS = Asiento.builder()
            .asieId(2)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .ubicacion("a2")
            .estado("A")
            .build();

    public static AsientoDTO ASIENTODTO_UNO = AsientoDTO.builder()
            .asieId(1)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .ubicacion("a1")
            .estado("A")
            .build();

    public static AsientoDTO ASIENTODTO_DOS = AsientoDTO.builder()
            .asieId(2)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .ubicacion("a1")
            .estado("A")
            .build();

    public static AsientoDTO ASIENTODTO_UBICACION_NULL = AsientoDTO.builder()
            .asieId(1)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .ubicacion(null)
            .estado("A")
            .build();

    public static List<Asiento> ASIENTOS = Arrays.asList(ASIENTO_UNO, ASIENTO_DOS);
    public static List<AsientoDTO> ASIENTOSDTO = Arrays.asList(ASIENTODTO_UNO, ASIENTODTO_DOS);
    public static List<Asiento> ASIENTOS_VACIO = Arrays.asList();
    public static List<AsientoDTO> ASIENTOSDTO_VACIO = Arrays.asList();
}
