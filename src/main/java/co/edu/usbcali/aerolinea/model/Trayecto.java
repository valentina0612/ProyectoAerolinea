package co.edu.usbcali.aerolinea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "trayecto")
public class Trayecto {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tray_id", nullable = false)
    private Integer trayId;

    @ManyToOne
    @JoinColumn(name = "avio_id", referencedColumnName = "avio_id")
    private Avion avion;
    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuerto;
    @ManyToOne
    @JoinColumn(name = "aero_id_destino",referencedColumnName = "aero_id" )
    private Aeropuerto aeropuerto2;
    @Column(name = "hora_salida", nullable = false)
    private String horaSalida;
    @Column(name = "hora_llegada", nullable = false)
    private String horaLlegada;
    @ManyToOne
    @JoinColumn(name = "vuel_id", referencedColumnName = "vuel_id" )
    private Vuelo vuelo;
    @Column(name = "estado", nullable = false)
    private String estado;

}
