package co.edu.usbcali.aerolinea.controllers;
import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.services.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = "*")

public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/obtenerFactura")
    public ResponseEntity<List<FacturaDTO>> obtenerFactura() {
        return new ResponseEntity(facturaService.obtenerFacturas(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardarFactura",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarFactura(@RequestBody FacturaDTO facturaDTO) {
        try {
            return new ResponseEntity(facturaService.guardarFactura(facturaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(facturaService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }


    }
}
