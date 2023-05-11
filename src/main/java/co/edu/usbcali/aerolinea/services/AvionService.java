package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;


import java.util.List;

public interface AvionService {
    AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception;

    AvionDTO modificarAvion(AvionDTO avionDTO) throws Exception;
    List<AvionDTO> obtenerAviones();
    AvionDTO buscarPorId(Integer id) throws Exception;
}
