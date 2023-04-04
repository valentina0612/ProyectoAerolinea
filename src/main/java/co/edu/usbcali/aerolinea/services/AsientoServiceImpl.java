package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AsientoMapper;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
