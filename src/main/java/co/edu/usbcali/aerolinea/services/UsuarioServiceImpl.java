package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null){
            throw new Exception("El usuario viene con datos nulos");
        }
        if (usuarioDTO.getUsuaId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (usuarioDTO.getRolUsuario_rousid()<0){
            throw new Exception("id no válido");
        }
        if (usuarioDTO.getApellido()== null || usuarioDTO.getApellido().trim().equals("")){
            throw new Exception("El apellido no puede estar vacio");
        }
        if (usuarioDTO.getNombre()== null || usuarioDTO.getNombre().trim().equals("")){
            throw new Exception("El nombre no puede estar vacio");
        }
        if (usuarioDTO.getCorreo()== null || usuarioDTO.getCorreo().trim().equals("")){
            throw new Exception("El correo no puede estar vacio");
        }
        if (usuarioDTO.getCedula()== null || usuarioDTO.getCedula().trim().equals("")){
            throw new Exception("La cédula no puede estar vacía");
        }
        if(usuarioRepository.findById(usuarioDTO.getUsuaId()).isPresent()){
            throw new Exception("El ID no puede repetirse");
        }
        Usuario usuario = UsuarioMapper.dtoToModel(usuarioDTO);
        return UsuarioMapper.modelToDto(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.modelToDtoList(usuarios);
    }
}
