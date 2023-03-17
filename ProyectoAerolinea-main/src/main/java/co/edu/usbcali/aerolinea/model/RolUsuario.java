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
@Table(name= "rol_usuario")

public class RolUsuario {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rous_id", nullable = false)
    private Integer rousId;

    @Column(length = 30, nullable = false)
    private String descripcion;
    @Column(length = 1, nullable = false)
    private char estado;

}
