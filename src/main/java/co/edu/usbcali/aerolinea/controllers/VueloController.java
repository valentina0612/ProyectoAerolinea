
package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.services.VuelosService;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vuelo")
@CrossOrigin(origins = "*")
public class VueloController {
    private VuelosService vueloService;

    public VueloController(VuelosService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/obtenerVuelos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelos(){
        return new ResponseEntity(vueloService.obtenerVuelos(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarVuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarVuelo(@RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity(vueloService.guardarVuelo(vueloDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<VueloDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(vueloService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/modificarVuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modificarVuelo(@RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity(vueloService.modificarVuelo(vueloDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/vuelosActivos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelosActivos() {
        return new ResponseEntity(vueloService.obtenerVuelosActivos(), HttpStatus.OK);
    }
    @PutMapping(value = "/eliminarVuelo/{idVuelo}")
    public ResponseEntity eliminarVuelo(@PathVariable("idVuelo") Integer idVuelo) {
        try {
            return new ResponseEntity(vueloService.eliminarVuelo(idVuelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
