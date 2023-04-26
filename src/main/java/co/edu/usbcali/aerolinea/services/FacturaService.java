package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;

import java.util.List;

public interface FacturaService {
    FacturaDTO guardarFactura(FacturaDTO facturaDTO) throws Exception;
    List<FacturaDTO> obtenerFacturas();
}
