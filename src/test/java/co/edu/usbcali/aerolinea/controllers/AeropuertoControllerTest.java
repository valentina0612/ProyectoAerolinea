package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import co.edu.usbcali.aerolinea.utility.AeropuertoUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AeropuertoControllerTest {
    @Autowired
    private AeropuertoController aeropuertoController;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @MockBean
    private AeropuertoService aeropuertoService;

    @Test
    void guardarUsuario()throws Exception{
        when(aeropuertoService.guardarAeropuerto(any(AeropuertoDTO.class))).thenReturn(AeropuertoUtilityTest.AEROPUERTODTO);
        ResponseEntity<AeropuertoDTO> response = aeropuertoController.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO);
        verify(aeropuertoService).guardarAeropuerto(eq(AeropuertoUtilityTest.AEROPUERTODTO));
        assertTrue(response.getStatusCode().value() == HttpStatus.OK.value());
    }

}

