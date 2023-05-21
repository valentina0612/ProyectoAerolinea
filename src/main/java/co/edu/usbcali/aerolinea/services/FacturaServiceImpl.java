package co.edu.usbcali.aerolinea.services;
import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.AvionMapper;
import co.edu.usbcali.aerolinea.mapper.FacturaMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Factura;
import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.model.Reserva;
import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
        validar(facturaDTO, true);
        return  crearOModificar(facturaDTO);
    }

    @Override
    public FacturaDTO modificarFactura(FacturaDTO facturaDTO) throws Exception {
        validar(facturaDTO, false);
        return  crearOModificar(facturaDTO);
    }

    @Override
    public List<FacturaDTO> obtenerFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return FacturaMapper.modelToDtoList(facturas);


    }

    @Override
    public FacturaDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !facturaRepository.existsById(id)) {
            throw new Exception("No se ha encontrado la factura con Id " + id + ".");
        }
        return FacturaMapper.modelToDto(facturaRepository.getReferenceById(id));
    }

    @Override
    public List<FacturaDTO> obtenerFacturasActivas() {
        List<Factura> facturas = facturaRepository.findAllByEstado("Activo");
        return FacturaMapper.modelToDtoList(facturas);
    }


    @Override
    public FacturaDTO eliminarFactura(Integer id) throws Exception {
        FacturaDTO facturaEliminada = buscarPorId(id);
        facturaEliminada.setEstado("Inactivo");
        return crearOModificar(facturaEliminada);
    }

    @Override
    public FacturaDTO obtenerFacturaReserva(Integer idReserva) throws Exception {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new Exception("No se ha encontrado esa reserva"));
        return FacturaMapper.modelToDto(facturaRepository.findByReserva(reserva));
    }

    private void validar(FacturaDTO facturaDTO, boolean esCreacion) throws Exception {
        if (facturaDTO == null) throw new Exception("No han llegado los datos de la factura.");

        if (facturaDTO.getFactId() == null) throw new Exception("El id de la factura es obligatorio.");

        if(facturaDTO.getFecha().after(new Date())) throw new Exception("Esa fecha ya pasó.");

        if (esCreacion) {
            if(facturaRepository.existsById(facturaDTO.getFactId())) {
                throw new Exception("La factura con Id " +
                        facturaDTO.getFactId() + " ya se encuentra registrado.");
            }
        }

        if (!esCreacion) {
            if (!facturaRepository.existsById(facturaDTO.getFactId())) {
                throw new Exception("No se ha encontrado la factura con Id " +
                        facturaDTO.getFactId() + ".");
            }
            if (facturaRepository.existsByReservaAndFactId(facturaDTO.getReseId(), facturaDTO.getFactId())) {
                throw new Exception("La reserva" + facturaDTO.getReseId()+ " ya tiene su respectiva factura.");
            }

        }

        if (!reservaRepository.existsById(facturaDTO.getReseId())) {
            throw new Exception("El ID de la reserva " + facturaDTO.getReseId()
                    + " no se encuentra en base de datos");
        }
        ValidationUtility.stringIsNullOrBlank(facturaDTO.getEstado(), "El estado no debe ser nulo");
        ValidationUtility.integerIsNullOrLessZero(facturaDTO.getFactId(), "El id de la factura debe ser positivo");
        ValidationUtility.integerIsNullOrLessZero(facturaDTO.getReseId(), "El id la reserva debe ser positivo");
    }
    private FacturaDTO crearOModificar(FacturaDTO facturaDTO) {
        Factura factura = FacturaMapper.dtoToModel(facturaDTO);
        return FacturaMapper.modelToDto(facturaRepository.save(factura));
    }

}
