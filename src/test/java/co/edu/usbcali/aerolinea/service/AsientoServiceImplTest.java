package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.AsientoDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Asiento;
import co.edu.usbcali.aerolinea.model.Avion;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.AsientoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.services.AsientoService;
import co.edu.usbcali.aerolinea.services.AsientoServiceImpl;
import co.edu.usbcali.aerolinea.utility.AsientoUtilityTest;
import co.edu.usbcali.aerolinea.utility.AvionUtilityTest;
import co.edu.usbcali.aerolinea.utility.TipoAsientoUtilityTest;
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
    public class AsientoServiceImplTest {
        @InjectMocks
        private AsientoServiceImpl asientoServiceImpl;

        @Mock
        private AsientoRepository asientoRepository;

        @Mock
        private AvionRepository avionRepository ;

        @Mock
        private TipoAsientoRepository tipoAsientoRepository;

        @Test
        public void guardarAsientoOk() throws Exception {
            given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(true);
            given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.ID_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(false);
            given(asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);
            given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
            given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);


            AsientoDTO asientoSavedDTO = asientoServiceImpl.guardarAsiento(AsientoUtilityTest.ASIENTODTO_UNO);

            assertEquals(AsientoUtilityTest.ID_UNO, asientoSavedDTO.getAsieId());
        }

        @Test
        public void guardarAsientoNotOk() {
            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(true);

            assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.guardarAsiento(AsientoUtilityTest.ASIENTODTO_UNO));
        }

        @Test
        public void obtenerAsientosOk() {
            given(asientoRepository.findAll()).willReturn(AsientoUtilityTest.ASIENTOS);

            List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientos();

            assertEquals(AsientoUtilityTest.ASIENTOS_SIZE, asientosSavedDTO.size());
            assertEquals(AsientoUtilityTest.UBICACION_UNO, asientosSavedDTO.get(0).getUbicacion());
        }

        @Test
        public void obtenerAsientosNotOk() {
            given(asientoRepository.findAll()).willReturn(AsientoUtilityTest.ASIENTOS_VACIO);

            List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientos();

            assertEquals(AsientoUtilityTest.ASIENTOS_VACIO_SIZE, asientosSavedDTO.size());
        }

        @Test
        public void obtenerAsientosActivosOk() {
            given(asientoRepository.findAllByEstado("Activo")).willReturn(AsientoUtilityTest.ASIENTOS);

            List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientosActivos();

            assertEquals(AsientoUtilityTest.ASIENTOS_SIZE, asientosSavedDTO.size());
            assertEquals(AsientoUtilityTest.UBICACION_UNO, asientosSavedDTO.get(0).getUbicacion());
        }

        @Test
        public void obtenerAsientosActivosNotOk() {
            given(asientoRepository.findAllByEstado("Activo")).willReturn(AsientoUtilityTest.ASIENTOS_VACIO);

            List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientosActivos();

            assertEquals(AsientoUtilityTest.ASIENTOS_VACIO_SIZE, asientosSavedDTO.size());
        }

        @Test
        public void buscarPorId_TestOk_() throws Exception {
            tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
            asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO);

            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(true);
            given(asientoRepository.getReferenceById(AsientoUtilityTest.ID_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);

            AsientoDTO asientoSavedDTO = asientoServiceImpl.buscarPorId(AsientoUtilityTest.ID_UNO);

            assertEquals(AsientoUtilityTest.ID_UNO, asientoSavedDTO.getAsieId());
        }

        @Test
        public void buscarPorIdPorIdNotOk() {
            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(false);

            assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.buscarPorId(AsientoUtilityTest.ID_UNO));
        }

        @Test
        public void actualizarAsientoOk() throws Exception {
            given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(true);
            given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.ID_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(true);
            given(asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);
            given(avionRepository.existsById(AvionUtilityTest.ID_UNO)).willReturn(true);
            given(avionRepository.getReferenceById(AvionUtilityTest.ID_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

            AsientoDTO asientoSavedDTO = asientoServiceImpl.modificarAsiento(AsientoUtilityTest.ASIENTODTO_UNO);

            assertEquals(AsientoUtilityTest.ID_UNO, asientoSavedDTO.getAsieId());
        }

        @Test
        public void actualizarAsientoNotOk() {
            given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(false);

            assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.modificarAsiento(AsientoUtilityTest.ASIENTODTO_UNO));
        }
    }
