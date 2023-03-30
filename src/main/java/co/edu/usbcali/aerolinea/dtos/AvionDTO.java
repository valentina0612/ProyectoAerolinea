package co.edu.usbcali.aerolinea.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class AvionDTO {
    private Integer avioID;
    private String modelo;
    private String estado;
}
