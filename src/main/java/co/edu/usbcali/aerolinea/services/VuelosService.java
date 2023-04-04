package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.VueloDTO;

import java.util.List;

public interface VuelosService {
    VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception;
    List<VueloDTO> obtenerVuelos();
}
