package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aeropuerto")
@CrossOrigin(origins = "*")
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService) {

        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping("/obtenerAeropuertos")
    public ResponseEntity<List<AeropuertoDTO>> aeropuertos(){
        return new ResponseEntity(
                aeropuertoService.obtenerAeropuertos()
                , HttpStatus.OK);
    }

    @PostMapping(path = "/guardarAeropuerto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
        try {
            return new ResponseEntity(aeropuertoService.guardarAeropuerto(aeropuertoDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<AeropuertoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(aeropuertoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/modificarAeropuerto",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity modificarAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
            try {
                return new ResponseEntity(aeropuertoService.modificarAeropuerto(aeropuertoDTO), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
            }
        }
    @GetMapping(path = "/aeropuertosActivos")
    public ResponseEntity<List<AeropuertoDTO>> obtenerAeropuertosActivos() {
        return new ResponseEntity(aeropuertoService.obtenerAeropuertosActivos(), HttpStatus.OK);
    }
    @PutMapping(value = "/eliminarAeropuerto/{idAeropuerto}")
    public ResponseEntity eliminarAeropuerto(@PathVariable("idAeropuerto") Integer idAeropuerto) {
        try {
            return new ResponseEntity(aeropuertoService.eliminarAeropuerto(idAeropuerto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
