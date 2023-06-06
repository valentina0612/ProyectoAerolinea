package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Usuario;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.services.VuelosService;
import co.edu.usbcali.aerolinea.services.VuelosServiceImpl;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilityTest;
import co.edu.usbcali.aerolinea.utility.VuelosUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class VuelosServicelpmlTest {

    @InjectMocks
    private VuelosServiceImpl vuelosServiceImpl;

    @Mock
    private VueloRepository vueloRepository;

    @Mock
    private AeropuertoRepository aeropuertoRepository;


    @Test
    public void guardarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(false);
        given(vueloRepository.save(any())).willReturn(VuelosUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vuelosServiceImpl.guardarVuelo(VuelosUtilityTest.VUELODTO_UNO);

        assertEquals(VuelosUtilityTest.ID_UNO, vueloSavedDTO.getVueloId());
    }

    @Test
    public void guardarVueloNotOk() {
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> vuelosServiceImpl.guardarVuelo(VuelosUtilityTest.VUELODTO_UNO));
    }

    @Test
    public void obtenerVuelosOk() {
        given(vueloRepository.findAll()).willReturn(VuelosUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vuelosServiceImpl.obtenerVuelos();

        assertEquals(VuelosUtilityTest.VUELOS_SIZE, vuelosSavedDTO.size());
        assertEquals(VuelosUtilityTest.PRECIO_UNO, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosNotOk() {
        given(vueloRepository.findAll()).willReturn(VuelosUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vuelosServiceImpl.obtenerVuelos();

        assertEquals(VuelosUtilityTest.VUELOS_VACIO_SIZE, vuelosSavedDTO.size());
    }

    @Test
    public void obtenerVuelosActivosOk() {
        given(vueloRepository.findByEstado("Activo")).willReturn(VuelosUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vuelosServiceImpl.obtenerVuelosActivos();

        assertEquals(VuelosUtilityTest.VUELOS_SIZE, vuelosSavedDTO.size());
        assertEquals(VuelosUtilityTest.PRECIO_UNO, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosActivosNotOk() {
        given(vueloRepository.findByEstado("Activo")).willReturn(VuelosUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vuelosServiceImpl.obtenerVuelosActivos();

        assertEquals(VuelosUtilityTest.VUELOS_VACIO_SIZE, vuelosSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_DOS);
        vueloRepository.save(VuelosUtilityTest.VUELO_UNO);

        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VuelosUtilityTest.ID_UNO)).willReturn(VuelosUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vuelosServiceImpl.buscarPorId(VuelosUtilityTest.ID_UNO);

        assertEquals(VuelosUtilityTest.ID_UNO, vueloSavedDTO.getVueloId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vuelosServiceImpl.buscarPorId(VuelosUtilityTest.ID_UNO));
    }

    @Test
    public void modificarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.save(any())).willReturn(VuelosUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vuelosServiceImpl.modificarVuelo(VuelosUtilityTest.VUELODTO_UNO);

        assertEquals(VuelosUtilityTest.ID_UNO, vueloSavedDTO.getVueloId());
    }


    @Test
    public void modificarVueloNotOk() {
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vuelosServiceImpl.modificarVuelo(VuelosUtilityTest.VUELODTO_UNO));
    }

}
