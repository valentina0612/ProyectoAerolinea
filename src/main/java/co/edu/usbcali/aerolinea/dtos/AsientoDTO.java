package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class AsientoDTO {
    private Integer asieId;
    private Integer tipoAsiento_tiasId;
    private Integer avion_avioId;
    private String ubicacion;
    private float precio;
    private  String estado;
}

