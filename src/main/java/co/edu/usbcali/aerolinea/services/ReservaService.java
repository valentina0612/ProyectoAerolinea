package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception;
    List<ReservaDTO> obtenerReservas();

    ReservaDTO buscarPorId(Integer id) throws Exception;
}
