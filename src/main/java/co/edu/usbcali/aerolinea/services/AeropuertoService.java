package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO ;

import java.util.List;

public interface AeropuertoService {
    AeropuertoDTO  guardarAeropuerto (AeropuertoDTO  aeropuertoDTO) throws Exception;

    List<AeropuertoDTO > obtenerAeropuertos();
}
