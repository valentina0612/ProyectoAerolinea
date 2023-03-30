package co.edu.usbcali.aerolinea.repository;
import co.edu.usbcali.aerolinea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
