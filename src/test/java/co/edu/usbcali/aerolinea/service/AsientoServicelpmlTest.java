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
public class AsientoServicelpmlTest{

    @Autowired
    private AsientoService asientoService;

    @MockBean
    private TipoAsientoRepository tipoAsientoRepository;

    @MockBean
    private AvionRepository avionRepository;
    @MockBean
    AsientoRepository asientoRepository;


    @Test
    void obtenerAsientos_TestOK_() throws Exception {
        TipoAsiento tipoAsiento =
                TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        Avion avion =
                Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();

        List<Asiento> asientoRetorno = Arrays.asList(Asiento.builder()
                    .asieId(2)
                    .tipoAsiento(tipoAsiento)
                    .avion(avion)
                    .ubicacion("DSADA")
                    .precio(2323)
                    .estado("activo")
                    .build(),
                Asiento.builder()
                        .asieId(3)
                        .tipoAsiento(tipoAsiento)
                        .avion(avion)
                        .ubicacion("DSADA")
                        .precio(2323)
                        .estado("activo")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(asientoRepository.findAll()).thenReturn(asientoRetorno);

        List<AsientoDTO> asiento = asientoService.obtenerAsientos();

        assertEquals(2, asiento.size());


    }
    @Test
    public void obtenerAsientos_TestNotOk_() {
        List<Asiento> asiento = Arrays.asList();

        Mockito.when(asientoRepository.findAll()).thenReturn(asiento);

        List<AsientoDTO> asientoDTOS  = asientoService.obtenerAsientos();

        assertEquals(0, asientoDTOS.size());
    }

    @Test
    void buscarPorId_TestOK_() throws Exception {
        TipoAsiento tipoAsiento = TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        Avion avion = Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        Asiento asientoPrueba = Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();

        // Mock de la respuesta del repositorio
        Mockito.when(asientoRepository.existsById(1)).thenReturn(true);
        Mockito.when(asientoRepository.getReferenceById(1)).thenReturn(asientoPrueba);

        // Llamado al método a probar
        AsientoDTO asientoDTO = asientoService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(asientoDTO.getAsieId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(asientoRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> asientoService.buscarPorId(1));
    }


}
