package co.edu.usbcali.aerolinea.services;
import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.mapper.ReservaMapper;
import co.edu.usbcali.aerolinea.model.Reserva;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaImpl implements ReservaService{

    private final ReservaRepository reservaRepository;


    public ReservaImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception {
        if (reservaDTO == null) {
            throw new Exception("La reserva viene con datos nulos");
        }
        if (reservaDTO.getReseId()==null){
            throw new Exception("El ID de la reserva no puede ser nulo");
        }
        if(reservaDTO.getReseId()<0){
            throw new Exception("El ID de la reserva no puede ser negativo");
        }
        if (reservaDTO.getVuelId()==null){
            throw new Exception("El ID del vuelo no puede ser nulo");
        }
        if(reservaDTO.getVuelId()<0){
            throw new Exception("El ID del vuelo no puede ser negativo");
        }
        if (reservaDTO.getAsieId()==null){
            throw new Exception("El ID del asiento no puede ser nulo");
        }
        if(reservaDTO.getAsieId()<0){
            throw new Exception("El ID del asiento no puede ser negativo");
        }
        if (reservaDTO.getUsuaId()==null){
            throw new Exception("El ID del usuario no puede ser nulo");
        }
        if(reservaDTO.getUsuaId()<0){
            throw new Exception("El ID del usuario no puede ser negativo");
        }
        Reserva reserva = ReservaMapper.dtoToModel(reservaDTO);
        return ReservaMapper.modelToDto(reservaRepository.save(reserva));

    }

    @Override
    public List<ReservaDTO> obtenerReserva() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.modelToDtoList(reservas);
    }
}
