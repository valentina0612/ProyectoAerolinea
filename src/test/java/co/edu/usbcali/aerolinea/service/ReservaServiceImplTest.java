package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.ReservaDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.services.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ReservaServiceImplTest {
    @Autowired
    private ReservaService reservaService;
    @MockBean
    private ReservaRepository reservaRepository;
    @Test
    public void obtenerReservasPositivo(){
        Aeropuerto aeropuerto=Aeropuerto.builder()
                .aeroId(1)
                .nombre("cliente prueba")
                .iata("MEX")
                .ubicacion("paris")
                .estado("activo")
                .build();
        Vuelo vuelo= Vuelo.builder()
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

        TipoAsiento tipoAsiento=TipoAsiento.builder()
                .tiasId(1)
                .descripcion("sada")
                .estado("activo")
                .build();

        Avion avion=Avion.builder()
                .avioID(1)
                .modelo("bvv2")
                .estado("activo")
                .build();

        Asiento asiento=Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();

        RolUsuario rolUsuario= RolUsuario.builder()
                .rousId(1)
                .descripcion("asdsa")
                .estado("activo")
                .build();

        Usuario usuario= Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .descripcion("asdasd")
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        Reserva.builder()
                .reseId(1)
                .vuelId(1)
                .asieId(1)
                .usuaId(1)
                .precioTotal(1000)
                .estadoPago("pagado")
                .fecha(new Date())
                .estado("activo")
                .build();

        List<Reserva>reservas= Arrays.asList(Reserva.builder()
                .reseId(1)
                .vuelId(1)
                .asieId(1)
                .usuaId(1)
                .precioTotal(1000)
                .estadoPago("activo")
                .fecha(new Date())
                .estado("activo")
                .build(),

           Reserva.builder().reseId(2)
                   .vuelId(2)
                   .asieId(2)
                   .usuaId(2)
                   .precioTotal(1200)
                   .estadoPago("pagado")
                   .fecha(new Date())
                   .estado("activo")
                   .build());

        Mockito.when(reservaRepository.findAll()).thenReturn(reservas);
        List<ReservaDTO>reservasDTO=reservaService.obtenerReservas();
        assertEquals(2, reservasDTO.size());
        assertEquals(1000, reservasDTO.get(0).getPrecioTotal());


    }
    @Test
    public void obtenerReservasNegativo(){
        List<Reserva>reservas=Arrays.asList();
        Mockito.when(reservaRepository.findAll()).thenReturn(reservas);
        List<ReservaDTO>reservasDTO=reservaService.obtenerReservas();
        assertEquals(0, reservasDTO.size());
    }

    @Test
    public void obtenerReservaPorIdPositivo() throws Exception{
        Aeropuerto aeropuerto=Aeropuerto.builder()
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

        TipoAsiento tipoAsiento=TipoAsiento.builder()
                .tiasId(1)
                .descripcion("sada")
                .estado("activo")
                .build();

        Avion avion=Avion.builder()
                .avioID(1)
                .modelo("bvv2")
                .estado("activo")
                .build();

        Asiento asiento=Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();
        RolUsuario rolUsuario= RolUsuario.builder()
                .rousId(1)
                .descripcion("asdsa")
                .estado("activo")
                .build();
        Usuario usuario= Usuario.builder()
                .usuaId(1)
                .rolUsuario(rolUsuario)
                .descripcion("asdasd")
                .cedula("121323")
                .nombre("brayan")
                .apellido("zamora")
                .correo("brayanzamora@gmail.com")
                .estado("activo")
                .build();

        Reserva reserva=Reserva.builder()
                .reseId(1)
                .vuelId(1)
                .asieId(1)
                .usuaId(1)
                .precioTotal(10000)
                .estadoPago("pagado")
                .fecha(new Date())
                .estado("activo")
                .build();

        Mockito.when(reservaRepository.existsById(1)).thenReturn(true);
        Mockito.when(reservaRepository.getReferenceById(1)).thenReturn(reserva);
        ReservaDTO reservaDTO=(ReservaDTO) reservaService.obtenerReservas();
        assertEquals(1, reservaDTO.getUsuaId());

    }
    @Test
    public void obtenerReservaPorIdNegativo(){
        Mockito.when(reservaRepository.existsById(1)).thenReturn(false);
        assertThrows(java.lang.Exception.class, () -> reservaService.obtenerReservas());
    }

}
