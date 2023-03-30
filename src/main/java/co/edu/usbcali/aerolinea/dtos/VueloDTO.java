package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@ToString

public class VueloDTO {

    private Integer vueloId;
    private Integer aeropuerto_aeroIdOrigen;
    private Integer aeropuerto_aeroIdDestino;
    private float precio;
    private String hora_salida;
    private String hora_llegada;
    private float precioAsientoVip;
    private float precioAsientoNormal;
    private float precioAsientoBasico;
    private  String estado;

}
