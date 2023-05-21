package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.model.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
    boolean existsAeropuertoByNombre(String nombre);
    boolean existsAeropuertoByNombreAndAeroId(String nombre, Integer id);
    Aeropuerto findByNombre(String nombre);
    List<Aeropuerto> findAllByEstado(String estado);
}
