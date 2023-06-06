package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import co.edu.usbcali.aerolinea.services.TipoAsientoServiceImpl;
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
public class TipoAsientoServicelpmlTest {

    @InjectMocks
    private TipoAsientoServiceImpl tipoAsientoServiceImpl;

    @Mock
    private TipoAsientoRepository tipoAsientoRepository;

    @Test
    public void guardarTipoAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(false);
        given(tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        assertEquals(TipoAsientoUtilityTest.ID_UNO, tipoAsientoSavedDTO.getTiasId());
    }

    @Test
    public void guardarTipoAsientoNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> tipoAsientoServiceImpl.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO));
    }
    @Test
    public void obtenerTipoAsientosOk() {
        given(tipoAsientoRepository.findAll()).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsiento();

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_SIZE, tipoAsientosSavedDTO.size());
        assertEquals(TipoAsientoUtilityTest.DESCRIPCION_UNO, tipoAsientosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerTipoAsientosNotOk() {
        given(tipoAsientoRepository.findAll()).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsiento();

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO_SIZE, tipoAsientosSavedDTO.size());
    }

    @Test
    public void obtenerTipoAsientosActivosOk() {
        given(tipoAsientoRepository.findByEstado("Activo")).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientosActivos();

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_SIZE, tipoAsientosSavedDTO.size());
        assertEquals(TipoAsientoUtilityTest.DESCRIPCION_UNO, tipoAsientosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerTipoAsientosActivosNotOk() {
        given(tipoAsientoRepository.findByEstado("Activo")).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientosActivos();

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO_SIZE, tipoAsientosSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(true);
        given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.ID_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.buscarPorId(TipoAsientoUtilityTest.ID_UNO);

        assertEquals(TipoAsientoUtilityTest.DESCRIPCION_UNO, tipoAsientoSavedDTO.getDescripcion());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(Exception.class, () -> tipoAsientoServiceImpl.buscarPorId(TipoAsientoUtilityTest.ID_UNO));
    }
    @Test
    public void actualizarTipoAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(true);
        given(tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.modificarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO);

        assertEquals(TipoAsientoUtilityTest.ID_UNO, tipoAsientoSavedDTO.getTiasId());
    }

    @Test
    public void actualizarTipoAsientoNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> tipoAsientoServiceImpl.modificarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO_UNO));
    }

}
