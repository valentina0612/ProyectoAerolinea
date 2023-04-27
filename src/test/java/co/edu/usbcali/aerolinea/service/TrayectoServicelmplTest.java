package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.dtos.TrayectoDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.repository.TrayectoRepository;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import co.edu.usbcali.aerolinea.services.TrayectoService;
import co.edu.usbcali.aerolinea.services.VuelosService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TrayectoServicelmplTest {
    @Autowired
    private TrayectoService trayectoService;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @MockBean
    private AvionRepository avionRepository;

    @MockBean
    private VuelosService vuelosService;

    @MockBean
    private TrayectoRepository trayectoRepository;


    @Test
    void obtenerTrayectos_TestOK_(){

        Aeropuerto aeropuerto =
                Aeropuerto.builder()
                        .aeroId(1)
                        .nombre("cliente prueba")
                        .iata("MEX")
                        .ubicacion("paris")
                        .estado("activo")
                        .build();

        Avion avion =
                Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        Vuelo vuelo =
                Vuelo.builder()
                        .vueloId(1)
                        .aeropuertoOrigen(aeropuerto)
                        .aeropuertoDestino(aeropuerto)
                        .precio(3243)
                        .hora_salida("12:00")
                        .hora_llegada("12:23")
                        .precioAsientoVip(3223)
                        .precioAsientoNormal(2321)
                        .precioAsientoBasico(2134)
                        .estado("activo")
                        .build();

        Trayecto.builder()
                .trayId(1)
                .avion(avion)
                .aeropuerto(aeropuerto)
                .aeropuerto2(aeropuerto)
                .horaSalida("2020-02-20")
                .horaLlegada("2020-05-20")
                .vuelo(vuelo)
                .estado("Activo")
                .build();

        List<Trayecto> trayectoRetorno = Arrays.asList(Trayecto.builder()
                        .trayId(2)
                        .avion(avion)
                        .aeropuerto(aeropuerto)
                        .aeropuerto2(aeropuerto)
                        .horaSalida("2020-02-20")
                        .horaLlegada("2020-05-20")
                        .vuelo(vuelo)
                        .estado("Activo")
                        .build());
                Trayecto.builder()
                            .trayId(3)
                            .avion(avion)
                            .aeropuerto(aeropuerto)
                            .aeropuerto2(aeropuerto)
                            .horaSalida("2020-02-25")
                            .horaLlegada("2020-07-23")
                            .vuelo(vuelo)
                            .estado("Activo")
                            .build();

        Mockito.when(trayectoRepository.findAll()).thenReturn(trayectoRetorno);

        List<TrayectoDTO> trayecto = trayectoService.obtenerTrayectos();

        assertEquals(1, trayecto.size());
    }

    @Test
    public void obtenerTrayecto_TestNotOk_() {
        List<Trayecto> trayecto = Arrays.asList();

        Mockito.when(trayectoRepository.findAll()).thenReturn(trayecto);

        List<TrayectoDTO> trayectoDTO  = trayectoService.obtenerTrayectos();

        assertEquals(0, trayectoDTO.size());
    }

    @Test
    void buscarPorId_TestOk_() throws Exception {
        Aeropuerto aeropuerto =
                Aeropuerto.builder()
                        .aeroId(1)
                        .nombre("cliente prueba")
                        .iata("MEX")
                        .ubicacion("paris")
                        .estado("activo")
                        .build();

        Avion avion =
                Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        Vuelo vuelo =
                Vuelo.builder()
                        .vueloId(1)
                        .aeropuertoOrigen(aeropuerto)
                        .aeropuertoDestino(aeropuerto)
                        .precio(3243)
                        .hora_salida("12:00")
                        .hora_llegada("12:23")
                        .precioAsientoVip(3223)
                        .precioAsientoNormal(2321)
                        .precioAsientoBasico(2134)
                        .estado("activo")
                        .build();

        Trayecto trayectoPrueba = Trayecto.builder()
                        .trayId(1)
                        .avion(avion)
                        .aeropuerto(aeropuerto)
                        .aeropuerto2(aeropuerto)
                        .horaSalida("2020-02-20")
                        .horaLlegada("2020-05-20")
                        .vuelo(vuelo)
                        .estado("Activo")
                        .build();

        Mockito.when(trayectoRepository.existsById(1)).thenReturn(true);
        Mockito.when(trayectoRepository.getReferenceById(1)).thenReturn(trayectoPrueba);

        // Llamado al método a probar
        TrayectoDTO trayectoDTO = trayectoService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(trayectoDTO.getTrayId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(trayectoRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> trayectoService.buscarPorId(1));
    }
}
