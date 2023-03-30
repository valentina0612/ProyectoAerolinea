package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
}
