package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.model.Trayecto;

import java.util.List;
import java.util.stream.Collectors;

public class TrayectoMapper {
    public static TrayectoDTO modelToDto(Trayecto trayecto){
        return TrayectoDTO.builder()
                .trayId(trayecto.getTrayId())
                .avioId(trayecto.getAvion()!= null?
                        trayecto.getAvion().getAvioID():null)
                .aereoIdOrigen(trayecto.getAeropuerto() != null?
                               trayecto.getAeropuerto().getAeroId():null)
                .aereoIdDestino((trayecto.getAeropuerto2()!=null?
                                 trayecto.getAeropuerto2().getAeroId():null))
                .horaSalida(trayecto.getHoraSalida())
                .horaLlegada(trayecto.getHoraLlegada())
                .vuelId(trayecto.getVuelo()!=null?
                        trayecto.getVuelo().getVueloId():null)
                .estado(trayecto.getEstado())
                .build();

    }
    public static Trayecto dtoToModel (TrayectoDTO trayectoDTO){
        return Trayecto.builder()
                //.trayId(trayectoDTO.getTrayId())
                .horaSalida(trayectoDTO.getHoraSalida())
                .horaLlegada(trayectoDTO.getHoraLlegada())
                .estado(trayectoDTO.getEstado())
                .build();
    }
    public static List<TrayectoDTO> modelToDtoList(List<Trayecto> trayecto){
        return trayecto.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }
    public static List<Trayecto> dtoToModelList(List<TrayectoDTO> trayectoDTOS){
        return trayectoDTOS.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }

}
