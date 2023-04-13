package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.model.TipoAsiento;

import java.util.List;
import java.util.stream.Collectors;


public class TipoAsientoMapper {
    public static TipoAsientoDTO modelToDto(TipoAsiento tipoAsiento){
        return TipoAsientoDTO.builder()
                .tiasId(tipoAsiento.getTiasId())
                .descripcion(tipoAsiento.getDescripcion())
                .estado(tipoAsiento.getEstado())
                .build();

    }
    public static TipoAsiento dtoToModel (TipoAsientoDTO tipoAsientoDTO){
        return TipoAsiento.builder()
                .tiasId(tipoAsientoDTO.getTiasId())
                .descripcion(tipoAsientoDTO.getDescripcion())
                .estado(tipoAsientoDTO.getEstado())
                .build();
    }

    public static List<TipoAsientoDTO> modelToDtoList(List<TipoAsiento> tipoAsientos){
        return tipoAsientos.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<TipoAsiento> dtoToModelList(List<TipoAsientoDTO> tipoAsientoDTOS){
        return tipoAsientoDTOS.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
