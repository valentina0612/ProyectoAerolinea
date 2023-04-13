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
@Table(name= "avion")
public class Avion {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avio_id", nullable = false)
    private Integer avioID;

    @Column(length = 30, nullable = false)
    private String modelo;
    @Column(length = 15, nullable = false)
    private String estado;
}
