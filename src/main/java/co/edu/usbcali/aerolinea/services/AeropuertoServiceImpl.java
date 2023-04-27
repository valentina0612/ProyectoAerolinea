package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
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

    @Override
    public AeropuertoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !aeropuertoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el cliente con Id " + id + ".");
        }
        return AeropuertoMapper.modelToDto(aeropuertoRepository.getReferenceById(id));
    }

}
