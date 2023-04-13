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
@Table(name= "aeropuerto")
public class Aeropuerto {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aero_id", nullable = false)
    private Integer aeroId;

    @Column(length = 80, nullable = false)
    private String nombre;
    @Column(length = 5, nullable = false)
    private String iata;

    @Column(length = 80, nullable = false)
    private String ubicacion;

    @Column(length = 1, nullable = false)
    private char estado;
}
