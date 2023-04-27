package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.*;
import co.edu.usbcali.aerolinea.services.ReservaService;

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
public class ReservaServicelpmlTest {
    @Autowired
    private ReservaService reservaService;

    @MockBean
    private VueloRepository vueloRepository;

    @MockBean
    private AvionRepository avionRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;


    @MockBean
    private  ReservaRepository reservaRepository;


    @Test
    void obtenerUsuarios_TestOK_(){

        RolUsuario rolUsuario =
                RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        Usuario usuario = Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        Aeropuerto aeropuerto = Aeropuerto.builder()
                .aeroId(1)
                .nombre("cliente prueba")
                .iata("MEX")
                .ubicacion("paris")
                .estado("activo")
                .build();

        Vuelo vuelo = Vuelo.builder()
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

        TipoAsiento tipoAsiento =
                TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        Avion avion = Avion.builder()
                        .avioID(1)
                        .modelo("bvv2")
                        .estado("activo")
                        .build();

        Asiento asiento = Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();


        Reserva.builder()
                .reseId(1)
                .vuelo(vuelo)
                .asiento(asiento)
                .usuario(usuario)
                .precioTotal(234)
                .estadoPago("pagado")
                .fecha("2020-07-23")
                .estado("activo")
                .build();

        List<Reserva> reservaRetorno = Arrays.asList(Reserva.builder()
                .reseId(2)
                .vuelo(vuelo)
                .asiento(asiento)
                .usuario(usuario)
                .precioTotal(234)
                .estadoPago("pagado")
                .fecha("2020-07-23")
                .estado("activo")
                .build(),
        Reserva.builder()
                        .reseId(3)
                        .vuelo(vuelo)
                        .asiento(asiento)
                        .usuario(usuario)
                        .precioTotal(234)
                        .estadoPago("pagado")
                        .fecha("2020-07-23")
                        .estado("activo")
                        .build());

        Mockito.when(reservaRepository.findAll()).thenReturn(reservaRetorno);

        List<ReservaDTO> reserva = reservaService.obtenerReservas();

        assertEquals(2, reserva.size());
    }

    @Test
    public void obtenerReserva_TestNotOk_() {
        List<Reserva> reserva = Arrays.asList();

        Mockito.when(reservaRepository.findAll()).thenReturn(reserva);

        List<ReservaDTO>  reservaDTOS = reservaService.obtenerReservas();

        assertEquals(0, reservaDTOS.size());
    }

    @Test
    void buscarPorId_TestOk_() throws Exception {
        RolUsuario rolUsuario =
                RolUsuario.builder()
                        .rousId(1)
                        .descripcion("asdsa")
                        .estado("activo")
                        .build();

        Usuario usuario = Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        Aeropuerto aeropuerto = Aeropuerto.builder()
                .aeroId(1)
                .nombre("cliente prueba")
                .iata("MEX")
                .ubicacion("paris")
                .estado("activo")
                .build();

        Vuelo vuelo = Vuelo.builder()
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

        TipoAsiento tipoAsiento =
                TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        Avion avion = Avion.builder()
                .avioID(1)
                .modelo("bvv2")
                .estado("activo")
                .build();

        Asiento asiento = Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();


        Reserva reservaPrueba = Reserva.builder()
                .reseId(1)
                .vuelo(vuelo)
                .asiento(asiento)
                .usuario(usuario)
                .precioTotal(234)
                .estadoPago("pagado")
                .fecha("2020-07-23")
                .estado("activo")
                .build();


        Mockito.when(reservaRepository.existsById(1)).thenReturn(true);
        Mockito.when(reservaRepository.getReferenceById(1)).thenReturn(reservaPrueba);

        // Llamado al método a probar
        ReservaDTO reservaDTO = reservaService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(reservaDTO.getReseId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(reservaRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> reservaService.buscarPorId(1));
    }
}
