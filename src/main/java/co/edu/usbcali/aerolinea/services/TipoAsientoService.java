package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;

import java.util.List;

public interface TipoAsientoService{
    TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;

    TipoAsientoDTO modificarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;
    List<TipoAsientoDTO> obtenerTipoAsiento();

    TipoAsientoDTO buscarPorId(Integer id) throws Exception;

    TipoAsientoDTO eliminarTipoAsiento(Integer id) throws Exception;

    List<TipoAsientoDTO> obtenerTipoAsientosActivos();
}
