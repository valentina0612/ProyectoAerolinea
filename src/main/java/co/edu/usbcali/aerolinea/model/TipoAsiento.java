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
@Table(name= "tipo_asiento")
public class TipoAsiento {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tias_id", nullable = false)
    private Integer tiasId;

    @Column(length = 30, nullable = false)
    private String descripcion;
    @Column(length = 15, nullable = false)
    private String estado;
}