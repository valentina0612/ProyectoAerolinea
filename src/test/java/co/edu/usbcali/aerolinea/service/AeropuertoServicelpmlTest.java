package co.edu.usbcali.aerolinea.service;


import co.edu.usbcali.aerolinea.dtos.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.AeropuertoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AeropuertoServicelpmlTest {

    @Autowired
    private AeropuertoService aeropuertoService;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @Test
    void obtenerAeropuertos_TestOK_(){
        //assertEquals(2,1);
        Aeropuerto.builder()
                .aeroId(1)
                .nombre("cliente prueba")
                .iata("MEX")
                .ubicacion("paris")
                .estado("activo")
                .build();

        List<Aeropuerto> aeropuertoRetorno = Arrays.asList(Aeropuerto.builder()
                        .aeroId(1)
                        .nombre("cliente prueba")
                        .iata("MEX")
                        .ubicacion("paris")
                        .estado("activo")
                        .build(),
                Aeropuerto.builder()
                                .aeroId(2)
                                .nombre("cliente prueba2")
                                .iata("MEX")
                                .ubicacion("paris")
                                .estado("activo")
                                .build());

        Mockito.when(aeropuertoRepository.findAll()).thenReturn(aeropuertoRetorno);

        List<AeropuertoDTO> aeropuerto = aeropuertoService.obtenerAeropuertos();

        assertEquals(2, aeropuerto.size());
    }

    @Test
    public void obtenerAeropuertos_TestNotOk_() {
        List<Aeropuerto> aeropuertos = Arrays.asList();

        Mockito.when(aeropuertoRepository.findAll()).thenReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoService.obtenerAeropuertos();

        assertEquals(0, aeropuertosDTO.size());
    }


    @Test
    void buscarPorId_TestOk_() throws Exception {
        Aeropuerto aeropuertoPrueba =
                Aeropuerto.builder()
                        .aeroId(1)
                        .nombre("cliente prueba")
                        .iata("MEX")
                        .ubicacion("paris")
                        .estado("activo")
                        .build();

        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(true);
        Mockito.when(aeropuertoRepository.getReferenceById(1)).thenReturn(aeropuertoPrueba);

        // Llamado al método a probar
        AeropuertoDTO aeropuertoDTO = aeropuertoService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(aeropuertoDTO.getAeroId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoService.buscarPorId(1));
    }


}