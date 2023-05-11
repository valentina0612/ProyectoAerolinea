package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.AsientoMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AsientoServiceImpl implements AsientoService{
    private final AsientoRepository asientoRepository;
    private final TipoAsientoRepository tipoAsientoRepository;
    private final AvionRepository avionRepository;


    public AsientoServiceImpl(AsientoRepository asientoRepository, TipoAsientoRepository tipoAsientoRepository, AvionRepository avionRepository) {
        this.asientoRepository = asientoRepository;
        this.tipoAsientoRepository = tipoAsientoRepository;
        this.avionRepository = avionRepository;
    }

    @Override
    public AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception {
        validar(asientoDTO,true);
        return crearOModificar(asientoDTO);
    }

    @Override
    public AsientoDTO modificarAsiento(AsientoDTO asientoDTO) throws Exception {
        validar(asientoDTO,false);
        return crearOModificar(asientoDTO);
    }

    @Override
    public List<AsientoDTO> obtenerAsientos() {
        List<Asiento> asientos = asientoRepository.findAll();
        return AsientoMapper.modelToDtoList(asientos);
    }

    @Override
    public AsientoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !asientoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el asiento con Id " + id + ".");
        }
        return AsientoMapper.modelToDto(asientoRepository.getReferenceById(id));
    }

    //Este método se usará cuando se implemente el método put
    private void validar(AsientoDTO asientoDTO, boolean esCreacion) throws Exception {
        if (asientoDTO == null) throw new Exception("No han llegado los datos del asiento.");

        if (asientoDTO.getAsieId() == null) throw new Exception("El id del asiento es obligatorio.");

        if (esCreacion) {
            if(asientoRepository.existsById(asientoDTO.getAsieId())) {
                throw new Exception("El asiento con Id " +
                        asientoDTO.getAsieId() + " ya se encuentra registrado.");
            }
        }
        if (!esCreacion) {
            if (!asientoRepository.existsById(asientoDTO.getAsieId())) {
                throw new Exception("No se ha encontrado el asiento con Id " +
                        asientoDTO.getAsieId() + ".");
            }
        }


        if (!tipoAsientoRepository.existsById(asientoDTO.getTipoAsiento_tiasId())) {
            throw new Exception("El ID de tipo de asiento " + asientoDTO.getTipoAsiento_tiasId()
                    + " no se encuentra en base de datos");
        }

        if (!avionRepository.existsById(asientoDTO.getAvion_avioId())) {
            throw new Exception("El ID del avion " + asientoDTO.getAvion_avioId()
                    + " no se encuentra en base de datos");
        }
        ValidationUtility.stringIsNullOrBlank(asientoDTO.getUbicacion(), "La ubicación es obligatorio.");
        ValidationUtility.integerIsNullOrLessZero(asientoDTO.getPrecio(), "El precio debe ser mayor a 0");
        ValidationUtility.integerIsNullOrLessZero(asientoDTO.getTipoAsiento_tiasId(), "El ID del tipo de asiento debe ser un número positivo");
        ValidationUtility.integerIsNullOrLessZero(asientoDTO.getAvion_avioId(), "El ID del avión debe ser positivo");
        ValidationUtility.stringIsNullOrBlank(asientoDTO.getEstado(), "El estado es obligatorio.");
    }
    private AsientoDTO crearOModificar(AsientoDTO asientoDTO) {
        // Mapeo el cliente hacia Domain/Modelo/Entity
        Asiento asiento=AsientoMapper.dtoToModel(asientoDTO);

        TipoAsiento tipoAsiento = tipoAsientoRepository.getReferenceById(asientoDTO.getTipoAsiento_tiasId());

        Avion avion = avionRepository.getReferenceById(asientoDTO.getAvion_avioId());

        asiento.setTipoAsiento(tipoAsiento);
        asiento.setAvion(avion);

        return AsientoMapper.modelToDto(asientoRepository.save(asiento));
    }
}
