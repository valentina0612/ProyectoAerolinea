package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.services.FacturaService;
import co.edu.usbcali.aerolinea.services.FacturaServiceImpl;
import co.edu.usbcali.aerolinea.services.UsuarioService;
import co.edu.usbcali.aerolinea.utility.FacturaUtilityTest;
import co.edu.usbcali.aerolinea.utility.ReservaUtilityTest;
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
public class FacturaServicelmplTest {
    @InjectMocks
    private FacturaServiceImpl facturaServiceImpl;

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    public void obtenerFacturasOk() {
        given(facturaRepository.findAll()).willReturn(FacturaUtilityTest.FACTURAS);

        List<FacturaDTO> facturasSavedDTO = facturaServiceImpl.obtenerFacturas();

        assertEquals(FacturaUtilityTest.FACTURAS_SIZE, facturasSavedDTO.size());
        assertEquals(FacturaUtilityTest.ID_UNO, facturasSavedDTO.get(0).getFactId());
    }

    @Test
    public void obtenerFacturasNotOk() {
        given(facturaRepository.findAll()).willReturn(FacturaUtilityTest.FACTURAS_VACIO);

        List<FacturaDTO> facturasSavedDTO = facturaServiceImpl.obtenerFacturas();

        assertEquals(FacturaUtilityTest.FACTURAS_VACIO_SIZE, facturasSavedDTO.size());
    }

    @Test
    public void buscarPorId_TestOk_() throws Exception {
        reservaRepository.save(ReservaUtilityTest.RESERVA_UNO);
        facturaRepository.save(FacturaUtilityTest.FACTURA_UNO);

        given(facturaRepository.existsById(FacturaUtilityTest.ID_UNO)).willReturn(true);
        given(facturaRepository.getReferenceById(FacturaUtilityTest.ID_UNO)).willReturn(FacturaUtilityTest.FACTURA_UNO);

        FacturaDTO facturaSavedDTO = facturaServiceImpl.buscarPorId(FacturaUtilityTest.ID_UNO);

        assertEquals(FacturaUtilityTest.ID_UNO, facturaSavedDTO.getFactId());
    }

    @Test
    public void buscarPorIdPorIdNotOk() {
        given(facturaRepository.existsById(FacturaUtilityTest.ID_UNO)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> facturaServiceImpl.buscarPorId(FacturaUtilityTest.ID_UNO));
    }

}
