package co.edu.usbcali.aerolinea.controllers;
import co.edu.usbcali.aerolinea.dtos.MensajeDTO;
import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.services.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaService reservaService;


    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @GetMapping("/obtenerReserva")
    public ResponseEntity<List<ReservaDTO>> obtenerReserva(){
        return new ResponseEntity(reservaService.obtenerReserva(), HttpStatus.OK);
    }
    @PostMapping(path = "/guardarReserva",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return new ResponseEntity(reservaService.guardarReserva(reservaDTO), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

}
