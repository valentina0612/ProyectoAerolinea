package co.edu.usbcali.aerolinea.services;
import co.edu.usbcali.aerolinea.mapper.FacturaMapper;

import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Factura;


import co.edu.usbcali.aerolinea.dtos.FacturaDTO;

import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaImpl implements FacturaService {

    private final FacturaRepository facturaRepository;


    public FacturaImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;

    }

    @Override
    public FacturaDTO guardarFactura(FacturaDTO facturaDTO)throws Exception {
        if (facturaDTO == null) {
            throw new Exception("La factura viene con datos nulos");
        }
        if (facturaDTO.getFactId() == null) {
            throw new Exception("El ID de la factura no puede ser nulo");
        }
        if (facturaDTO.getFactId()<0){
            throw new Exception("El ID de la factura no puede ser negativo");
        }
        if (facturaDTO.getReseId()==null){
            throw new Exception("El ID de la reserva no puede ser nulo");
        }
        if(facturaDTO.getReseId()<0){
            throw new Exception("El ID de la reserva no puede ser negativo");
        }

        Factura factura = FacturaMapper.dtoToModel(facturaDTO);
        return FacturaMapper.modelToDto(facturaRepository.save(factura));

    }

    @Override
    public List<FacturaDTO> obtenerFactura() {
        List<Factura> facturas = facturaRepository.findAll();
        return FacturaMapper.modelToDtoList(facturas);


    }

}
