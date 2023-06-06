package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.ReservaService;

import co.edu.usbcali.aerolinea.services.ReservaServiceImpl;
import co.edu.usbcali.aerolinea.utility.AsientoUtilityTest;
import co.edu.usbcali.aerolinea.utility.ReservaUtilityTest;
import co.edu.usbcali.aerolinea.utility.UsuarioUtilityTest;
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
public class ReservaServicelpmlTest {
    @InjectMocks
    private ReservaServiceImpl reservaServiceImpl;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private VueloRepository vueloRepository;

    @Mock
    private AsientoRepository asientoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarReservaOk() throws Exception {
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VuelosUtilityTest.ID_UNO)).willReturn(VuelosUtilityTest.VUELO_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(true);
        given(asientoRepository.getReferenceById(AsientoUtilityTest.ID_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.ID_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);
        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(false);
        given(reservaRepository.save(any())).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.guardarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        assertEquals(ReservaUtilityTest.ID_UNO, reservaSavedDTO.getReseId());
    }

    @Test
    public void guardarReservaNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.guardarReserva(ReservaUtilityTest.RESERVADTO_UNO));
    }
    @Test
    public void obtenerReservasOk() {
        given(reservaRepository.findAll()).willReturn(ReservaUtilityTest.RESERVAS);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservas();

        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, reservasSavedDTO.size());
        assertEquals(ReservaUtilityTest.PRECIO_TOTAL_UNO, reservasSavedDTO.get(0).getPrecioTotal());
    }

    @Test
    public void obtenerReservasNotOk() {
        given(reservaRepository.findAll()).willReturn(ReservaUtilityTest.RESERVAS_VACIO);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservas();

        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, reservasSavedDTO.size());
    }
    @Test
    public void obtenerReservasActivasOk() {
        given(reservaRepository.findByEstado("Activo")).willReturn(ReservaUtilityTest.RESERVAS);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservasActivas();

        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, reservasSavedDTO.size());
        assertEquals(ReservaUtilityTest.PRECIO_TOTAL_UNO, reservasSavedDTO.get(0).getPrecioTotal());
    }

    @Test
    public void obtenerReservasActivasNotOk() {
        given(reservaRepository.findByEstado("Activo")).willReturn(ReservaUtilityTest.RESERVAS_VACIO);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservasActivas();

        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, reservasSavedDTO.size());
    }

    @Test
    public void obtenerReservaPorIdOk() throws Exception {
        vueloRepository.save(VuelosUtilityTest.VUELO_UNO);
        asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);
        reservaRepository.save(ReservaUtilityTest.RESERVA_UNO);

        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(true);
        given(reservaRepository.getReferenceById(ReservaUtilityTest.ID_UNO)).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.buscarPorId(ReservaUtilityTest.ID_UNO);

        assertEquals(ReservaUtilityTest.ID_UNO, reservaSavedDTO.getReseId());
    }

    @Test
    public void obtenerReservaPorIdNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.buscarPorId(ReservaUtilityTest.ID_UNO));
    }

    @Test
    public void actualizarReservaOk() throws Exception {
        given(vueloRepository.existsById(VuelosUtilityTest.ID_UNO)).willReturn(true);
        given(vueloRepository.getReferenceById(VuelosUtilityTest.ID_UNO)).willReturn(VuelosUtilityTest.VUELO_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ID_UNO)).willReturn(true);
        given(asientoRepository.getReferenceById(AsientoUtilityTest.ID_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.ID_UNO)).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.ID_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);
        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(true);
        given(reservaRepository.save(any())).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.modificarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        assertEquals(ReservaUtilityTest.ID_UNO, reservaSavedDTO.getReseId());
    }
    @Test
    public void actualizarReservaNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.modificarReserva(ReservaUtilityTest.RESERVADTO_UNO));
    }

}
