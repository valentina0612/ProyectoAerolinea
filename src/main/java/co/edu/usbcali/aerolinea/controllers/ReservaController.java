package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.*;
import co.edu.usbcali.aerolinea.services.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*")

public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @GetMapping("/obtenerReserva")
    public ResponseEntity<List<ReservaDTO>> obtenerReserva(){
        return new ResponseEntity(reservaService.obtenerReservas(), HttpStatus.OK);
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
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        try {
            return new ResponseEntity(reservaService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/modificarReserva",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modificarReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return new ResponseEntity(reservaService.modificarReserva(reservaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/reservasUsuario/")
    public ResponseEntity<List<ReservaDTO>> obtenerReservaActivas() {
        return new ResponseEntity(reservaService.obtenerReservasActivas(), HttpStatus.OK);
    }
    @PutMapping(value = "/eliminarReserva/{idReserva}")
    public ResponseEntity eliminarReserva(@PathVariable("idReserva") Integer idReserva) {
        try {
            return new ResponseEntity(reservaService.eliminarReserva(idReserva), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtenerReservasUsuario/{id}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasUsuario(@PathVariable Integer id) {
        try {
            return new ResponseEntity(reservaService.obtenerReservasUsuario(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }


}
