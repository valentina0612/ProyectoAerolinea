package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.AsientoMapper;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AsientoServiceImpl implements AsientoService{
    private final AsientoRepository asientoRepository;
    private final TipoAsientoRepository tipoAsientoRepository;
    private final AvionRepository avionRepository;


    public AsientoServiceImpl(AsientoRepository asientoRepository, TipoAsientoRepository tipoAsientoRepository, AvionRepository avionRepository) {
        this.asientoRepository = asientoRepository;
        this.tipoAsientoRepository = tipoAsientoRepository;
        this.avionRepository = avionRepository;
    }

    @Override
    public AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception {
        if(asientoDTO == null){
            throw new Exception("El asiento no puede ser nulo");
        }
        if(asientoDTO.getAsieId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if(asientoDTO.getTipoAsiento_tiasId() <= 0){
            throw new Exception("Id de tipo asiento no válido");
        }
        if(asientoDTO.getAvion_avioId() <= 0){
            throw new Exception("Id del avión no válido");
        }
        if(asientoDTO.getUbicacion() == null || asientoDTO.getUbicacion().trim().equals("")){
            throw new Exception("Ubicación no válida");
        }
        if(asientoDTO.getPrecio() <=0 ){
            throw new Exception("Precio no válido");
        }
        if(asientoDTO.getEstado() == null || asientoDTO.getEstado().trim().equals("")){
            throw new Exception("Estado no válido");
        }
        if(asientoRepository.findById(asientoDTO.getAsieId()).isPresent()){
            throw new Exception("El ID no puede repetirse");
        }
        TipoAsiento tipoAsiento = tipoAsientoRepository.getReferenceById(asientoDTO.getTipoAsiento_tiasId());
        Avion avion = avionRepository.getReferenceById(asientoDTO.getAvion_avioId());
        Asiento asiento = AsientoMapper.dtoToModel(asientoDTO);
        asiento.setTipoAsiento(tipoAsiento);
        asiento.setAvion(avion);
        return AsientoMapper.modelToDto(asientoRepository.save(asiento));

    }

    @Override
    public List<AsientoDTO> obtenerAsientos() {
        List<Asiento> asientos = asientoRepository.findAll();
        return AsientoMapper.modelToDtoList(asientos);
    }

    @Override
    public AsientoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !asientoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el asiento con Id " + id + ".");
        }
        return AsientoMapper.modelToDto(asientoRepository.getReferenceById(id));
    }

    //Este método se usará cuando se implemente el método put
    private void validarClienteDTO(AsientoDTO asientoDTO, boolean esCreacion) throws Exception {
        if (asientoDTO == null) throw new Exception("No han llegado los datos del asiento.");

        if (asientoDTO.getAsieId() == null) throw new Exception("El id del asiento es obligatorio.");

        if (esCreacion) {
            if(asientoRepository.existsById(asientoDTO.getAsieId())) {
                throw new Exception("El asiento con Id " +
                        asientoDTO.getAsieId() + " ya se encuentra registrado.");
            }

        }
        if (!esCreacion) {
            if (!asientoRepository.existsById(asientoDTO.getAsieId())) {
                throw new Exception("No se ha encontrado el asiento con Id " +
                        asientoDTO.getAsieId() + ".");
            }
        }

        if (asientoDTO.getTipoAsiento_tiasId() == null || asientoDTO.getTipoAsiento_tiasId() <= 0) {
            throw new Exception("El ID del tipo de asiento debe ser un número positivo.");
        }

        if (asientoDTO.getAvion_avioId() == null || asientoDTO.getAvion_avioId() <= 0) {
            throw new Exception("El ID del avion debe ser un número positivo.");
        }


        // Validar si el tipo de documento consultado no existe
        if (!asientoRepository.existsById(asientoDTO.getTipoAsiento_tiasId())) {
            throw new Exception("El ID de tipo de asiento " + asientoDTO.getTipoAsiento_tiasId()
                    + " no se encuentra en base de datos");
        }

        if (!asientoRepository.existsById(asientoDTO.getAvion_avioId())) {
            throw new Exception("El ID del avion " + asientoDTO.getAvion_avioId()
                    + " no se encuentra en base de datos");
        }
    }
}
