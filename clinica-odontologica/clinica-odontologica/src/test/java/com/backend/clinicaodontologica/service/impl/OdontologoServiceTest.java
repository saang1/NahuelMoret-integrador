package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologoDeNombrePedroConId1(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("144", "Pedro", "Gomez");

        OdontologoSalidaDto odontologoSalidaDto  = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("Pedro", odontologoSalidaDto.getNombre());
        assertEquals(1, odontologoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDeOdontologos(){
        assertTrue(odontologoService.listarOdontologos().size() > 0);
    }

    @Test
    void alIntentarActualizarElOdontologoId2_deberiaLanzarseUnaResourceNotFoundException(){
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));
    }

    @Test
    @Order(3)
    void alIntentarEliminarUnOdontologoYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}

