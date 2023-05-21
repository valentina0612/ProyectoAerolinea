package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.services.AsientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/asiento")
@CrossOrigin(origins = "*")

public class AsientoController {
    private final AsientoService asientoService;

    public AsientoController(AsientoService asientoService) {
        this.asientoService = asientoService;
    }


    @GetMapping("/obtenerAsientos")
    public ResponseEntity<List<AsientoDTO>> obtenerAsientos(){
        return new ResponseEntity(asientoService.obtenerAsientos(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAsiento(@RequestBody AsientoDTO asientoDTO) {
        try {
            return new ResponseEntity(asientoService.guardarAsiento(asientoDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<AsientoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try{
            return new ResponseEntity(asientoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

}
