package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;

import java.util.List;

public interface TrayectoService {
    TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception;
    List<TrayectoDTO> obtenerTrayectos();
}
