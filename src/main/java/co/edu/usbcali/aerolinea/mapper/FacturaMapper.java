package co.edu.usbcali.aerolinea.mapper;
import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.model.Factura;
import java.util.List;
import java.util.stream.Collectors;
public class FacturaMapper {
    public static FacturaDTO modelToDto(Factura factura){
        return FacturaDTO.builder()
                .factId(factura.getFactId())
                .reseId(factura.getReseId())
                .fecha(factura.getFecha())
                .estado(factura.getEstado())
                .build();

    }
    public static Factura dtoToModel (FacturaDTO facturaDTO){
        return Factura.builder()
                .factId(facturaDTO.getFactId())
                .reseId(facturaDTO.getReseId())
                .fecha(facturaDTO.getFecha())
                .build();
    }

    public static List<FacturaDTO> modelToDtoList(List<Factura> factura){
        return factura.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Factura> dtoToModelList(List<FacturaDTO> facturaDTOS){
        return facturaDTOS.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }

}
