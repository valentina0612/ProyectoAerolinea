package co.edu.usbcali.aerolinea.repository;


import co.edu.usbcali.aerolinea.model.Factura;
import co.edu.usbcali.aerolinea.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findAllByEstado(String estado);

    Factura findByReserva(Reserva reserva);

    Boolean existsByReserva(Integer Id);

    Boolean existsByReservaAndFactId(Integer resId, Integer factId);

}
