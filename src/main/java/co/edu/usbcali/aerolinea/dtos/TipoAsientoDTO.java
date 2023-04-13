package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data
@Builder
@AllArgsConstructor
@ToString
public class TipoAsientoDTO{
    private Integer tiasId;
    private String descripcion;
    private String estado;
}
