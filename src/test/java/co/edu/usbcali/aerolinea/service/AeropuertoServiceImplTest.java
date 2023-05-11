package co.edu.usbcali.aerolinea.service;


import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import co.edu.usbcali.aerolinea.services.AeropuertoServiceImpl;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AeropuertoServiceImplTest {
    @InjectMocks
    private AeropuertoServiceImpl aeropuertoServiceImpl;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Test
    public void guardarAeropuertoNotOk() {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO));
    }

    @Test
    public void obtenerAeropuertosOk() {
        given(aeropuertoRepository.findAll()).willReturn(AeropuertoUtilityTest.AEROPUERTOS);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_SIZE, aeropuertosSavedDTO.size());
        assertEquals(AeropuertoUtilityTest.NOMBRE_UNO, aeropuertosSavedDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosNotOk() {
        given(aeropuertoRepository.findAll()).willReturn(AeropuertoUtilityTest.AEROPUERTOS_VACIO);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(AeropuertoUtilityTest.AEROPUERTOS_VACIO_SIZE, aeropuertosSavedDTO.size());
    }


    @Test
    public void buscarPorId_TestOk_() throws Exception {
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);

        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.ID_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);

        AeropuertoDTO aeropuertoSavedDTO = aeropuertoServiceImpl.buscarPorId(AeropuertoUtilityTest.ID_UNO);

        assertEquals(AeropuertoUtilityTest.ID_UNO, aeropuertoSavedDTO.getAeroId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.buscarPorId(AeropuertoUtilityTest.ID_UNO));
    }

}