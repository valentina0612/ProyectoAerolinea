package co.edu.usbcali.aerolinea.services;
import co.edu.usbcali.aerolinea.mapper.FacturaMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.Factura;


import co.edu.usbcali.aerolinea.dtos.FacturaDTO;

import co.edu.usbcali.aerolinea.model.Reserva;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private final ReservaRepository reservaRepository;
    public FacturaServiceImpl(FacturaRepository facturaRepository, ReservaRepository reservaRepository) {
        this.facturaRepository = facturaRepository;
        this.reservaRepository = reservaRepository;
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
        if(facturaRepository.findById(facturaDTO.getFactId()).isPresent()) {
            throw new Exception("El ID no puede repetirse");
        }
        Reserva reserva= reservaRepository.getReferenceById(facturaDTO.getFactId());
        Factura factura = FacturaMapper.dtoToModel(facturaDTO);
        factura.setReserva(reserva);
        return FacturaMapper.modelToDto(facturaRepository.save(factura));

    }

    @Override
    public List<FacturaDTO> obtenerFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return FacturaMapper.modelToDtoList(facturas);


    }

}
