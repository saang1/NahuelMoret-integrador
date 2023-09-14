package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {


    @Query("SELECT t FROM Turno t JOIN t.paciente p WHERE p.apellido = ?1")
    List<Turno> listarTurnosPorApellidoPaciente(String apellido);


    @Query(value = "SELECT * FROM TURNOS JOIN ODONTOLOGOS ON TURNOS.ODONTOLOGO_ID = ODONTOLOGOS.ID WHERE ODONTOLOGOS.APELLIDO = ?1", nativeQuery = true)
    List<Turno> listarTurnosPorApellidoOdontologo(String apellido);

}
