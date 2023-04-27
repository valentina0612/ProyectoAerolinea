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
@Table(name= "factura")
public class Factura {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_id", nullable = false)
    private Integer factId;

    @ManyToOne
    @JoinColumn(name = "rese_id", referencedColumnName = "rese_id")
    private Reserva reserva;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private String fecha;
    @Column(name = "estado", nullable = false)
    private String estado;
}
