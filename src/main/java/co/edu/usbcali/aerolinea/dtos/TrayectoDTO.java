package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class TrayectoDTO {
    private Integer trayId;
    private Integer avion_avioId;
    private Integer aeropuerto_aeroIdOrigen;
    private Integer aeropuerto_aeroIdDestino;
    private String hora_salida;
    private String hora_llegada;
    private Integer vuelo_vuelId;
    private  String estado;
}
