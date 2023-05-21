package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.exceptions.AeropuertoExceptions;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class AeropuertoControllerTest {
    @Mock //Objeto Mock de Mockito
    private AeropuertoService aeropuertoService;

    @InjectMocks //Setea un controlador para inyectar los objetos de mockito en él
    private AeropuertoController aeropuertoController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); //Inicializa el controlador y los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(aeropuertoController).build();
    }
}