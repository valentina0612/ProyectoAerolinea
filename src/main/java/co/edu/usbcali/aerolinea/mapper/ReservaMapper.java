package co.edu.usbcali.aerolinea.mapper;
import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.model.Reserva;
import java.util.List;
import java.util.stream.Collectors;
public class ReservaMapper {
    public static ReservaDTO modelToDto(Reserva reserva){
        return ReservaDTO.builder()
                .reseId(reserva.getReseId())
                .vuelId(reserva.getVuelId())
                .asieId(reserva.getAsieId())
                .usuaId(reserva.getUsuaId())
                .precioTotal(reserva.getPrecioTotal())
                .estadoPago(reserva.getEstadoPago())
                .fecha(reserva.getFecha())
                .estado(reserva.getEstado())
                .build();

    }
    public static Reserva dtoToModel (ReservaDTO reservaDTO){
        return Reserva.builder()
                .reseId(reservaDTO.getReseId())
                .vuelId(reservaDTO.getVuelId())
                .asieId(reservaDTO.getAsieId())
                .usuaId(reservaDTO.getUsuaId())
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
