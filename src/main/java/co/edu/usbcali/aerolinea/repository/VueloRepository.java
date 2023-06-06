package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    List<Vuelo> findByEstado(String estado);

    List<Vuelo> findByAeropuertoDestino_UbicacionAndAeropuertoOrigen_UbicacionAndEstado(String aeropuertoDestino, String aeropuertoOrigen, String estado);
}
