package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
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
        if (rolUsuarioDTO == null){
            throw new Exception("El rol de usuario viene con datos nulos");
        }
        if (rolUsuarioDTO.getRousId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (rolUsuarioDTO.getRousId()<0){
            throw new Exception("El id no puede ser negativo");
        }
        if (rolUsuarioDTO.getDescripcion()== null || rolUsuarioDTO.getDescripcion().trim().equals("")){
            throw new Exception("La descripción no puede estar vacia");
        }
        if(rolUsuarioRepository.findById(rolUsuarioDTO.getRousId()).isPresent()){
            throw new Exception("El ID no puede repetirse");
        }
        RolUsuario rolUsuario = RolUsuarioMapper.dtoToModel(rolUsuarioDTO);
        return RolUsuarioMapper.modelToDto(rolUsuarioRepository.save(rolUsuario));
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
}

