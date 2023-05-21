package co.edu.usbcali.aerolinea.repository;



import co.edu.usbcali.aerolinea.model.TipoAsiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TipoAsientoRepository extends JpaRepository<TipoAsiento, Integer>{
    boolean existsTipoAsientoByDescripcion(String descripcion);
    boolean existsTipoAsientoByDescripcionAndTiasIdIsNot(String descripcion, Integer id);

    List<TipoAsiento> findByEstado(String estado);

}
