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
public class FacturaDTO {
    private Integer factId;
    private Integer reseId;
    private Date fecha;
    private String estado;


}
