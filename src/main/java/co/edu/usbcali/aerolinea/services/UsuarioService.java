package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) throws Exception;

    List<UsuarioDTO> obtenerUsuarios();

    UsuarioDTO buscarPorId(Integer id) throws Exception;

    UsuarioDTO eliminarUsuario(Integer id) throws Exception;

    List<UsuarioDTO> obtenerUsuariosActivos();

    UsuarioDTO login(String correo, String cedula) throws Exception;
}
