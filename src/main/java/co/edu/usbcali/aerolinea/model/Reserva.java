package co.edu.usbcali.aerolinea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rese_id", nullable = false)
    private Integer reseId;
    @ManyToOne
    @JoinColumn(name = "vuel_id", referencedColumnName = "vuel_id")
    private Vuelo vuelo;
    @ManyToOne
    @JoinColumn(name = "asie_id", referencedColumnName = "asie_id")
    private Asiento asiento;
    @ManyToOne
    @JoinColumn(name = "usua_id", referencedColumnName = "usua_id")
    private Usuario usuario;
    @Column(length = 15, nullable = false)
    private float precioTotal;
    @Column(length = 15, nullable = false)
    private String estadoPago;
    @Column(length = 30, nullable = false)
    private String fecha;
    @Column(length = 15, nullable = false)
    private  String estado;
}
