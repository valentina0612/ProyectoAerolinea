package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolUsuarioImpl implements RolUsuarioService {
    private final RolUsuarioRepository rolUsuarioRepository;

    public RolUsuarioImpl(RolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    @Override
    public RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        if (rolUsuarioDTO == null){
            throw new Exception("El rol de usuario viene con datos nulos");
        }
        if (rolUsuarioDTO.getRousId() == null || rolUsuarioDTO.getRousId()==null){
            throw new Exception("El ID no puede ser nulo");
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

}

