package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDTO modelToDto(Usuario usuario){
        return UsuarioDTO.builder()
                .usuaId(usuario.getUsuaId())
                .rolUsuario_rousid( usuario.getRolUsuario()!= null?
                        usuario.getRolUsuario().getRousId():null)
                .cedula(usuario.getCedula())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado(usuario.getEstado())
                .build();

    }
    public static Usuario dtoToModel (UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .usuaId(usuarioDTO.getUsuaId())
                .cedula(usuarioDTO.getCedula())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .correo(usuarioDTO.getCorreo())
                .estado(usuarioDTO.getEstado())
                .build();
    }

    public static List<UsuarioDTO> modelToDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Usuario> dtoToModelList(List<UsuarioDTO> usuariosDTO){
        return usuariosDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
