
package co.edu.usbcali.aerolinea.services;

import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.mapper.TrayectoMapper;
import co.edu.usbcali.aerolinea.mapper.UsuarioMapper;
import co.edu.usbcali.aerolinea.mapper.VueloMapper;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.Trayecto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.utility.ConstantesUtility;
import co.edu.usbcali.aerolinea.utility.ValidationUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class VuelosServiceImpl implements VuelosService{
    private final VueloRepository vueloRepository;
    private final AeropuertoRepository aeropuertoRepository;

    //Inyección de dependencias
    public VuelosServiceImpl(VueloRepository vueloRepository, AeropuertoRepository aeropuertoRepository) {
        this.vueloRepository = vueloRepository;
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception {
        validar(vueloDTO, true);
        return crearOModificar(vueloDTO);

    }

    @Override
    public VueloDTO modificarVuelo(VueloDTO vueloDTO) throws Exception {
        validar(vueloDTO, false);
        return crearOModificar(vueloDTO);
    }

    @Override
    public List<VueloDTO> obtenerVuelos() {
        List<Vuelo> vuelos= vueloRepository.findAll();
        return VueloMapper.modelToDtoList(vuelos);
    }
    @Override
    public VueloDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !vueloRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el vuelo con Id " + id + ".");
        }
        return VueloMapper.modelToDto(vueloRepository.getReferenceById(id));
    }

    @Override
    public VueloDTO eliminarVuelo(Integer id) throws Exception {
        VueloDTO vueloEliminado = buscarPorId(id);
        vueloEliminado.setEstado("Inactivo");
        return crearOModificar(vueloEliminado);
    }

    @Override
    public List<VueloDTO> obtenerVuelosActivos() {
        return VueloMapper.modelToDtoList(vueloRepository.findByEstado("Activo"));
    }

    private void validar(VueloDTO vueloDTO, boolean esCreacion) throws Exception {
        if (vueloDTO == null) throw new Exception("No han llegado los datos del vuelo.");

        //if (vueloDTO.getVueloId() == null) throw new Exception("El id del vuelo es obligatorio.");

        // if (vueloDTO.getHora_llegada().before(new Date())) throw new Exception("Esa fecha ya pasó.");

       //if (vueloDTO.getHora_salida().before(new Date())) throw new Exception("Esa fecha ya pasó.");


        if (esCreacion) {
            /*
            if(vueloRepository.existsById(vueloDTO.getVueloId())) {
                throw new Exception("El vuelo con Id " +
                        vueloDTO.getVueloId() + " ya se encuentra registrado.");
            }

             */
        }
        if (!esCreacion) {
            if (!vueloRepository.existsById(vueloDTO.getVueloId())) {
                throw new Exception("No se ha encontrado el vuelo con Id " +
                        vueloDTO.getVueloId() + ".");
            }
        }

        if (!aeropuertoRepository.existsById(vueloDTO.getAeropuerto_aeroIdDestino())) {
            throw new Exception("El ID del aeropuerto de destino" + vueloDTO.getAeropuerto_aeroIdDestino()
                    + " no se encuentra en base de datos");
        }

        // Validar si el tipo de documento consultado no existe
        if (!aeropuertoRepository.existsById(vueloDTO.getAeropuerto_aeroIdOrigen())) {
            throw new Exception("El ID del aeropuerto de origen " + vueloDTO.getAeropuerto_aeroIdOrigen()
                    + " no se encuentra en base de datos");
        }

        ValidationUtility.stringIsNullOrBlank(vueloDTO.getEstado(), "El estado no puede ser nulo");
        }
    private VueloDTO crearOModificar(VueloDTO vueloDTO) {
        Aeropuerto aeropuertoOrigen = aeropuertoRepository.getReferenceById(vueloDTO.getAeropuerto_aeroIdOrigen());
        Aeropuerto aeropuertoDestino = aeropuertoRepository.getReferenceById(vueloDTO.getAeropuerto_aeroIdDestino());
        Vuelo vuelo = VueloMapper.dtoToModel(vueloDTO);
        vuelo.setAeropuertoOrigen(aeropuertoOrigen);
        vuelo.setAeropuertoDestino(aeropuertoDestino);
        return VueloMapper.modelToDto(vueloRepository.save(vuelo));
    }

}

