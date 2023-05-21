package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolusuario")
@CrossOrigin(origins = "*")

public class RolUsuarioController {

    private final RolUsuarioService rolUsuarioService;

    public RolUsuarioController(RolUsuarioService rolUsuarioService) {

        this.rolUsuarioService = rolUsuarioService;
    }

    @GetMapping("/obtenerRoles")
    public ResponseEntity<List<RolUsuarioDTO>> roles(){
        return new ResponseEntity(rolUsuarioService.obtenerRolesUsuario(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarRol",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarRol(@RequestBody RolUsuarioDTO rolUsuarioDTO) {
        try {
            return new ResponseEntity(rolUsuarioService.guardarRolUsuario(rolUsuarioDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<RolUsuarioDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(rolUsuarioService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

