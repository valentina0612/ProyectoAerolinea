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
@Table(name = "trayecto")
public class Trayecto {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tray_id", nullable = false)
    private Integer trayId;
    @Column(name = "avio_id", nullable = false)
    private Integer avioId;
    @Column(name = "aereo_id_origen", nullable = false)
    private Integer aereoIdOrigen;
    @Column(name = "aereo_id_destin", nullable = false)
    private Integer aereoIdDestin;
    @Column(name = "hora_salida", nullable = false)
    private Time horaSalida;
    @Column(name = "hora_llegada", nullable = false)
    private Time horaLlegada;
    @Column(name = "vuel_id", nullable = false)
    private Integer vuelId;
    @Column(name = "estado", nullable = false)
    private String estado;




}
