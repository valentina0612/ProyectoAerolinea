package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
