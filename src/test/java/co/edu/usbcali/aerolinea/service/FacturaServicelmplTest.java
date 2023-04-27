package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.services.FacturaService;
import co.edu.usbcali.aerolinea.services.UsuarioService;
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
public class FacturaServicelmplTest {
    @Autowired
    private FacturaService facturaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private FacturaRepository facturaRepository;

    @Test
    void obtenerFacturas_TestOK_() throws Exception {
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

        Reserva reserva = Reserva.builder()
                .reseId(1)
                .vuelo(vuelo)
                .asiento(asiento)
                .usuario(usuario)
                .precioTotal(234)
                .estadoPago("pagado")
                .fecha("2020-07-23")
                .estado("activo")
                .build();

        Factura.builder()
                .factId(1)
                .reserva(reserva)
                .fecha("2020-07-23")
                .estado("activo")
                .build();

        List<Factura> facturaRetorno = Arrays.asList(Factura.builder()
                        .factId(2)
                        .reserva(reserva)
                        .fecha("2020-07-23")
                        .estado("activo")
                        .build(),
                Factura.builder()
                        .factId(3)
                        .reserva(reserva)
                        .fecha("2020-07-23")
                        .estado("activo")
                        .build());

        // Mock de la respuesta del repositorio
        Mockito.when(facturaRepository.findAll()).thenReturn(facturaRetorno);

        List<FacturaDTO> factura = facturaService.obtenerFacturas();

        assertEquals(2, factura.size());
    }

    @Test
    public void obtenerFacturas_TestNotOk() {
        List<Factura> factura = Arrays.asList();

        Mockito.when(facturaRepository.findAll()).thenReturn(factura);

        List<FacturaDTO> facturaDTOS = facturaService.obtenerFacturas();

        assertEquals(0, facturaDTOS.size());
    }

    @Test
    void buscarPorId() throws Exception {
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

        Reserva reserva = Reserva.builder()
                .reseId(1)
                .vuelo(vuelo)
                .asiento(asiento)
                .usuario(usuario)
                .precioTotal(234)
                .estadoPago("pagado")
                .fecha("2020-07-23")
                .estado("activo")
                .build();

        Factura facturaPrueba =Factura.builder()
                .factId(1)
                .reserva(reserva)
                .fecha("2020-07-23")
                .estado("activo")
                .build();



        // Mock de la respuesta del repositorio
        Mockito.when(facturaRepository.existsById(1)).thenReturn(true);
        Mockito.when(facturaRepository.getReferenceById(1)).thenReturn(facturaPrueba);

        // Llamado al método a probar
        FacturaDTO facturaDTO = facturaService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(facturaDTO.getFactId(), 1);
    }

    @Test
    public void buscarPorIdNotOk() {
        Mockito.when(facturaRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> facturaService.buscarPorId(1));
    }
}
