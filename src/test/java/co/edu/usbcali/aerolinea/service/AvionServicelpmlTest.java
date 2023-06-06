package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.dtos.AvionDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.services.AvionService;
import co.edu.usbcali.aerolinea.services.AvionServiceImpl;
import co.edu.usbcali.aerolinea.utility.AvionUtilityTest;
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
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AvionServicelpmlTest {
    @InjectMocks
    private AvionServiceImpl avionServiceImpl;

    @Mock
    private AvionRepository avionRepository;

    @Test
    public void guardarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO_UNO);

        assertEquals(AvionUtilityTest.ID_UNO, avionSavedDTO.getAvioID());
    }

    @Test
    public void guardarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO_UNO));
    }

    @Test
    public void obtenerAvionesOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAviones();

        assertEquals(AvionUtilityTest.AVIONES_SIZE, avionesSavedDTO.size());
        assertEquals(AvionUtilityTest.MODELO_UNO, avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesNotOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesDTO = avionServiceImpl.obtenerAviones();

        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, avionesDTO.size());
    }

    @Test
    public void obtenerAvionesActivosOk() {
        given(avionRepository.findAllByEstado("Activo")).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(AvionUtilityTest.AVIONES_SIZE, avionesSavedDTO.size());
        assertEquals(AvionUtilityTest.MODELO_UNO, avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesActivosNotOk() {
        given(avionRepository.findAllByEstado("Activo")).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, avionesSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        avionRepository.save(AvionUtilityTest.AVION_UNO);

        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionDTO = avionServiceImpl.buscarPorId(AvionUtilityTest.ID_UNO);

        assertEquals(AvionUtilityTest.ID_UNO, avionDTO.getAvioID());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.buscarPorId(AvionUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.modificarAvion(AvionUtilityTest.AVIONDTO_UNO);

        assertEquals(AvionUtilityTest.ID_UNO, avionSavedDTO.getAvioID());
    }

    @Test
    public void actualizarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.modificarAvion(AvionUtilityTest.AVIONDTO_UNO));
    }
}
