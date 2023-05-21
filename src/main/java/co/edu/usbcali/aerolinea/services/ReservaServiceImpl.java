package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.mapper.FacturaMapper;
import co.edu.usbcali.aerolinea.mapper.ReservaMapper;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.AsientoRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;

import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        validar(reservaDTO, true);
        return crearOModificar(reservaDTO);
    }
    @Override
    public List<ReservaDTO> obtenerReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.modelToDtoList(reservas);
    }

    @Override
    public ReservaDTO modificarReserva(ReservaDTO reservaDTO) throws Exception {
        validar(reservaDTO, false);
        return crearOModificar(reservaDTO);
    }

    @Override
    public ReservaDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !reservaRepository.existsById(id)) {
            throw new Exception("No se ha encontrado la reserva con Id " + id + ".");
        }
        return ReservaMapper.modelToDto(reservaRepository.getReferenceById(id));
    }

    @Override
    public List<ReservaDTO> obtenerReservasVuelo(Integer idVuelo) throws Exception {
        Vuelo vuelo = vueloRepository.findById(idVuelo)
                .orElseThrow(() -> new Exception("No se ha encontrado ese vuelo"));

        List<Reserva> reservas = reservaRepository.findAllByEstadoAndVuelo("Activo",vuelo);
        return ReservaMapper.modelToDtoList(reservas);
    }


    @Override
    public List<ReservaDTO> obtenerReservasUsuario(Integer idUsuario) throws  Exception{
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("No se ha encontrado ese usuario"));
        List<Reserva> reservas = reservaRepository.findAllByUsuario(usuario);
        return ReservaMapper.modelToDtoList(reservas);
    }

    @Override
    public List<ReservaDTO> obtenerReservasActivas() {
        return ReservaMapper.modelToDtoList(reservaRepository.findByEstado("Activo"));
    }

    @Override
    public ReservaDTO eliminarReserva(Integer id) throws Exception {
        ReservaDTO reservaEliminada = buscarPorId(id);
        reservaEliminada.setEstado("Inactivo");
        return  crearOModificar(reservaEliminada);
    }

    private void validar(ReservaDTO reservaDTO, boolean esCreacion) throws Exception {
        if (reservaDTO == null) throw new Exception("No han llegado los datos de la reserva");

        if (reservaDTO.getReseId() == null) throw new Exception("El ID de la reserva es obligatorio.");

        if(reservaDTO.getFecha().after(new Date())) throw new Exception("Esa fecha ya pasó.");

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

        // Validar si el tipo de documento consultado no existe
        if (!vueloRepository.existsById(reservaDTO.getVuelId())) {
            throw new Exception("El ID del vuelo " + reservaDTO.getVuelId()
                    + " no se encuentra en base de datos");
        }

        if (!asientoRepository.existsById(reservaDTO.getAsieId())) {
            throw new Exception("El ID del asiento " + reservaDTO.getAsieId()
                    + " no se encuentra en base de datos");
        }

        if (!usuarioRepository.existsById(reservaDTO.getUsuaId())) {
            throw new Exception("El ID del usuario " + reservaDTO.getUsuaId()
                    + " no se encuentra en base de datos");
        }
        ValidationUtility.stringIsNullOrBlank(reservaDTO.getEstado(), "El estado no debe ser nulo");
        ValidationUtility.stringIsNullOrBlank(reservaDTO.getEstadoPago(), "El estado de pago no debe ser nulo");
        ValidationUtility.integerIsNullOrLessZero(reservaDTO.getPrecioTotal(), "El precio total debe ser mayor a 0");
    }
    private ReservaDTO crearOModificar(ReservaDTO reservaDTO) {
        Vuelo vuelo = vueloRepository.getReferenceById(reservaDTO.getVuelId());
        Asiento asiento = asientoRepository.getReferenceById(reservaDTO.getAsieId());
        Usuario usuario = usuarioRepository.getReferenceById(reservaDTO.getUsuaId());
        Reserva reserva = ReservaMapper.dtoToModel(reservaDTO);
        reserva.setVuelo(vuelo);
        reserva.setAsiento(asiento);
        reserva.setUsuario(usuario);
        return ReservaMapper.modelToDto(reservaRepository.save(reserva));
    }
}
