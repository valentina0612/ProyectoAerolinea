package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mapper.AsientoMapper;
import co.edu.usbcali.aerolinea.mapper.AvionMapper;
import co.edu.usbcali.aerolinea.mapper.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
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
        validar(avionDTO, true);
        return crearOModificar(avionDTO);
    }

    @Override
    public AvionDTO modificarAvion(AvionDTO avionDTO) throws Exception {
        validar(avionDTO, false);
        return crearOModificar(avionDTO);
    }

    @Override
    public List<AvionDTO> obtenerAviones(){
        List<Avion> aviones = avionRepository.findAll();
        return AvionMapper.modelToDtoList(aviones);
    }

    @Override
    public AvionDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !avionRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el avión con Id " + id + ".");
        }
        return AvionMapper.modelToDto(avionRepository.getReferenceById(id));
    }

    @Override
    public List<AvionDTO> obtenerAvionesActivos() {
        List<Avion> aviones = avionRepository.findAllByEstado("Activo");
        return AvionMapper.modelToDtoList(aviones);
    }

    @Override
    public AvionDTO eliminarAvion(Integer id) throws Exception {
        AvionDTO avionEliminado = buscarPorId(id);
        avionEliminado.setEstado("Inactivo");
        return crearOModificar(avionEliminado);
    }

    private void validar(AvionDTO avionDTO, boolean esCreacion) throws Exception {

        if (avionDTO == null) throw new Exception("No han llegado los datos del avión.");

        if (avionDTO.getAvioID() == null) throw new Exception("El id del avión es obligatorio.");

        if (esCreacion) {
            if(avionRepository.existsById(avionDTO.getAvioID())) {
                throw new Exception("El avión con Id " +
                         avionDTO.getAvioID() + " ya se encuentra registrado.");
            }
        }
        if (!esCreacion) {
            if (!avionRepository.existsById(avionDTO.getAvioID())) {
                throw new Exception("No se ha encontrado el avión con Id " +
                        avionDTO.getAvioID()+ ".");
            }
        }
        ValidationUtility.integerIsNullOrLessZero(avionDTO.getAvioID(), "El id del avión debe ser positivo");
        ValidationUtility.stringIsNullOrBlank(avionDTO.getModelo(), "El modelo es obligatorio");
        ValidationUtility.stringIsNullOrBlank(avionDTO.getEstado(), "El estado es obligatorio.");
    }
    private AvionDTO crearOModificar(AvionDTO avionDTO) {
        Avion avion = AvionMapper.dtoToModel(avionDTO);
        return AvionMapper.modelToDto(avionRepository.save(avion));
    }
}

