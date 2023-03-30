package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    List<UsuarioDTO> obtenerUsuarios();
}
