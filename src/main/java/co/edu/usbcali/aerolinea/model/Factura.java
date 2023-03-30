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
@Table(name= "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_id", nullable = false)
    private Integer factId;
    @ManyToOne
    @JoinColumn(name = "rese_id", referencedColumnName = "rese_id")
    private Reserva reserva;
    @Column(length = 30, nullable = false)
    private String fecha;
    @Column(length = 15, nullable = false)
    private String estado;
}
