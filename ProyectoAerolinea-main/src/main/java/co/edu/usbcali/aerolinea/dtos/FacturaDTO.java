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

public class FacturaDTO {
    private Integer factId;
    private Integer reseId;
    private Date fecha;
    private String estado;

}
