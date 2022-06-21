package com.dh.clase23.service;

import com.dh.clase23.dominio.Turno;
import com.dh.clase23.repository.H2Aux;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class TurnoServiceImplTest {

    @Autowired
    ITurnoService turnoService;
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    IOdontologoService odontologoService;

    @BeforeAll
    public static void prepararDB(){
        try {
            H2Aux.getConnectionInit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void agregarTurnoTest(){
        Turno turnoTest = new Turno(1, pacienteService.buscarXId(1), odontologoService.buscarOdontologoXId(1), Date.valueOf("2022-07-07").toLocalDate());
        turnoService.guardarTurno(turnoTest);
        Turno turnoTestRecuperado = turnoService.buscarTurno(1);

        assertNotNull(turnoTestRecuperado);
        assertEquals(Date.valueOf("2022-07-07").toLocalDate(), turnoTestRecuperado.getFecha());
    }

    @Test
    @Order(2)
    public void buscarXIdTest(){
        int idBuscado = 1;
        Turno turnoBuscado = turnoService.buscarTurno(1);

        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    public void listarTurnosTest(){
        List<Turno> listaTurnos = turnoService.listarTurnos();
        assertTrue(listaTurnos.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        Turno turnoUpdate = new Turno(1, pacienteService.buscarXId(2), odontologoService.buscarOdontologoXId(1), Date.valueOf("2022-07-07").toLocalDate());
        turnoService.actualizarTurno(turnoUpdate);
        Turno turnoUpdateBuscado = turnoService.buscarTurno(1);

        assertEquals("Mack", turnoUpdateBuscado.getPaciente().getApellido());
    }

    @Test
    @Order(5)
    public void eliminarTurnoTest(){
        int idDelete = 1;
        turnoService.eliminarTurno(idDelete);
        Turno turnoRecuperado = turnoService.buscarTurno(1);

        assertNull(turnoRecuperado);
    }

}