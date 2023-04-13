package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Builder
@AllArgsConstructor
@ToString
@Data
public class ReservaDTO {

    private Integer reseId;
    private Integer vuelId;
    private Integer asieId;
    private Integer usuaId;
    private Integer precioTotal;
    private String estadoPago;
    private Date fecha;
    private String estado;



}
