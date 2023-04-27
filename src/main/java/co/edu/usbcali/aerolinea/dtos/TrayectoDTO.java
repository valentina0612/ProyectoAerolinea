package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@ToString
public class TrayectoDTO {
    private Integer trayId;
    private Integer avioId;
    private Integer aereoIdOrigen;
    private Integer aereoIdDestino;
    private String horaSalida;
    private String horaLlegada;
    private Integer vuelId;
    private String estado;
}
