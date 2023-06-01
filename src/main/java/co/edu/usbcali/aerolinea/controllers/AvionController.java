package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.services.AvionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avion")
@CrossOrigin(origins = "*")

public class AvionController {
    private final AvionService avionService;

    public AvionController(AvionService avionService) {

        this.avionService = avionService;
    }

    @GetMapping("/obtenerAviones")
    public ResponseEntity<List<AvionDTO>> aviones() {
        return new ResponseEntity(avionService.obtenerAviones(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarAviones",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAvion(@RequestBody AvionDTO avionDTO) {
        try {
            return new ResponseEntity(avionService.guardarAvion(avionDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AvionDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(avionService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/modificarAvion",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modificarAvion(@RequestBody AvionDTO avionDTO) {
        try {
            return new ResponseEntity(avionService.modificarAvion(avionDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/AvionesActivos")
    public ResponseEntity<List<AvionDTO>> obtenerAvionesActivos() {
        return new ResponseEntity(avionService.obtenerAvionesActivos(), HttpStatus.OK);
    }
    @PutMapping(value = "/eliminarAvion/{idAvion}")
    public ResponseEntity eliminarAvion(@PathVariable("idAvion") Integer idAvion) {
        try {
            return new ResponseEntity(avionService.eliminarAvion(idAvion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}



