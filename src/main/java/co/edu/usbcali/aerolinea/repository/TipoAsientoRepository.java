package co.edu.usbcali.aerolinea.repository;


import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoAsientoRepository extends JpaRepository<TipoAsiento, Integer>{
    boolean existsTipoAsientoByDescripcion(String descripcion);
    boolean existsTipoAsientoByDescripcionAndTiasIdIsNot(String descripcion, Integer id);
}
