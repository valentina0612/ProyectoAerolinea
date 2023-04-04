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
    private String tipoAsiento;
    private Integer tipoAsiento_tiasId;
    private String nombreTipoAsiento;
    private String ModeloAvion;
    private Integer avion_avioId;
    private String ubicacion;
    private float precio;
    private  String estado;
}

