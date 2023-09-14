package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;



    @Test
    void deberiaRetornarseUnaListaVaciaDeTurnos(){
        assertFalse(turnoService.listarTurnos().size() > 0);
    }

    @Test
    void alIntentarActualizarElPacienteId2_deberiaLanzarseUnaResourceNotFoundException(){
        TurnoModificacionEntradaDto turnoModificacionEntradaDto = new TurnoModificacionEntradaDto();
        turnoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> turnoService.modificarTurno(turnoModificacionEntradaDto));
    }

    @Test
    void alIntentarEliminarUnTurnoYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            turnoService.eliminarTurno(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }


}