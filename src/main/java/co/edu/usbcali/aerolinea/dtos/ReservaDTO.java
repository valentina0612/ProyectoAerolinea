package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class ReservaDTO {
    private Integer reseId;
    private Integer vuelo_vuelId;
    private Integer asiento_asieId;
    private Integer usuario_usuaId;
    private float precioTotal;
    private String estadoPago;
    private String fecha;
    private  String estado;
}
