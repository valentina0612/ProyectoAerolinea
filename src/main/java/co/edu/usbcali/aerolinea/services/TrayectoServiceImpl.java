package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Trayecto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TrayectoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrayectoServiceImpl implements TrayectoService{
    private final TrayectoRepository trayectoRepository;
    private final AvionRepository avionRepository;
    private final AeropuertoRepository aeropuertoRepository;
    private final VueloRepository vueloRepository;
    public TrayectoServiceImpl(TrayectoRepository trayectoRepository, AvionRepository avionRepository, AeropuertoRepository aeropuertoRepository, VueloRepository vueloRepository) {
        this.trayectoRepository = trayectoRepository;
        this.avionRepository = avionRepository;
        this.aeropuertoRepository = aeropuertoRepository;
        this.vueloRepository = vueloRepository;
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
        if (trayectoDTO.getAereoIdDestino()==null){
            throw new Exception("El ID del avion de destino no puede ser nulo");
        }
        if (trayectoDTO.getAereoIdDestino()<0){
            throw new Exception("El id del avion de destino no puede ser negativo");
        }
        if (trayectoDTO.getVuelId()==null){
            throw new Exception("El ID del vuelo no puede ser nulo");
        }
        if (trayectoDTO.getVuelId()<0){
            throw new Exception("El id del vuelo no puede ser negativo");
        }
        if(trayectoRepository.findById(trayectoDTO.getTrayId()).isPresent()){
            throw new Exception("El ID ya existe");
        }
        Avion avion = avionRepository.getReferenceById(trayectoDTO.getAvioId());
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(trayectoDTO.getAereoIdOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(trayectoDTO.getAereoIdDestino());
        Vuelo vuelo = vueloRepository.getReferenceById(trayectoDTO.getVuelId());
        Trayecto trayecto= TrayectoMapper.dtoToModel(trayectoDTO);
        trayecto.setAvion(avion);
        trayecto.setAeropuerto(aeropuertoOrigen);
        trayecto.setAeropuerto2(aeropuertoDestino);
        trayecto.setVuelo(vuelo);
        return TrayectoMapper.modelToDto(trayectoRepository.save(trayecto));
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectos() {
        List<Trayecto>trayectos=trayectoRepository.findAll();
        return TrayectoMapper.modelToDtoList(trayectos);
    }
}
