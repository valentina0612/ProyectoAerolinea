package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;

import java.util.List;

public interface RolUsuarioService {
    RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;

    List<RolUsuarioDTO> obtenerRolesUsuario();
}

