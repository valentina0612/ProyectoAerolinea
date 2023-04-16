package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;

import java.util.List;

public interface AsientoService {
    AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception;
    List<AsientoDTO> obtenerAsientos();

    AsientoDTO buscarPorId(Integer id) throws Exception;
}
