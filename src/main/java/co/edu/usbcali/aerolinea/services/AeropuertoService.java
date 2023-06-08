package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO ;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;

import java.util.List;

public interface AeropuertoService {
    AeropuertoDTO  guardarAeropuerto (AeropuertoDTO  aeropuertoDTO) throws Exception;
    AeropuertoDTO  modificarAeropuerto (AeropuertoDTO  aeropuertoDTO) throws Exception;

    List<AeropuertoDTO > obtenerAeropuertos();

    AeropuertoDTO buscarPorId(Integer id) throws Exception;

    AeropuertoDTO eliminarAeropuerto(Integer id) throws Exception;

    List<AeropuertoDTO> obtenerAeropuertosActivos();
}
