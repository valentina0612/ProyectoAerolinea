package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class AsientoMapper {
    public static AsientoDTO modelToDto(Asiento asiento){
        return AsientoDTO.builder()
                .asieId(asiento.getAsieId())
                .tipoAsiento_tiasId(asiento.getTipoAsiento()!= null?
                        asiento.getTipoAsiento().getTiasId():null)
                .avion_avioId(asiento.getAvion()!= null?
                        asiento.getAvion().getAvioID():null)
                .nombreTipoAsiento(asiento.getTipoAsiento()!= null?
                        asiento.getTipoAsiento().getDescripcion():null)
                .ModeloAvion(asiento.getAvion()!= null?
                        asiento.getAvion().getModelo():null)
                .ubicacion(asiento.getUbicacion())
                .precio(asiento.getPrecio())
                .estado(asiento.getEstado())
                .build();
    }
    public static Asiento dtoToModel (AsientoDTO asientoDTO){
        return Asiento.builder()
                .asieId(asientoDTO.getAsieId())
                .ubicacion(asientoDTO.getUbicacion())
                .precio(asientoDTO.getPrecio())
                .estado(asientoDTO.getEstado())
                .build();
    }

    public static List<AsientoDTO> modelToDtoList(List<Asiento> asientos){
        return asientos.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Asiento> dtoToModelList(List<AsientoDTO> AsientosDTO){
        return AsientosDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
