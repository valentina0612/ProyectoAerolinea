package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolUsuarioRepository rolUsuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolUsuarioRepository rolUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolUsuarioRepository = rolUsuarioRepository;
    }
    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null){
            throw new Exception("El usuario viene con datos nulos");
        }
        if (usuarioDTO.getUsuaId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (usuarioDTO.getRolUsuario_rousid()<= 0){
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
        RolUsuario rolUsuario = rolUsuarioRepository.getReferenceById(usuarioDTO.getRolUsuario_rousid());
        Usuario usuario = UsuarioMapper.dtoToModel(usuarioDTO);
        usuario.setRolUsuario(rolUsuario);
        return UsuarioMapper.modelToDto(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.modelToDtoList(usuarios);
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !usuarioRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el cliente con Id " + id + ".");
        }
        return UsuarioMapper.modelToDto(usuarioRepository.getReferenceById(id));
    }

    private void validarClienteDTO(UsuarioDTO usuarioDTO, boolean esCreacion) throws Exception {
        if (usuarioDTO == null) throw new Exception("No han llegado los datos del cliente.");

        if (usuarioDTO.getUsuaId() == null) throw new Exception("El id del cliente es obligatorio.");

        if (StringUtils.isBlank(usuarioDTO.getCorreo()) ||
                !Pattern.matches(ConstantesUtility.PATTERN_MAIL_REGEX, usuarioDTO.getCorreo())) {
            throw new Exception("El correo electrónico no es válido.");
        }

        if (esCreacion) {
            if(usuarioRepository.existsById(usuarioDTO.getUsuaId())) {
                throw new Exception("El cliente con Id " +
                        usuarioDTO.getUsuaId() + " ya se encuentra registrado.");
            }

        }
        if (esCreacion) {
            if(usuarioRepository.existsById(usuarioDTO.getUsuaId())) {
                throw new Exception("El cliente con Id " +
                        usuarioDTO.getUsuaId() + " ya se encuentra registrado.");
            }
            if (usuarioRepository.existsClienteByMail(usuarioDTO.getCorreo())) {
                throw new Exception("El correo electrónico " + usuarioDTO.getCorreo() + " ya está registrado para otro cliente.");
            }
        }
        if (!esCreacion) {
            if (!usuarioRepository.existsById(usuarioDTO.getUsuaId())) {
                throw new Exception("No se ha encontrado el cliente con Id " +
                        usuarioDTO.getUsuaId() + ".");
            }
            if (usuarioRepository.existsClienteByMailAndIdIsNot(usuarioDTO.getCorreo(), usuarioDTO.getUsuaId())) {
                throw new Exception("El correo electrónico " + usuarioDTO.getCorreo() + " ya está registrado para otro cliente.");
            }
        }

        if (usuarioDTO.getRolUsuario_rousid() == null || usuarioDTO.getRolUsuario_rousid() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }

        // Validar si el tipo de documento consultado no existe
        if (!usuarioRepository.existsById(usuarioDTO.getRolUsuario_rousid())) {
            throw new Exception("El tipo de documento " + usuarioDTO.getRolUsuario_rousid()
                    + " no se encuentra en base de datos");
        }

        if (StringUtils.isBlank(usuarioDTO.getNombre())) throw new Exception("El nombre del cliente es obligatorio.");

        if (StringUtils.isBlank(usuarioDTO.getApellido())) throw new Exception("El apellido del cliente es obligatorio.");


    }
}
