package co.edu.usbcali.aerolinea.mapper;
import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.model.Reserva;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaMapper {
    public static ReservaDTO modelToDto(Reserva reserva){
        return ReservaDTO.builder()
                .reseId(reserva.getReseId())
                .vuelId(reserva.getVuelo()!= null?
                        reserva.getVuelo().getVueloId():null)
                .asieId(reserva.getAsiento()!= null?
                        reserva.getAsiento().getAsieId():null)
                .usuaId(reserva.getUsuario()!= null?
                        reserva.getUsuario().getUsuaId():null)
                .precioTotal(reserva.getPrecioTotal())
                .estadoPago(reserva.getEstadoPago())
                .fecha(reserva.getFecha())
                .estado(reserva.getEstado())
                .build();

    }
    public static Reserva dtoToModel (ReservaDTO reservaDTO){
        return Reserva.builder()
                //.reseId(reservaDTO.getReseId())
                .precioTotal(reservaDTO.getPrecioTotal())
                .estadoPago(reservaDTO.getEstadoPago())
                .fecha(reservaDTO.getFecha())
                .estado(reservaDTO.getEstado())
                .build();
    }
    public static List<ReservaDTO> modelToDtoList(List<Reserva> reserva){
        return reserva.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Reserva> dtoToModelList(List<ReservaDTO> reservaDTOS){
        return reservaDTOS.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }


}
