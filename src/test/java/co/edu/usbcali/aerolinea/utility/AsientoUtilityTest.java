package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;

import java.util.Arrays;
import java.util.List;

public class AsientoUtilityTest {
    public static Integer ID_UNO = 1;
    public static String UBICACION_UNO = "a1";
    public static String ESTADO_UNO = "Activo";
    public static Integer ID_DOS = 2;
    public static Integer ASIENTOS_SIZE = 2;
    public static Integer ASIENTOS_VACIO_SIZE = 0;
    public static String UBICACION_REQUIRED_MESSAGE = "La ubicación del asiento no puede ser nula o vacía";
    public static String ID_NOT_FOUND_MESSAGE = "El asiento con id %s no existe";

    public static Asiento ASIENTO_UNO = Asiento.builder()
            .asieId(1)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .avion(AvionUtilityTest.AVION_UNO)
            .ubicacion("a1")
            .precio(2323)
            .estado("Activo")
            .build();

    public static Asiento ASIENTO_DOS = Asiento.builder()
            .asieId(2)
            .tipoAsiento(TipoAsientoUtilityTest.TIPOASIENTO_UNO)
            .avion(AvionUtilityTest.AVION_DOS)
            .ubicacion("a1")
            .precio(2323)
            .estado("Activo")
            .build();

    public static AsientoDTO ASIENTODTO_UNO = AsientoDTO.builder()
            .asieId(1)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .avion_avioId(AvionUtilityTest.AVION_UNO.getAvioID())
            .ubicacion("a1")
            .precio(2323)
            .estado("Activo")
            .build();

    public static AsientoDTO ASIENTODTO_DOS = AsientoDTO.builder()
            .asieId(2)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .avion_avioId(AvionUtilityTest.AVION_DOS.getAvioID())
            .ubicacion("a1")
            .precio(2323)
            .estado("Activo")
            .build();

    public static AsientoDTO ASIENTODTO_UBICACION_NULL = AsientoDTO.builder()
            .asieId(1)
            .tipoAsiento_tiasId(ASIENTO_UNO.getTipoAsiento().getTiasId())
            .avion_avioId(AvionUtilityTest.AVION_UNO.getAvioID())
            .ubicacion(null)
            .precio(2323)
            .estado("Activo")
            .build();

    public static List<Asiento> ASIENTOS = Arrays.asList(ASIENTO_UNO, ASIENTO_DOS);
    public static List<AsientoDTO> ASIENTOSDTO = Arrays.asList(ASIENTODTO_UNO, ASIENTODTO_DOS);
    public static List<Asiento> ASIENTOS_VACIO = Arrays.asList();
    public static List<AsientoDTO> ASIENTOSDTO_VACIO = Arrays.asList();
}
