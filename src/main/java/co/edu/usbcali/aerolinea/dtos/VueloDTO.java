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
    private String nombreAeroOrigen;
    private String nombreAeroDestino;
    private float precio;
    private Date hora_salida;
    private Date hora_llegada;
    private float precioAsientoVip;
    private float precioAsientoNormal;
    private float precioAsientoBasico;
    private  String estado;

}
