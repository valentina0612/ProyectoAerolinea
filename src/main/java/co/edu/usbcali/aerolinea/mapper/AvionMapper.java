package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.model.Avion;

import java.util.List;
import java.util.stream.Collectors;

public class AvionMapper {
    public static AvionDTO modelToDto(Avion avion){
        return AvionDTO.builder()
                .avioID(avion.getAvioID())
                .modelo(avion.getModelo())
                .estado(avion.getEstado())
                .build();

    }
    public static Avion dtoToModel (AvionDTO avionDTO){
        return Avion.builder()
                .avioID(avionDTO.getAvioID())
                .modelo(avionDTO.getModelo())
                .estado(avionDTO.getEstado())
                .build();
    }

    public static List<AvionDTO> modelToDtoList(List<Avion> Aviones){
        return Aviones.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Avion> dtoToModelList(List<AvionDTO> AvionesDTO){
        return AvionesDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
