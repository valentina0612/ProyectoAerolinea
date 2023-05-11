package co.edu.usbcali.aerolinea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "vuelo")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vuel_id", nullable = false)
    private Integer vueloId;
    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "aero_id_destino", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoDestino;

    @Column(length = 19, nullable = false)
    private float precio;
    @Column(length = 30)
    private Date hora_salida;
    @Column(length = 30)
    private Date hora_llegada;
    @Column(length = 19)
    private float precioAsientoVip;
    @Column(length = 19)
    private float precioAsientoNormal;
    @Column(length = 19)
    private float precioAsientoBasico;
    @Column(length = 15, nullable = false)
    private  String estado;
}
