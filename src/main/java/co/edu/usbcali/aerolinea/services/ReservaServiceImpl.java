package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.mapper.ReservaMapper;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.AsientoRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;

import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;
    private final AsientoRepository asientoRepository;
    private final UsuarioRepository usuarioRepository;
    public ReservaServiceImpl(ReservaRepository reservaRepository, VueloRepository vueloRepository, AsientoRepository asientoRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.vueloRepository = vueloRepository;
        this.asientoRepository = asientoRepository;
        this.usuarioRepository = usuarioRepository;
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
        if(reservaRepository.findById(reservaDTO.getReseId()).isPresent()){
            throw new Exception("El ID ya existe");
        }
        Vuelo vuelo = vueloRepository.getReferenceById(reservaDTO.getVuelId());
        Asiento asiento = asientoRepository.getReferenceById(reservaDTO.getAsieId());
        Usuario usuario = usuarioRepository.getReferenceById(reservaDTO.getUsuaId());
        Reserva reserva = ReservaMapper.dtoToModel(reservaDTO);
        reserva.setVuelo(vuelo);
        reserva.setAsiento(asiento);
        reserva.setUsuario(usuario);
        return ReservaMapper.modelToDto(reservaRepository.save(reserva));

    }
    @Override
    public List<ReservaDTO> obtenerReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.modelToDtoList(reservas);
    }

    @Override
    public ReservaDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !reservaRepository.existsById(id)) {
            throw new Exception("No se ha encontrado la reserva con Id " + id + ".");
        }
        return ReservaMapper.modelToDto(reservaRepository.getReferenceById(id));
    }

    private void validarClienteDTO(ReservaDTO reservaDTO, boolean esCreacion) throws Exception {
        if (reservaDTO == null) throw new Exception("No han llegado los datos de la reserva");

        if (reservaDTO.getReseId() == null) throw new Exception("El ID de la reserva es obligatorio.");

        if (esCreacion) {
            if(reservaRepository.existsById(reservaDTO.getReseId())) {
                throw new Exception("La reserva con Id " +
                        reservaDTO.getReseId() + " ya se encuentra registrada.");
            }

        }
        if (!esCreacion) {
            if (!reservaRepository.existsById(reservaDTO.getReseId())) {
                throw new Exception("No se ha encontrado la reserva con Id " +
                        reservaDTO.getReseId() + ".");
            }
        }

        if (reservaDTO.getVuelId() == null || reservaDTO.getVuelId() <= 0) {
            throw new Exception("El ID del vuelo debe ser un número positivo.");
        }

        if (reservaDTO.getUsuaId() == null || reservaDTO.getUsuaId() <= 0) {
            throw new Exception("El ID del usuario debe ser un número positivo.");
        }
        if (reservaDTO.getAsieId() == null || reservaDTO.getAsieId() <= 0) {
            throw new Exception("El ID del asiento debe ser un número positivo.");
        }


        // Validar si el tipo de documento consultado no existe
        if (!reservaRepository.existsById(reservaDTO.getVuelId())) {
            throw new Exception("El ID del vuelo " + reservaDTO.getVuelId()
                    + " no se encuentra en base de datos");
        }

        if (!reservaRepository.existsById(reservaDTO.getAsieId())) {
            throw new Exception("El ID del asiento " + reservaDTO.getAsieId()
                    + " no se encuentra en base de datos");
        }

        if (!reservaRepository.existsById(reservaDTO.getUsuaId())) {
            throw new Exception("El ID del usuario " + reservaDTO.getUsuaId()
                    + " no se encuentra en base de datos");
        }
    }

}
