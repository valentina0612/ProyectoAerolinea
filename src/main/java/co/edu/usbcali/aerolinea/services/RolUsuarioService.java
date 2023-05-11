package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;

import java.util.List;

public interface RolUsuarioService {
    RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;

    RolUsuarioDTO modificarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;

    List<RolUsuarioDTO> obtenerRolesUsuario();

    RolUsuarioDTO buscarPorId(Integer id) throws Exception;
}

