
package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.mapper.VueloMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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
            throw new Exception("El vuelo viene con datos nulos");
        }
        if (vueloDTO.getVueloId() == null){
            throw new Exception("El ID del vuelo no puede ser nulo");
        }
        if (vueloDTO.getAeropuerto_aeroIdOrigen()<=0){
            throw new Exception("id del origen del vuelo no válido");
        }
        if(vueloDTO.getAeropuerto_aeroIdDestino() <= 0){
            throw new Exception("id del destino del vuelo no válido");
        }
        if (vueloDTO.getPrecio() <= 0 || vueloDTO.getPrecioAsientoVip() <= 0 || vueloDTO.getPrecioAsientoNormal() <= 0 || vueloDTO.getPrecioAsientoBasico() <= 0){
            throw new Exception("precio del asiento no válido");
        }

        if(vueloRepository.findById(vueloDTO.getVueloId()).isPresent()){
            throw new Exception("El ID del vuelo no puede repetirse");
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

    @Override
    public VueloDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !vueloRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el vuelo con Id " + id + ".");
        }
        return VueloMapper.modelToDto(vueloRepository.getReferenceById(id));
    }

    private void validarClienteDTO(VueloDTO vueloDTO, boolean esCreacion) throws Exception {
        if (vueloDTO == null) throw new Exception("No han llegado los datos del vuelo.");

        if (vueloDTO.getVueloId() == null) throw new Exception("El id del vuelo es obligatorio.");

        if (esCreacion) {
            if(vueloRepository.existsById(vueloDTO.getVueloId())) {
                throw new Exception("El vuelo con Id " +
                        vueloDTO.getVueloId() + " ya se encuentra registrado.");
            }

        }
        if (esCreacion) {
            if(vueloRepository.existsById(vueloDTO.getVueloId())) {
                throw new Exception("El vuelo con Id " +
                        vueloDTO.getVueloId() + " ya se encuentra registrado.");
            }
        }
        if (!esCreacion) {
            if (!vueloRepository.existsById(vueloDTO.getVueloId())) {
                throw new Exception("No se ha encontrado el vuelo con Id " +
                        vueloDTO.getVueloId() + ".");
            }
        }

        if (vueloDTO.getAeropuerto_aeroIdDestino() == null || vueloDTO.getAeropuerto_aeroIdDestino() <= 0) {
            throw new Exception("El ID del destino del vuelo debe ser un número positivo.");
        }

        if (vueloDTO.getAeropuerto_aeroIdOrigen() == null || vueloDTO.getAeropuerto_aeroIdOrigen() <= 0) {
            throw new Exception("El ID del origen del vuelo debe ser un número positivo.");
        }

        // Validar si el tipo de documento consultado no existe
        if (!vueloRepository.existsById(vueloDTO.getAeropuerto_aeroIdDestino())) {
            throw new Exception("El ID del destino del vuelo " + vueloDTO.getAeropuerto_aeroIdDestino()
                    + " no se encuentra en base de datos");
        }

        // Validar si el tipo de documento consultado no existe
        if (!vueloRepository.existsById(vueloDTO.getAeropuerto_aeroIdOrigen())) {
            throw new Exception("El ID del origen del vuelo " + vueloDTO.getAeropuerto_aeroIdOrigen()
                    + " no se encuentra en base de datos");
        }

    }
}

