package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception;
    List<ReservaDTO> obtenerReserva();
}
