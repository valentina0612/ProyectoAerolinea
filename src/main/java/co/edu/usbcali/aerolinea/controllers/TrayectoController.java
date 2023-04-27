package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.services.TrayectoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/trayecto")
public class TrayectoController {
    private final TrayectoService trayectoService;


    public TrayectoController(TrayectoService trayectoService) {
        this.trayectoService = trayectoService;
    }
    @GetMapping("/obtenerTrayecto")
    public ResponseEntity<List<TrayectoDTO>> obtenerTrayecto(){
        return new ResponseEntity(trayectoService.obtenerTrayectos(), HttpStatus.OK);
    }
    @PostMapping(path = "/guardarTrayecto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarTrayecto(@RequestBody TrayectoDTO trayectoDTO) {
        try {
            return new ResponseEntity(trayectoService.guardarTrayecto(trayectoDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<TrayectoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(trayectoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
