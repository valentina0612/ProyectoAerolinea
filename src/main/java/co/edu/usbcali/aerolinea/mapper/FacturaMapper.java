package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.Factura;
import co.edu.usbcali.aerolinea.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class FacturaMapper {
    public static FacturaDTO modelToDto(Factura factura){
        return FacturaDTO.builder()
                .factId(factura.getFactId())
                .reseId(factura.getReserva()!= null?
                        factura.getReserva().getReseId():null)
                .fecha(factura.getFecha())
                .estado(factura.getEstado())
                .build();

    }
    public static Factura dtoToModel (FacturaDTO facturaDTO){
        return Factura.builder()
                .factId(facturaDTO.getFactId())
                .fecha(facturaDTO.getFecha())
                .estado(facturaDTO.getEstado())
                .build();
    }

    public static List<FacturaDTO> modelToDtoList(List<Factura> facturas){
        return facturas.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Factura> dtoToModelList(List<FacturaDTO> facturaDTOS){
        return facturaDTOS.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}
