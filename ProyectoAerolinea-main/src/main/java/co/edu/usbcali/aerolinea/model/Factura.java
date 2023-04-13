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
@Table(name = "factura")
public class Factura {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fact_id", nullable = false)
    private Integer factId;

    @Column(name = "rese_id", nullable = false)
    private Integer reseId;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "estado", nullable = false)
    private String estado;
}
