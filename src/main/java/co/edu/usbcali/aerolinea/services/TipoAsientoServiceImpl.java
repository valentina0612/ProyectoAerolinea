package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAsientoServiceImpl implements TipoAsientoService{

    private final TipoAsientoRepository tipoAsientoRepository;

    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoRepository) {
        this.tipoAsientoRepository = tipoAsientoRepository;
    }

    @Override
    public TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
      validadaciones(tipoAsientoDTO, true);
      return crearOModificar(tipoAsientoDTO);
    }

    @Override
    public TipoAsientoDTO modificarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        validadaciones(tipoAsientoDTO, false);
        return crearOModificar(tipoAsientoDTO);
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsiento() {
        return TipoAsientoMapper.modelToDtoList(tipoAsientoRepository.findAll());
    }

    @Override
    public TipoAsientoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !tipoAsientoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el tipo asiento con Id " + id + ".");
        }
        return TipoAsientoMapper.modelToDto(tipoAsientoRepository.getReferenceById(id));
    }

    @Override
    public TipoAsientoDTO eliminarTipoAsiento(Integer id) throws Exception {
        TipoAsientoDTO tipoAsientoEliminado = buscarPorId(id);
        tipoAsientoEliminado.setEstado("Inactivo");
        return crearOModificar(tipoAsientoEliminado);
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientosActivos() {
        return TipoAsientoMapper.modelToDtoList(tipoAsientoRepository.findByEstado("Activo"));
    }

    private void validadaciones (TipoAsientoDTO tipoAsientoDTO, boolean esCreacion) throws Exception {
        ValidationUtility.isNull(tipoAsientoDTO, "No han llegado los datos del tipo de asiento.");

        ValidationUtility.integerIsNullOrZero(tipoAsientoDTO.getTiasId(), "El id del tipo de asiento es obligatorio.");

        //Validar si es creación o actualización
        if (esCreacion) {
            if(tipoAsientoRepository.existsById(tipoAsientoDTO.getTiasId())) {
                throw new Exception("El tipo de asiento con Id " +
                        tipoAsientoDTO.getDescripcion() + " ya se encuentra registrado.");
            }
            if (tipoAsientoRepository.existsTipoAsientoByDescripcion(tipoAsientoDTO.getDescripcion())) {
                throw new Exception("El tipo de asiento " + tipoAsientoDTO.getDescripcion()+ " ya está registrado.");
            }
        }
        if (!esCreacion) {
            if (!tipoAsientoRepository.existsById(tipoAsientoDTO.getTiasId())) {
                throw new Exception("No se ha encontrado el tipo de asiento con Id " +
                        tipoAsientoDTO.getDescripcion() + ".");
            }
            if (tipoAsientoRepository.existsTipoAsientoByDescripcionAndTiasIdIsNot(tipoAsientoDTO.getDescripcion(), tipoAsientoDTO.getTiasId())) {
                throw new Exception("El tipo de asiento " + tipoAsientoDTO.getDescripcion() + " ya está registrado.");
            }
        }

        ValidationUtility.stringIsNullOrBlank(tipoAsientoDTO.getDescripcion(), "La descriçión del rol usuario es obligatorio.");
        ValidationUtility.stringIsNullOrBlank(tipoAsientoDTO.getEstado(), "El estado es obligatorio.");
    }

    private TipoAsientoDTO crearOModificar(TipoAsientoDTO tipoAsientoDTO) {
        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToModel(tipoAsientoDTO);
        return TipoAsientoMapper.modelToDto(tipoAsientoRepository.save(tipoAsiento));
    }

}
