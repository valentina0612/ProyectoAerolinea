package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolUsuarioServiceImpl implements RolUsuarioService {
    private final RolUsuarioRepository rolUsuarioRepository;

    public RolUsuarioServiceImpl(RolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    @Override
    public RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        validadaciones(rolUsuarioDTO, true);
        return crearOModificar(rolUsuarioDTO);
    }

    @Override
    public RolUsuarioDTO modificarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        validadaciones(rolUsuarioDTO, false);
        return crearOModificar(rolUsuarioDTO);
    }


    @Override
    public List<RolUsuarioDTO> obtenerRolesUsuario() {
        List<RolUsuario> rolesUsuario = rolUsuarioRepository.findAll();
        return RolUsuarioMapper.modelToDtoList(rolesUsuario);
    }

    @Override
    public RolUsuarioDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !rolUsuarioRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el rol usuario con Id " + id + ".");
        }
        return RolUsuarioMapper.modelToDto(rolUsuarioRepository.getReferenceById(id));
    }

    @Override
    public RolUsuarioDTO eliminarRolUsuario(Integer id) throws Exception {
        RolUsuarioDTO rolUsuarioEliminado = buscarPorId(id);
        rolUsuarioEliminado.setDescripcion("Inactivo");
        return modificarRolUsuario(rolUsuarioEliminado);
    }

    @Override
    public List<RolUsuarioDTO> obtenerRolesActivos() {
        List<RolUsuario> rolesUsuario = rolUsuarioRepository.findByEstado("Activo");
        return RolUsuarioMapper.modelToDtoList(rolesUsuario);
    }

    private void validadaciones (RolUsuarioDTO rolUsuarioDTO, boolean esCreacion) throws Exception {
        ValidationUtility.isNull(rolUsuarioDTO, "No han llegado los datos del rol usuario.");

        ValidationUtility.integerIsNullOrZero(rolUsuarioDTO.getRousId(), "El id del rol de usuario es obligatorio.");

        //Validar si es creación o actualización
        if (esCreacion) {
            if(rolUsuarioRepository.existsById(rolUsuarioDTO.getRousId())) {
                throw new Exception("El rol de usuario con Id " +
                        rolUsuarioDTO.getRousId() + " ya se encuentra registrado.");
            }
            if (rolUsuarioRepository.existsRolUsuarioByDescripcion(rolUsuarioDTO.getDescripcion())) {
                throw new Exception("El rol de usuario " + rolUsuarioDTO.getRousId() + " ya está registrado.");
            }
        }
        if (!esCreacion) {
            if (!rolUsuarioRepository.existsById(rolUsuarioDTO.getRousId())) {
                throw new Exception("No se ha encontrado el rol de usuario con Id " +
                        rolUsuarioDTO.getRousId() + ".");
            }
            if (rolUsuarioRepository.existsRolUsuarioByDescripcionAndAndRousIdIsNot(rolUsuarioDTO.getDescripcion(), rolUsuarioDTO.getRousId())) {
                throw new Exception("El rol de usuario " + rolUsuarioDTO.getDescripcion() + " ya está registrado.");
            }
        }

        ValidationUtility.stringIsNullOrBlank(rolUsuarioDTO.getDescripcion(), "La descriçión del rol usuario es obligatorio.");
        ValidationUtility.stringIsNullOrBlank(rolUsuarioDTO.getEstado(), "El estado es obligatorio.");
    }

    private RolUsuarioDTO crearOModificar(RolUsuarioDTO rolUsuarioDTO) {
        RolUsuario rolUsuario = RolUsuarioMapper.dtoToModel(rolUsuarioDTO);
        return RolUsuarioMapper.modelToDto(rolUsuarioRepository.save(rolUsuario));
    }
}

