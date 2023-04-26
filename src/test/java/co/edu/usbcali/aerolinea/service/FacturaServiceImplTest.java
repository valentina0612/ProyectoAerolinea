package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.FacturaDTO;
import co.edu.usbcali.aerolinea.model.*;
import co.edu.usbcali.aerolinea.repository.FacturaRepository;
import co.edu.usbcali.aerolinea.services.FacturaService;
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
public class FacturaServiceImplTest {
    @Autowired
    private FacturaService facturaService;

    @MockBean
    private FacturaRepository facturaRepository;

    @Test
    public void obtenerFacturasPositivo(){
        Aeropuerto aeropuerto = Aeropuerto.builder()
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
                .precioAsientoBasico(2321)
                .precioAsientoNormal(2134)
                .estado("activo")
                .build();

        TipoAsiento tipoAsiento=TipoAsiento.builder()
                .tiasId(1)
                .descripcion("sada")
                .estado("activo")
                .build();

        Avion avion= Avion.builder()
                .avioID(1)
                .modelo("bvv2")
                .estado("activo")
                .build();
        Asiento asiento =Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .precio(2323)
                .estado("activo")
                .ubicacion("DSADA")
                .build();

        RolUsuario rolUsuario=RolUsuario.builder()
                .rousId(1)
                .descripcion("asdsa")
                .estado("activo")
                .build();
        Usuario usuario = Usuario.builder()
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
                .precioTotal(100000)
                .estadoPago("")
                .fecha(new Date())
                .estado("activo")
                .build();
        Factura factura=Factura.builder()
                .factId(1)
                .reseId(1)
                .fecha(new Date())
                .estado("activo")
                .build();

        List<Factura>facturas= Arrays.asList(Factura.builder()
                .factId(1)
                .reseId(1)
                .fecha(new Date())
                .estado("activo")
                .build(),
         Factura.builder()
                 .factId(2)
                 .reseId(1)
                 .fecha(new Date())
                 .estado("activo")
                 .build());

        Mockito.when(facturaRepository.findAll()).thenReturn(facturas);
       // List<FacturaDTO>facturaDTOS=facturaService.obtenerFactura();
        List<FacturaDTO> facturasDTO = facturaService.obtenerFacturas();
        assertEquals(2, facturasDTO.size());
        assertEquals(1, facturasDTO.get(0).getFactId());



    }
    @Test
    public void obtenerFacturasNegativo(){
        List<Factura>facturas=Arrays.asList();
        Mockito.when(facturaRepository.findAll()).thenReturn(facturas);
        List<FacturaDTO>facturasDTO=facturaService.obtenerFacturas();
        assertEquals(0,facturasDTO.size());
    }
    @Test
    public void obtenerFacturaPorIdPositivo()throws Exception{
        Aeropuerto aeropuerto=Aeropuerto.builder()
                .aeroId(1)
                .nombre("cliente prueba")
                .iata("MEX")
                .ubicacion("paris")
                .estado("activo")
                .build();

        Vuelo vuelo=Vuelo.builder()
                .vueloId(1)
                .aeropuertoOrigen(aeropuerto)
                .aeropuertoDestino(aeropuerto)
                .precio(3243)
                .hora_salida("12:00")
                .hora_llegada("12:23")
                .precioAsientoVip(3223)
                .precioAsientoNormal(2134)
                .precioAsientoBasico(2321)
                .estado("activo")
                .build();

        TipoAsiento tipoAsiento=TipoAsiento.builder()
                .tiasId(1)
                .descripcion("2323")
                .estado("activo")
                .build();

        Avion avion=Avion.builder()
                .avioID(1)
                .modelo("bvv2")
                .estado("activo")
                .build();

        Asiento asiento =Asiento.builder()
                .asieId(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("DSADA")
                .precio(2323)
                .estado("activo")
                .build();

        RolUsuario rolUsuario =RolUsuario.builder()
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
                .precioTotal(100000)
                .estadoPago("")
                .fecha(new Date())
                .estado("activo")
                .build();

        Factura factura=Factura.builder()
                .factId(1)
                .reseId(1)
                .fecha(new Date())
                .estado("activo")
                .build();

        Mockito.when(facturaRepository.existsById(1)).thenReturn(true);
        Mockito.when(facturaRepository.getReferenceById(1)).thenReturn(factura);
        FacturaDTO facturaDTO= (FacturaDTO) facturaService.obtenerFacturas();
        assertEquals(1,facturaDTO.getFactId());

    }

    @Test
    public void obtenerFacturaPorIdNegativo(){
        Mockito.when(facturaRepository.existsById(1)).thenReturn(false);
        assertThrows(java.lang.Exception.class, () -> facturaService.obtenerFacturas());
    }



}
