package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.mapper.AvionMapper;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionServiceImpl implements AvionService{

    private final AvionRepository avionRepository;

    public AvionServiceImpl(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }
    @Override
    public AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception{
        if (avionDTO == null) {
            throw new Exception("El rol de usuario viene con datos nulos");
        }
        if (avionDTO.getAvioID() == null){
            throw new Exception("El ID no puede ser nulo");
        }
        if (avionDTO.getAvioID()<0){
            throw new Exception("El id no puede ser nulo");
        }
        if (avionDTO.getModelo()== null || avionDTO.getModelo().trim().equals("")){
            throw new Exception("La descripción no puede estar vacia");
        }
        if(avionRepository.findById(avionDTO.getAvioID()).isPresent()){
            throw new Exception("El ID no puede repetirse");
        }
        Avion avion = AvionMapper.dtoToModel(avionDTO);
        return AvionMapper.modelToDto(avionRepository.save(avion));
    }

    @Override
    public List<AvionDTO> obtenerAviones(){
        List<Avion> aviones = avionRepository.findAll();
        return AvionMapper.modelToDtoList(aviones);
    }
}

