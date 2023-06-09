package co.edu.usbcali.aerolinea.repository;
import co.edu.usbcali.aerolinea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    List<Usuario> findByEstado(String estado);
    boolean existsByCorreo(String correo);
    boolean existsByCorreoAndUsuaIdIsNot(String correo, Integer id);

    Usuario findByCorreo(String correo);

    boolean existsByCedula(String cedula);

    boolean existsByCorreoAndCedulaIsNot(String correo, String cedula);

}
