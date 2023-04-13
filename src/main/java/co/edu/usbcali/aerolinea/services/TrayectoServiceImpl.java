package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.model.Trayecto;
import co.edu.usbcali.aerolinea.repository.TrayectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrayectoServiceImpl implements TrayectoService{
    private final TrayectoRepository trayectoRepository;

    public TrayectoServiceImpl(TrayectoRepository trayectoRepository) {
        this.trayectoRepository = trayectoRepository;
    }

    @Override
    public TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        if(trayectoDTO==null){
            throw new Exception("El trayecto viene con datos nulos");
        }
        if(trayectoDTO.getTrayId()==null){
            throw new Exception("El ID del trayecto no puede ser nulo");
        }
        if (trayectoDTO.getTrayId()<0){
            throw new Exception("El id del trayecto no puede ser negativo");
        }
        if (trayectoDTO.getAvioId()==null){
            throw new Exception("El ID del avion no puede ser nulo");
        }
        if (trayectoDTO.getAvioId()<0){
            throw new Exception("El id del avion no puede ser negativo");
        }
        if (trayectoDTO.getAereoIdOrigen()==null){
            throw new Exception("El ID del avion de origen no puede ser nulo");
        }
        if (trayectoDTO.getAereoIdOrigen()<0){
            throw new Exception("El id del avion de origen no puede ser negativo");
        }
        if (trayectoDTO.getAereoIdDestin()==null){
            throw new Exception("El ID del avion de destino no puede ser nulo");
        }
        if (trayectoDTO.getAereoIdDestin()<0){
            throw new Exception("El id del avion de destino no puede ser negativo");
        }
        if (trayectoDTO.getVuelId()==null){
            throw new Exception("El ID del vuelo no puede ser nulo");
        }
        if (trayectoDTO.getVuelId()<0){
            throw new Exception("El id del vuelo no puede ser negativo");
        }
        Trayecto trayecto= TrayectoMapper.dtoToModel(trayectoDTO);
        return TrayectoMapper.modelToDto(trayectoRepository.save(trayecto));
    }

    @Override
    public List<TrayectoDTO> obtenerTrayecto() {
        List<Trayecto>trayectos=trayectoRepository.findAll();
        return TrayectoMapper.modelToDtoList(trayectos);
    }
}
