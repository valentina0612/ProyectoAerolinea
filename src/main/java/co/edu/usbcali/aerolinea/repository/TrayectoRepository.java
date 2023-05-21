package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Trayecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto, Integer> {
    List<Trayecto> findByEstado(String estado);

}
