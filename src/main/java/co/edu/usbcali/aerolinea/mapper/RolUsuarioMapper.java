package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.model.RolUsuario;

import java.util.List;
import java.util.stream.Collectors;

public class RolUsuarioMapper {
    public static RolUsuarioDTO modelToDto(RolUsuario rolUsuario){
        return RolUsuarioDTO.builder()
                .rousId(rolUsuario.getRousId())
                .descripcion(rolUsuario.getDescripcion())
                .estado(rolUsuario.getEstado())
                .build();

    }
    public static RolUsuario dtoToModel (RolUsuarioDTO rolUsuarioDTO){
        return RolUsuario.builder()
                .rousId(rolUsuarioDTO.getRousId())
                .descripcion(rolUsuarioDTO.getDescripcion())
                .estado(rolUsuarioDTO.getEstado())
                .build();
    }

    public static List<RolUsuarioDTO> modelToDtoList(List<RolUsuario> rolesUsuario){
    return rolesUsuario.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<RolUsuario> dtoToModelList(List<RolUsuarioDTO> rolesUsuarioDTO){
        return rolesUsuarioDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
