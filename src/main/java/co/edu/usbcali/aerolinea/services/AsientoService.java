package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import lombok.AllArgsConstructor;

import java.util.List;

public interface AsientoService {
    AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception;

    AsientoDTO modificarAsiento(AsientoDTO asientoDTO) throws Exception;

    List<AsientoDTO> obtenerAsientos();

    AsientoDTO buscarPorId(Integer id) throws Exception;

    List<AsientoDTO> obtenerAsientosActivos();

    AsientoDTO asientoOcupado(Integer id) throws Exception;

}
