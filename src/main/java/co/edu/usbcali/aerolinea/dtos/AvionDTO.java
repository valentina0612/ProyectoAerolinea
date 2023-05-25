package co.edu.usbcali.aerolinea.dtos;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
public class AvionDTO {
    private Integer avioID;
    private String modelo;
    private String estado;
}
