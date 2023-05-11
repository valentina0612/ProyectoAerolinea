package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;

import java.util.List;

public interface TrayectoService {
    TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception;

    TrayectoDTO modificarTrayecto(TrayectoDTO trayectoDTO) throws  Exception;
    List<TrayectoDTO> obtenerTrayectos();
    TrayectoDTO buscarPorId(Integer id) throws Exception;

}
