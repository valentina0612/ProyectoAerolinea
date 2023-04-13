package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;


import java.util.List;

public interface AvionService {
    AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception;
    List<AvionDTO> obtenerAviones();
}
