package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception;
    List<ReservaDTO> obtenerReservas();

    ReservaDTO modificarReserva(ReservaDTO reservaDTO) throws Exception;
    ReservaDTO buscarPorId(Integer id) throws Exception;

    List<ReservaDTO> obtenerReservasVuelo(Integer idVuelo) throws  Exception;

    List<ReservaDTO> obtenerReservasUsuario(Integer idUsuario) throws Exception;

    ReservaDTO eliminarReserva(Integer id) throws Exception;
}
