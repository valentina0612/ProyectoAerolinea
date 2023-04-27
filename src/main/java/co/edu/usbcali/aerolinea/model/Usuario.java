package co.edu.usbcali.aerolinea.model;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
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
@Table(name= "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usua_id", nullable = false)
    private Integer usuaId;

    @ManyToOne
    @JoinColumn(name = "rous_id", referencedColumnName = "rous_id")
    private RolUsuario rolUsuario;
    @Column(length = 12, nullable = false)
    private String cedula;
    @Column(length = 30, nullable = false)
    private String nombre;
    @Column(length = 30, nullable = false)
    private String apellido;
    @Column(length = 30, nullable = false)
    private String correo;
    @Column(length = 15, nullable = false)
    private String estado;
}

