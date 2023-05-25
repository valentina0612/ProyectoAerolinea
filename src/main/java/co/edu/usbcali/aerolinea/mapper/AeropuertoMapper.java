package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;

import java.util.List;
import java.util.stream.Collectors;

public class AeropuertoMapper {

    public static AeropuertoDTO modelToDto (Aeropuerto aeropuerto){
    return AeropuertoDTO.builder().
            aeroId(aeropuerto.getAeroId()).
            nombre(aeropuerto.getNombre()).
            iata(aeropuerto.getIata()).
            ubicacion(aeropuerto.getUbicacion()).
            estado(aeropuerto.getEstado())
            .build();
    }

    public static Aeropuerto dtoToModel (AeropuertoDTO aeropuertoDto){
        return Aeropuerto.builder()
                //.aeroId(aeropuertoDto.getAeroId())
                .nombre(aeropuertoDto.getNombre())
                .iata(aeropuertoDto.getIata())
                .ubicacion(aeropuertoDto.getUbicacion())
                .estado(aeropuertoDto.getEstado())
                .build();
    }

    public static List<AeropuertoDTO> modelToDtoList (List<Aeropuerto> aeropuertos){
        return aeropuertos.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Aeropuerto> dtoToModelList(List<AeropuertoDTO> aeropuertoDTOS){
        return aeropuertoDTOS.stream().map(td ->dtoToModel(td)).collect(Collectors.toList());
    }
}
