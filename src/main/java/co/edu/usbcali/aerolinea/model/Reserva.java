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
@Table(name= "reserva")
public class Reserva {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rese_id", nullable = false)
    private Integer reseId;

    @Column(name = "vuel_id", nullable = false)
    private Integer vuelId;

    @Column(name = "asie_id", nullable = false)
    private Integer asieId;

    @Column(name = "usua_id", nullable = false)
    private Integer usuaId;

    @Column(name = "precioTotal", nullable = false)
    private Integer  precioTotal;

    @Column(name = "estadoPago", nullable = false)
    private String estadoPago;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "estado", nullable = false)
    private String estado;

}
