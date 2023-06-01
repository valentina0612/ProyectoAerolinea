package co.edu.usbcali.aerolinea.utility;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.model.TipoAsiento;

import java.util.Arrays;
import java.util.List;

public class TipoAsientoUtilityTest {
    public static Integer ID_UNO = 1;
    public static String DESCRIPCION_UNO = "Ejecutivo";
    public static String ESTADO_UNO = "A";

    public static Integer ID_DOS = 2;
    public static Integer TIPOASIENTOS_SIZE = 2;
    public static Integer TIPOASIENTOS_VACIO_SIZE = 0;

    public static String DESCRIPCION_REQUIRED_MESSAGE = "La descripción no puede ser nula o vacía";
    public static String ID_NOT_FOUND_MESSAGE = "El tipo de asiento con id %s no existe";

    public static TipoAsiento TIPOASIENTO_UNO = TipoAsiento.builder()
            .tiasId(1)
            .descripcion("Ejecutivo")
            .estado("A")
            .build();

    public static TipoAsiento TIPOASIENTO_DOS = TipoAsiento.builder()
            .tiasId(2)
            .descripcion("Económico")
            .estado("A")
            .build();

    public static TipoAsientoDTO TIPOASIENTODTO_UNO = TipoAsientoDTO.builder()
            .tiasId(1)
            .descripcion("Ejecutivo")
            .estado("A")
            .build();

    public static TipoAsientoDTO TIPOASIENTODTO_DOS = TipoAsientoDTO.builder()
            .tiasId(2)
            .descripcion("Económico")
            .estado("A")
            .build();

    public static TipoAsientoDTO TIPOASIENTODTO_DESCRIPCION_NULL = TipoAsientoDTO.builder()
            .tiasId(1)
            .descripcion(null)
            .estado("A")
            .build();

    public static List<TipoAsiento> TIPOASIENTOS = Arrays.asList(TIPOASIENTO_UNO, TIPOASIENTO_DOS);
    public static List<TipoAsientoDTO> TIPOASIENTOSDTO = Arrays.asList(TIPOASIENTODTO_UNO, TIPOASIENTODTO_DOS);
    public static List<TipoAsiento> TIPOASIENTOS_VACIO = Arrays.asList();
    public static List<TipoAsientoDTO> TIPOASIENTOSDTO_VACIO = Arrays.asList();

}
