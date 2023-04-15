package co.edu.usbcali.aerolinea.service;

import co.edu.usbcali.aerolinea.dtos.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.model.RolUsuario;
import co.edu.usbcali.aerolinea.model.TipoAsiento;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.services.RolUsuarioService;
import co.edu.usbcali.aerolinea.services.TipoAsientoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class TipoAsientoServicelpmlTest {

    @Autowired
    private TipoAsientoService tipoAsientoService;

    @MockBean
    private TipoAsientoRepository tipoAsientoRepository;

    @Test
    void obtenerAeropuertos_TestOK_(){
        //assertEquals(2,1);
        TipoAsiento tipoAsientoPrueba =
                TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        List<TipoAsiento> tipoAsientoRetorno = Arrays.asList(TipoAsiento.builder()
                        .tiasId(2)
                        .descripcion("sada")
                        .estado("activo")
                        .build(),
                TipoAsiento.builder()
                                .tiasId(3)
                                .descripcion("sada")
                                .estado("activo")
                                .build());

        Mockito.when(tipoAsientoRepository.findAll()).thenReturn(tipoAsientoRetorno);
    }

    @Test
    void buscarPorId_TestOk_() throws Exception {
        TipoAsiento tipoAsientoPrueba =
                TipoAsiento.builder()
                        .tiasId(1)
                        .descripcion("sada")
                        .estado("activo")
                        .build();

        Mockito.when(tipoAsientoRepository.existsById(1)).thenReturn(true);
        Mockito.when(tipoAsientoRepository.getReferenceById(1)).thenReturn(tipoAsientoPrueba);

        // Llamado al método a probar
        TipoAsientoDTO tipoAsientoDTO = tipoAsientoService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(tipoAsientoDTO.getTiasId(), 1);
    }
}
