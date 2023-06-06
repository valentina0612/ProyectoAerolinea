package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import co.edu.usbcali.aerolinea.services.TrayectoService;
import co.edu.usbcali.aerolinea.services.TrayectoServiceImpl;
import co.edu.usbcali.aerolinea.services.VuelosService;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilityTest;
import co.edu.usbcali.aerolinea.utility.AvionUtilityTest;
import co.edu.usbcali.aerolinea.utility.TrayectoUtilityTest;
import co.edu.usbcali.aerolinea.utility.VuelosUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class TrayectoServicelmplTest {
    @InjectMocks
    private TrayectoServiceImpl trayectoServiceImpl;

    @Mock
    private TrayectoRepository trayectoRepository;

    @Mock
    private AvionRepository avionRepository;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Mock
    private VueloRepository vueloRepository;

    @Test
    public void guardarTrayectoOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VuelosUtilityTest.ID_UNO)).willReturn(VuelosUtilityTest.VUELO_UNO);
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);
        given(trayectoRepository.save(any())).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getTrayId());
    }

    @Test
    public void guardarTrayectoNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.guardarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO));
    }
    @Test
    public void obtenerTrayectosOk() {
        given(trayectoRepository.findAll()).willReturn(TrayectoUtilityTest.TRAYECTOS);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, trayectosSavedDTO.size());
        assertEquals(TrayectoUtilityTest.ID_UNO, trayectosSavedDTO.get(0).getTrayId());
    }

    @Test
    public void obtenerTrayectosNotOk() {
        given(trayectoRepository.findAll()).willReturn(TrayectoUtilityTest.TRAYECTOS_VACIO);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, trayectosSavedDTO.size());
    }
    @Test
    public void obtenerTrayectosActivosOk() {
        given(trayectoRepository.findByEstado("Activo")).willReturn(TrayectoUtilityTest.TRAYECTOS);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectosActivos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_SIZE, trayectosSavedDTO.size());
        assertEquals(TrayectoUtilityTest.ID_UNO, trayectosSavedDTO.get(0).getTrayId());
    }

    @Test
    public void obtenerTrayectosActivosNotOk() {
        given(trayectoRepository.findByEstado("Activo")).willReturn(TrayectoUtilityTest.TRAYECTOS_VACIO);

        List<TrayectoDTO> trayectosSavedDTO = trayectoServiceImpl.obtenerTrayectosActivos();

        assertEquals(TrayectoUtilityTest.TRAYECTOS_VACIO_SIZE, trayectosSavedDTO.size());
    }
    @Test
    public void buscarPorId_TestOk_() throws Exception {
        avionRepository.save(AvionUtilityTest.AVION_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_DOS);
        vueloRepository.save(VuelosUtilityTest.VUELO_UNO);
        trayectoRepository.save(TrayectoUtilityTest.TRAYECTO_UNO);

        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);
        given(trayectoRepository.getReferenceById(TrayectoUtilityTest.ID_UNO)).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.buscarPorId(TrayectoUtilityTest.ID_UNO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getTrayId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.buscarPorId(TrayectoUtilityTest.ID_UNO));
    }
    @Test
    public void actualizarTrayectoOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_DOS)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_DOS)).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VuelosUtilityTest.ID_UNO)).willReturn(VuelosUtilityTest.VUELO_UNO);
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(true);
        given(trayectoRepository.save(any())).willReturn(TrayectoUtilityTest.TRAYECTO_UNO);

        TrayectoDTO trayectoSavedDTO = trayectoServiceImpl.modificarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO);

        assertEquals(TrayectoUtilityTest.ID_UNO, trayectoSavedDTO.getTrayId());
    }

    @Test
    public void actualizarTrayectoNotOk() {
        given(trayectoRepository.existsById(TrayectoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> trayectoServiceImpl.modificarTrayecto(TrayectoUtilityTest.TRAYECTODTO_UNO));
    }
}
