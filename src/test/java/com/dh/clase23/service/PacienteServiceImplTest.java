package com.dh.clase23.service;

import com.dh.clase23.dominio.Domicilio;
import com.dh.clase23.dominio.Paciente;
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
class PacienteServiceImplTest {

    @Autowired
    IPacienteService pacienteService;

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
    public void agregarPacienteTest(){
        Paciente pacienteTest = new Paciente("Martinez","Julio","julio@test.com",123234, Date.valueOf("2022-04-02").toLocalDate(), new Domicilio("Salta", 111,"San Lorenzo", "Santa Fe"));
        pacienteService.guardarPaciente(pacienteTest);
        Paciente pacienteTestRecuperado = pacienteService.buscarXId(3);

        assertNotNull(pacienteTestRecuperado);
        assertEquals("julio@test.com", pacienteTestRecuperado.getEmail());

    }

    @Test
    @Order(2)
    public void buscarXIdTest(){
        int idBuscado = 1;
        Paciente pacienteBuscado = pacienteService.buscarXId(1);
        assertEquals("Baspineiro", pacienteBuscado.getApellido());
    }

    @Test
    @Order(3)
    public void listarPacientesTest(){
        List<Paciente> listaPacientes = pacienteService.listarPacientes();
        assertTrue(listaPacientes.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteUpdate =  new Paciente(3,"Martinez","Julio","julioActualizado@test.com",123234,Date.valueOf("2022-04-08").toLocalDate(), pacienteService.buscarXId(3).getDomicilio());
        pacienteService.actualizarPaciente(pacienteUpdate);
        Paciente pacienteUpdateRecuperado = pacienteService.buscarXId(3);

        assertEquals("julioActualizado@test.com", pacienteUpdateRecuperado.getEmail());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        int idDelete = 3;
        pacienteService.eliminarPaciente(idDelete);
        Paciente pacienteRecuperado = pacienteService.buscarXId(3);

        assertNull(pacienteRecuperado);
    }
}