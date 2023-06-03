package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.*;
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
       validar(usuarioDTO, true);
       return crearOModificar(usuarioDTO);

    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        validar(usuarioDTO, false);
        return crearOModificar(usuarioDTO);
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.modelToDtoList(usuarios);
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !usuarioRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el usuario con Id " + id + ".");
        }
        return UsuarioMapper.modelToDto(usuarioRepository.getReferenceById(id));
    }

    @Override
    public UsuarioDTO eliminarUsuario(Integer id) throws Exception {
        UsuarioDTO usuarioEliminado = buscarPorId(id);
        usuarioEliminado.setEstado("Inactivo");
        return crearOModificar(usuarioEliminado);
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosActivos() {
        return UsuarioMapper.modelToDtoList(usuarioRepository.findByEstado("Activo"));
    }

    @Override
    public UsuarioDTO login(String correo, String cedula) throws Exception {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new Exception("El usuario con correo " + correo + " no se encuentra registrado.");
        }

        return UsuarioMapper.modelToDto(usuario);
    }


    private void validar(UsuarioDTO usuarioDTO, boolean esCreacion) throws Exception {
        if (usuarioDTO == null) throw new Exception("No han llegado los datos del usuario.");

        //if (usuarioDTO.getUsuaId() == null) throw new Exception("El id del usuario es obligatorio.");

        if (StringUtils.isBlank(usuarioDTO.getCorreo()) ||
                !Pattern.matches(ConstantesUtility.PATTERN_MAIL_REGEX, usuarioDTO.getCorreo())) {
            throw new Exception("El correo electrónico no es válido.");
        }

        if (esCreacion) {
            /*
            if(usuarioRepository.existsById(usuarioDTO.getUsuaId())) {
                throw new Exception("El usuario con Id " +
                        usuarioDTO.getUsuaId() + " ya se encuentra registrado.");
            }

             */
            if (usuarioRepository.existsByCorreo(usuarioDTO.getCorreo())) {
                throw new Exception("El correo electrónico " + usuarioDTO.getCorreo() + " ya está registrado para otro usuario.");
            }
            if (usuarioRepository.existsByCedula(usuarioDTO.getCedula())) {
                throw new Exception("La cédula " + usuarioDTO.getCedula() + " ya está registrado para otro usuario.");
            }

        }
        if (!esCreacion) {
            if (!usuarioRepository.existsById(usuarioDTO.getUsuaId())) {
                throw new Exception("No se ha encontrado el usuario con Id " +
                        usuarioDTO.getUsuaId() + ".");
            }
            if (usuarioRepository.existsByCorreoAndUsuaIdIsNot(usuarioDTO.getCorreo(), usuarioDTO.getUsuaId())) {
                throw new Exception("El correo electrónico " + usuarioDTO.getCorreo() + " ya está registrado para otro usuario.");
            }
        }

        // Validar si el tipo de documento consultado no existe
        if (!rolUsuarioRepository.existsById(usuarioDTO.getRolUsuario_rousid())) {
            throw new Exception("El ID del rol del usuario " + usuarioDTO.getRolUsuario_rousid()
                    + " no se encuentra en base de datos");
        }

        if (StringUtils.isBlank(usuarioDTO.getNombre())) throw new Exception("El nombre del usuario es obligatorio.");
        if (StringUtils.isBlank(usuarioDTO.getApellido())) throw new Exception("El apellido del usuario es obligatorio.");

    }
    private UsuarioDTO crearOModificar(UsuarioDTO usuarioDTO) {
        RolUsuario rolUsuario = rolUsuarioRepository.getReferenceById(usuarioDTO.getRolUsuario_rousid());
        Usuario usuario = UsuarioMapper.dtoToModel(usuarioDTO);
        usuario.setRolUsuario(rolUsuario);
        return UsuarioMapper.modelToDto(usuarioRepository.save(usuario));
    }
}
