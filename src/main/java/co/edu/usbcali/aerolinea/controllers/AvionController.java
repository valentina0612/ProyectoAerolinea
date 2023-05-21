package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
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
}



