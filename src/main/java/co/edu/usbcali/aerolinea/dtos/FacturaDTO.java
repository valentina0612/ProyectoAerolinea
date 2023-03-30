package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class FacturaDTO {
    private Integer factId;
    private Integer reserva_reseId;
    private String fecha;
    private String estado;

}
