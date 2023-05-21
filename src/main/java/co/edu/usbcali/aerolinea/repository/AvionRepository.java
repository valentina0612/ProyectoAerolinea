package co.edu.usbcali.aerolinea.repository;



import co.edu.usbcali.aerolinea.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {
    List<Avion> findAllByEstado(String estado);
}
