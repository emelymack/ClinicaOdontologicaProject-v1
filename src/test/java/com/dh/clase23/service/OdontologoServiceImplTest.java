package com.dh.clase23.service;

import com.dh.clase23.repository.H2Aux;
import com.dh.clase23.repository.OdontologoDAOH2;
import com.dh.clase23.dominio.Odontologo;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// para darle un orden a los tests:
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class) //tells JUnit to run using Spring's testing support
@SpringBootTest //aclarar a Spring q es una clase de prueba
class OdontologoServiceImplTest {

    @Autowired
    IOdontologoService odontologoService;

    @BeforeAll
    // el BeforeAll siempre debe ser STATIC
    public static void prepararDB(){
        try {
            H2Aux.getConnectionInit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void agregarOdontologoTest(){
        Odontologo odontologoTest = new Odontologo("Lopez", "Mariano", "111553");
        odontologoService.guardarOdontologo(odontologoTest);
        Odontologo odontologoTestRecuperado = odontologoService.buscarOdontologoXId(2);

        assertNotNull(odontologoTestRecuperado);
        assertEquals("Lopez", odontologoTestRecuperado.getApellido());
    }

    @Test
    @Order(2)
    public void buscarXIdTest(){
        int idBuscado = 1;
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(idBuscado);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologos();
        assertTrue(listaOdontologos.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo odontologoUpdate = new Odontologo(2, "Dientes", "Mariano", "111553");
        odontologoService.actualizarOdontologo(odontologoUpdate);
        Odontologo odontologoUpdateRecuperado = odontologoService.buscarOdontologoXId(2);

        assertEquals("Dientes", odontologoUpdateRecuperado.getApellido());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        int idDelete = 2;
        odontologoService.eliminarOdontologo(idDelete);
        Odontologo odontologoRecuperado = odontologoService.buscarOdontologoXId(2);

        assertNull(odontologoRecuperado);
    }

}