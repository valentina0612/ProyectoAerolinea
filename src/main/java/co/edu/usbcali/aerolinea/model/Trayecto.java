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
@Table(name= "trayecto")
public class Trayecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tray_id", nullable = false)
    private Integer trayId;

    @ManyToOne
    @JoinColumn(name = "avio_id", referencedColumnName = "avio_id")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoOrigen;
    @ManyToOne
    @JoinColumn(name = "aero_id_destino", referencedColumnName = "aero_id")
    private Aeropuerto aeropuerto_aeroIdDestino;
    @Column(length = 30)
    private String hora_salida;

    @Column(length = 30)
    private String hora_llegada;
    @ManyToOne
    @JoinColumn(name = "vuel_id", referencedColumnName = "vuel_id")
    private Vuelo vuelo_vuelId;
    @Column(length = 15, nullable = false)
    private  String estado;
}
