package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.*;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import co.edu.usbcali.aerolinea.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        return new ResponseEntity(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.guardarUsuario(usuarioDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(usuarioService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/login/{correo}/{cedula}")
    public ResponseEntity<UsuarioDTO> login(@PathVariable String correo, String cedula) throws Exception {
        try {
            return new ResponseEntity(usuarioService.login(correo,cedula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/modificarUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modificarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.modificarUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/UsuariosActivos")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosActivos() {
        return new ResponseEntity(usuarioService.obtenerUsuariosActivos(), HttpStatus.OK);
    }
    @PutMapping(value = "/eliminarUsuari/{idUsuario}")
    public ResponseEntity eliminarAeropuerto(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity(usuarioService.eliminarUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

