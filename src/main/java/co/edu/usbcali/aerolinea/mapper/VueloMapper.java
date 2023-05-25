
package co.edu.usbcali.aerolinea.mapper;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.model.Vuelo;

import java.util.List;
import java.util.stream.Collectors;

public class VueloMapper {

    public static VueloDTO modelToDto(Vuelo vuelo){
        return VueloDTO.builder()
                .vueloId(vuelo.getVueloId())
                .aeropuerto_aeroIdOrigen( vuelo.getAeropuertoOrigen()!= null?
                        vuelo.getAeropuertoOrigen().getAeroId():null)
                .aeropuerto_aeroIdDestino( vuelo.getAeropuertoDestino()!= null?
                        vuelo.getAeropuertoDestino().getAeroId():null)
                .nombreAeroOrigen(vuelo.getAeropuertoOrigen()!= null?
                        vuelo.getAeropuertoOrigen().getNombre():null)
                .nombreAeroDestino(vuelo.getAeropuertoDestino()!= null?
                        vuelo.getAeropuertoDestino().getNombre():null)
                .precio(vuelo.getPrecio())
                .hora_salida(vuelo.getHora_salida())
                .hora_llegada(vuelo.getHora_llegada())
                .precioAsientoVip(vuelo.getPrecioAsientoVip())
                .precioAsientoNormal(vuelo.getPrecioAsientoNormal())
                .precioAsientoBasico(vuelo.getPrecioAsientoBasico())
                .estado(vuelo.getEstado())

                .build();

    }
    public static Vuelo dtoToModel (VueloDTO vueloDTO){
        return Vuelo.builder()
                //.vueloId(vueloDTO.getVueloId())
                .precio(vueloDTO.getPrecio())
                .hora_salida(vueloDTO.getHora_salida())
                .hora_llegada(vueloDTO.getHora_llegada())
                .precioAsientoVip(vueloDTO.getPrecioAsientoVip())
                .precioAsientoNormal(vueloDTO.getPrecioAsientoNormal())
                .precioAsientoBasico(vueloDTO.getPrecioAsientoBasico())
                .estado(vueloDTO.getEstado())
                .build();
    }

    public static List<VueloDTO> modelToDtoList(List<Vuelo> vuelos){
        return vuelos.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<Vuelo> dtoToModelList(List<VueloDTO> vuelosDTO){
        return vuelosDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }
}


