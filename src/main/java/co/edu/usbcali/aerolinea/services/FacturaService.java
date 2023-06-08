package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;

import java.util.List;

public interface FacturaService {
    FacturaDTO guardarFactura(FacturaDTO facturaDTO) throws Exception;

    FacturaDTO modificarFactura(FacturaDTO facturaDTO) throws Exception;
    List<FacturaDTO> obtenerFacturas();
    FacturaDTO buscarPorId(Integer id) throws Exception;
    List<FacturaDTO> obtenerFacturasActivas();

    FacturaDTO eliminarFactura(Integer id) throws  Exception;

}
