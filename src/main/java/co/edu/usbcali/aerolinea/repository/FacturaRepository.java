package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
