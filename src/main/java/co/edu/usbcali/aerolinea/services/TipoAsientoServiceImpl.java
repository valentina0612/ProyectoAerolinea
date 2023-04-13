package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAsientoServiceImpl implements TipoAsientoService{

    private final TipoAsientoRepository tipoAsientoRepository;

    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoRepository) {
        this.tipoAsientoRepository = tipoAsientoRepository;
    }

    @Override
    public TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        if(tipoAsientoDTO == null){
            throw new Exception("El aeropuerto viene con datos nulos");
        }
        if (tipoAsientoDTO.getTiasId() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (tipoAsientoDTO.getTiasId()<0){
            throw new Exception("El id no puede ser negativo");
        }
        if (tipoAsientoDTO.getDescripcion()== null || tipoAsientoDTO.getDescripcion().trim().equals("")){
            throw new Exception("El nombre no puede ser nulo");
        }
        if(tipoAsientoRepository.findById(tipoAsientoDTO.getTiasId()).isPresent()) {
            throw new Exception("El ID no puede repetirse");
        }
        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToModel(tipoAsientoDTO);
        return TipoAsientoMapper.modelToDto(tipoAsientoRepository.save(tipoAsiento));
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsiento() {
        return TipoAsientoMapper.modelToDtoList(tipoAsientoRepository.findAll());
    }

}
