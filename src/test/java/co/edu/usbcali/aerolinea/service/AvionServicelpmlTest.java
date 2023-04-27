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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AvionServicelpmlTest {

    @Autowired
    private AvionService avionService;

    @MockBean
    private AvionRepository avionRepository;

    @Test
    void obtenerAviones_TestOK_(){

          Avion avionPrueba =
                Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        List<Avion> avionRetorno = Arrays.asList(Avion.builder()
                        .avioID(2)
                        .modelo("bvv2")
                        .estado("activo")
                        .build(),
                Avion.builder()
                            .avioID(3)
                            .modelo("bvv2")
                            .estado("activo")
                            .build());

        Mockito.when(avionRepository.findAll()).thenReturn(avionRetorno);

        List<AvionDTO> avion = avionService.obtenerAviones();

        assertEquals(2, avion.size());
    }

    @Test
    public void obtenerAviones_TestNotOk_() {
        List<Avion> avion = Arrays.asList();

        Mockito.when(avionRepository.findAll()).thenReturn(avion);

        List<AvionDTO> avionDTOS  = avionService.obtenerAviones();

        assertEquals(0, avionDTOS.size());
    }

    @Test
    void buscarPorId_TestOk_() throws Exception {
            Avion avionPrueba =
                    Avion.builder()
                            .avioID(1)
                            .modelo("bvv2")
                            .estado("activo")
                            .build();

        Mockito.when(avionRepository.existsById(1)).thenReturn(true);
        Mockito.when(avionRepository.getReferenceById(1)).thenReturn(avionPrueba);

        // Llamado al método a probar
        AvionDTO avionDTO = avionService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(avionDTO.getAvioID(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(avionRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionService.buscarPorId(1));
    }
}
