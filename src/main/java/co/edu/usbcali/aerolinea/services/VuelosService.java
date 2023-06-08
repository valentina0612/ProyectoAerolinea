package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;

import java.util.List;

public interface VuelosService {
    VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception;

    VueloDTO modificarVuelo(VueloDTO vueloDTO) throws Exception;
    List<VueloDTO> obtenerVuelos();

    VueloDTO buscarPorId(Integer id) throws Exception;

    VueloDTO eliminarVuelo(Integer id) throws Exception;

    List<VueloDTO> obtenerVuelosActivos();
}
