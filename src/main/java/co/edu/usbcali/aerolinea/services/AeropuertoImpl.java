package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeropuertoImpl implements AeropuertoService{
    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        if(aeropuertoDTO == null){
            throw new Exception("El aeropuerto viene con datos nulos");
        }
        if (aeropuertoDTO.getAeroId() == null){
            throw new Exception("El id no puede ser nulo");
        }
        if(aeropuertoDTO.getAeroId() < 0){
            throw new Exception("El id no puede ser negativo");
        }
        if (aeropuertoDTO.getNombre()== null || aeropuertoDTO.getNombre().trim().equals("")){
            throw new Exception("El nombre no puede ser nulo");
        }
        if(aeropuertoRepository.findById(aeropuertoDTO.getAeroId()).isPresent()){
            throw new Exception("El id no puede repetirse");
        }
        Aeropuerto aeropuerto = AeropuertoMapper.dtoToModel(aeropuertoDTO);
        return AeropuertoMapper.modelToDto(aeropuertoRepository.save(aeropuerto));
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        return AeropuertoMapper.modelToDtoList(aeropuertos);
    }
}
