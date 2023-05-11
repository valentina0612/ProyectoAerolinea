package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        Validadaciones(aeropuertoDTO, true);
        return crearOModificar(aeropuertoDTO);
    }

    @Override
    public AeropuertoDTO modificarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        Validadaciones(aeropuertoDTO, false);
        return crearOModificar(aeropuertoDTO);
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        return AeropuertoMapper.modelToDtoList(aeropuertos);
    }

    @Override
    public AeropuertoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !aeropuertoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el aeropuerto con Id " + id + ".");
        }
        return AeropuertoMapper.modelToDto(aeropuertoRepository.getReferenceById(id));
    }
    private void Validadaciones (AeropuertoDTO aeropuertoDTO, boolean esCreacion) throws Exception {
        //Validar clienteDTO
        ValidationUtility.isNull(aeropuertoDTO, "No han llegado los datos del aeropuerto.");

        //Validar id cliente
        ValidationUtility.integerIsNullOrZero(aeropuertoDTO.getAeroId(), "El id del aeropuesto es obligatorio.");

        //Validar si es creación o actualización
        if (esCreacion) {
            if(aeropuertoRepository.existsById(aeropuertoDTO.getAeroId())) {
                throw new Exception("El cliente con Id " +
                        aeropuertoDTO.getAeroId() + " ya se encuentra registrado.");
            }
            if (aeropuertoRepository.existsAeropuertoByNombre(aeropuertoDTO.getNombre())) {
                throw new Exception("El nombre " + aeropuertoDTO.getNombre() + " ya está registrado.");
            }
        }
        if (!esCreacion) {
            if (!aeropuertoRepository.existsById(aeropuertoDTO.getAeroId())) {
                throw new Exception("No se ha encontrado el cliente con Id " +
                        aeropuertoDTO.getAeroId() + ".");
            }
            if (aeropuertoRepository.existsClienteByNombreAndIdIsNot(aeropuertoDTO.getNombre(), aeropuertoDTO.getAeroId())) {
                throw new Exception("El nombre " + aeropuertoDTO.getNombre() + " ya está registrado para otro aeropuerto.");
            }
        }

        ValidationUtility.stringIsNullOrBlank(aeropuertoDTO.getNombre(), "El nombre del aeropuerto es obligatorio.");

        ValidationUtility.stringIsNullOrBlank(aeropuertoDTO.getUbicacion(), "La ubicación del aeropuerto es obligatoria.");

        ValidationUtility.stringIsNullOrBlank(aeropuertoDTO.getIata(), "El IATA es obligatorio.");
        ValidationUtility.stringIsNullOrBlank(aeropuertoDTO.getEstado(), "El estado es obligatorio.");
    }

    private AeropuertoDTO crearOModificar(AeropuertoDTO aeropuertoDTO) {
        // Mapeo el cliente hacia Domain/Modelo/Entity
        Aeropuerto aeropuerto = AeropuertoMapper.dtoToModel(aeropuertoDTO);

        return AeropuertoMapper.modelToDto(aeropuertoRepository.save(aeropuerto));
    }

}
