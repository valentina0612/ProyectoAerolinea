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
@Table(name= "asiento")
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asie_id", nullable = false)
    private Integer asieId;

    @ManyToOne
    @JoinColumn(name = "tias_id", referencedColumnName = "tias_id")
    private TipoAsiento tipoAsiento;

    @ManyToOne
    @JoinColumn(name = "avio_id", referencedColumnName = "avio_id")
    private Avion avion;
    @Column(length = 12)
    private String ubicacion;
    @Column(length = 19)
    private Integer precio;
    @Column(length = 15, nullable = false)
    private  String estado;
    }
