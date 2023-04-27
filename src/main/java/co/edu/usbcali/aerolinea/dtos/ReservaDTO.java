package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@ToString
public class ReservaDTO {
    private Integer reseId;
    private Integer vuelId;
    private Integer asieId;
    private Integer usuaId;
    private Integer precioTotal;
    private String estadoPago;
    private String fecha;
    private String estado;

}
