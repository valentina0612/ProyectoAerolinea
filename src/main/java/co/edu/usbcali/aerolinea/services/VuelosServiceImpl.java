
package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.mapper.VueloMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VuelosServiceImpl implements VuelosService{
    private final VueloRepository vueloRepository;
    private final AeropuertoRepository aeropuertoRepository;

    //Inyección de dependencias
    public VuelosServiceImpl(VueloRepository vueloRepository, AeropuertoRepository aeropuertoRepository) {
        this.vueloRepository = vueloRepository;
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception {
        if (vueloDTO == null){
            throw new Exception("El usuario viene con datos nulos");
        }
        if (vueloDTO.getVueloId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (vueloDTO.getAeropuerto_aeroIdOrigen()<=0){
            throw new Exception("id no válido");
        }
        if(vueloDTO.getAeropuerto_aeroIdDestino() <= 0){
            throw new Exception("id no válido");
        }
        if (vueloDTO.getPrecio() <= 0 || vueloDTO.getPrecioAsientoVip() <= 0 || vueloDTO.getPrecioAsientoNormal() <= 0 || vueloDTO.getPrecioAsientoBasico() <= 0){
            throw new Exception("precio no válido");
        }

        if(vueloRepository.findById(vueloDTO.getVueloId()).isPresent()){
            throw new Exception("El ID no puede repetirse");
        }
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(vueloDTO.getAeropuerto_aeroIdOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(vueloDTO.getAeropuerto_aeroIdDestino());
        Vuelo vuelo = VueloMapper.dtoToModel(vueloDTO);
        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);
        return VueloMapper.modelToDto(vueloRepository.save(vuelo));

    }

    @Override
    public List<VueloDTO> obtenerVuelos() {
        List<Vuelo> vuelos= vueloRepository.findAll();
        return VueloMapper.modelToDtoList(vuelos);
    }
}

