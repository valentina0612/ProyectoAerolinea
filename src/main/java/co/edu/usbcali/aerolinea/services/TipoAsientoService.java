package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;

import java.util.List;

public interface TipoAsientoService{
    TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;
    List<TipoAsientoDTO> obtenerTipoAsiento();
}
