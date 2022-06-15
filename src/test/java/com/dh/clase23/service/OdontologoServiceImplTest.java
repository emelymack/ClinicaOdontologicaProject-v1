package com.dh.clase23.service;

import com.dh.clase23.repository.OdontologoDAOH2;
import com.dh.clase23.dominio.Odontologo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceImplTest {
    @BeforeAll
    // el BeforeAll siempre debe ser STATIC
    public static void prepararBD(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/cam9clase23M;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try{
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void listarOdontologos(){
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl(new OdontologoDAOH2());
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologos();

        assertTrue(listaOdontologos.size() > 0);
    }

    @Test
    public void buscarOdontologoXId(){
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl(new OdontologoDAOH2());
        int idBuscado = 1;
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(idBuscado);

        assertNotNull(odontologoBuscado);
    }

    //hacer un test x cada funcionalidad
}