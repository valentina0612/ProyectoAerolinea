package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Reserva;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findAllByEstadoAndVuelo(String estado, Vuelo vuelo);
    List<Reserva> findAllByUsuario(Usuario usuario);
}
