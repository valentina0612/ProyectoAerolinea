package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.VueloDTO;
import co.edu.usbcali.aerolinea.model.Aeropuerto;
import co.edu.usbcali.aerolinea.model.Vuelo;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.services.VuelosService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class VuelosServicelpmlTest {

    @Autowired
    private VuelosService vuelosService;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @MockBean
    VueloRepository vueloRepository;


    @Test
    void obtenerVuelos_TestOK_() throws Exception {
        Aeropuerto aeropuerto = Aeropuerto.builder().aeroId(1).build();
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

        List<Vuelo> vueloRetorno = Arrays.asList(Vuelo.builder()
                    .vueloId(2)
                    .aeropuertoOrigen(aeropuerto)
                    .aeropuertoDestino(aeropuerto)
                    .precio(3243)
                    .hora_salida("12:00")
                    .hora_llegada("12:23")
                    .precioAsientoVip(3223)
                    .precioAsientoNormal(2321)
                    .precioAsientoBasico(2134)
                    .estado("activo")
                    .build(),
                Vuelo.builder()
                        .vueloId(3)
                        .aeropuertoOrigen(aeropuerto)
                        .aeropuertoDestino(aeropuerto)
                        .precio(3243)
                        .hora_salida("12:00")
                        .hora_llegada("12:23")
                        .precioAsientoVip(3223)
                        .precioAsientoNormal(2321)
                        .precioAsientoBasico(2134)
                        .estado("activo")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(vueloRepository.findAll()).thenReturn(vueloRetorno);

        List<VueloDTO> vuelo = vuelosService.obtenerVuelos();

        assertEquals(2, vuelo.size());


    }

    @Test
    void buscarPorId() throws Exception {
        Aeropuerto aeropuerto = Aeropuerto.builder().aeroId(1).build();
        Vuelo vueloPrueba = Vuelo.builder()
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

        // Mock de la respuesta del repositorio
        Mockito.when(vueloRepository.existsById(1)).thenReturn(true);
        Mockito.when(vueloRepository.getReferenceById(1)).thenReturn(vueloPrueba);

        // Llamado al método a probar
        VueloDTO vueloDTO = vuelosService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(vueloDTO.getVueloId(), 1);
    }
}
