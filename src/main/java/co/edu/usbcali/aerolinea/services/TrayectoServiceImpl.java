package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TrayectoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class TrayectoServiceImpl implements TrayectoService{
    private final TrayectoRepository trayectoRepository;
    private final AvionRepository avionRepository;
    private final AeropuertoRepository aeropuertoRepository;
    private final VueloRepository vueloRepository;
    public TrayectoServiceImpl(TrayectoRepository trayectoRepository, AvionRepository avionRepository, AeropuertoRepository aeropuertoRepository, VueloRepository vueloRepository) {
        this.trayectoRepository = trayectoRepository;
        this.avionRepository = avionRepository;
        this.aeropuertoRepository = aeropuertoRepository;
        this.vueloRepository = vueloRepository;
    }

    @Override
    public TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        validar(trayectoDTO, true);
        return crearOModificar(trayectoDTO);

    }

    @Override
    public TrayectoDTO modificarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        validar(trayectoDTO, false);
        return crearOModificar(trayectoDTO);
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectos() {
        List<Trayecto>trayectos=trayectoRepository.findAll();
        return TrayectoMapper.modelToDtoList(trayectos);
    }

    @Override
    public TrayectoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !trayectoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el trayecto con Id " + id + ".");
        }
        return TrayectoMapper.modelToDto(trayectoRepository.getReferenceById(id));
    }

    @Override
    public TrayectoDTO eliminarTrayecto(Integer id) throws Exception {
        TrayectoDTO trayectoEliminado = buscarPorId(id);
        trayectoEliminado.setEstado("Inactivo");
        return crearOModificar(trayectoEliminado);
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectosActivos() {
        return TrayectoMapper.modelToDtoList(trayectoRepository.findByEstado("Activo"));
    }

    private void validar(TrayectoDTO trayectoDTO, boolean esCreacion) throws Exception {
        if (trayectoDTO == null) throw new Exception("No han llegado los datos del trayecto.");

        if (trayectoDTO.getTrayId() == null) throw new Exception("El id del trayecto es obligatorio.");

        if (esCreacion) {
            if(trayectoRepository.existsById(trayectoDTO.getTrayId())) {
                throw new Exception("El trayecto con Id " +
                        trayectoDTO.getTrayId() + " ya se encuentra registrado.");
            }

        }
        if (!esCreacion) {
            if (!trayectoRepository.existsById(trayectoDTO.getTrayId())) {
                throw new Exception("No se ha encontrado el trayecto con Id " +
                        trayectoDTO.getTrayId() + ".");
            }
        }

        if (!aeropuertoRepository.existsById(trayectoDTO.getAereoIdOrigen())) {
            throw new Exception("El ID del origen del vuelo " + trayectoDTO.getAereoIdOrigen()
                    + " no se encuentra en base de datos");
        }

        if (!aeropuertoRepository.existsById(trayectoDTO.getAereoIdDestino())) {
            throw new Exception("El ID del destino del vuelo " + trayectoDTO.getAereoIdDestino()
                    + " no se encuentra en base de datos");
        }

        if (!avionRepository.existsById(trayectoDTO.getAvioId())) {
            throw new Exception("El ID del avion " + trayectoDTO.getAvioId()
                    + " no se encuentra en base de datos");
        }

        if (!vueloRepository.existsById(trayectoDTO.getVuelId())) {
            throw new Exception("El ID del vuelo" + trayectoDTO.getVuelId()
                    + " no se encuentra en base de datos");
        }
        ValidationUtility.stringIsNullOrBlank(trayectoDTO.getEstado(), "El estado no puede ser nulo");

    }
    private TrayectoDTO crearOModificar(TrayectoDTO trayectoDTO) {
        Avion avion = avionRepository.getReferenceById(trayectoDTO.getAvioId());
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(trayectoDTO.getAereoIdOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(trayectoDTO.getAereoIdDestino());
        Vuelo vuelo = vueloRepository.getReferenceById(trayectoDTO.getVuelId());
        Trayecto trayecto= TrayectoMapper.dtoToModel(trayectoDTO);
        trayecto.setAvion(avion);
        trayecto.setAeropuerto(aeropuertoOrigen);
        trayecto.setAeropuerto2(aeropuertoDestino);
        trayecto.setVuelo(vuelo);
        return TrayectoMapper.modelToDto(trayectoRepository.save(trayecto));
    }

}
