package co.edu.usbcali.aerolinea.dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Data
@Builder
@AllArgsConstructor
@ToString
public class UsuarioDTO {
    private Integer usuaId;
    private Integer rolUsuario_rousid;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String estado;
}
