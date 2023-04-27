package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Trayecto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TrayectoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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

    @Override
    public TrayectoDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !trayectoRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el usuario con Id " + id + ".");
        }
        return TrayectoMapper.modelToDto(trayectoRepository.getReferenceById(id));
    }

    private void validarClienteDTO(TrayectoDTO trayectoDTO, boolean esCreacion) throws Exception {
        if (trayectoDTO == null) throw new Exception("No han llegado los datos del cliente.");

        if (trayectoDTO.getTrayId() == null) throw new Exception("El id del cliente es obligatorio.");

        if (esCreacion) {
            if(trayectoRepository.existsById(trayectoDTO.getTrayId())) {
                throw new Exception("El cliente con Id " +
                        trayectoDTO.getTrayId() + " ya se encuentra registrado.");
            }

        }
        if (!esCreacion) {
            if (!trayectoRepository.existsById(trayectoDTO.getTrayId())) {
                throw new Exception("No se ha encontrado el cliente con Id " +
                        trayectoDTO.getTrayId() + ".");
            }
        }

        if (trayectoDTO.getAereoIdOrigen() == null || trayectoDTO.getAereoIdOrigen() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }

        if (trayectoDTO.getAereoIdDestino() == null || trayectoDTO.getAereoIdDestino() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }

        if (trayectoDTO.getAvioId() == null || trayectoDTO.getAvioId() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }

        if (trayectoDTO.getVuelId() == null || trayectoDTO.getVuelId() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }


        // Validar si el tipo de documento consultado no existe
        if (!trayectoRepository.existsById(trayectoDTO.getAereoIdOrigen())) {
            throw new Exception("El tipo de documento " + trayectoDTO.getAereoIdOrigen()
                    + " no se encuentra en base de datos");
        }

        if (!trayectoRepository.existsById(trayectoDTO.getAereoIdDestino())) {
            throw new Exception("El tipo de documento " + trayectoDTO.getAereoIdDestino()
                    + " no se encuentra en base de datos");
        }

        if (!trayectoRepository.existsById(trayectoDTO.getAvioId())) {
            throw new Exception("El tipo de documento " + trayectoDTO.getAvioId()
                    + " no se encuentra en base de datos");
        }

        if (!trayectoRepository.existsById(trayectoDTO.getVuelId())) {
            throw new Exception("El tipo de documento " + trayectoDTO.getVuelId()
                    + " no se encuentra en base de datos");
        }

    }

}
