package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoasiento")
public class TipoAsientoController {
    private final TipoAsientoService tipoAsientoService;

    public TipoAsientoController(TipoAsientoService tipoAsientoService) {
        this.tipoAsientoService = tipoAsientoService;
    }

    @GetMapping("/obtenerTipoAsientos")
    public ResponseEntity<List<TipoAsientoDTO>> obtenerTipoAsientos(){
        return new ResponseEntity(tipoAsientoService.obtenerTipoAsiento(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarTipoAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarTipoAsiento(@RequestBody TipoAsientoDTO tipoAsientoDTO) {
        try {
            return new ResponseEntity(tipoAsientoService.guardarTipoAsiento(tipoAsientoDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<TipoAsientoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(tipoAsientoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

}
