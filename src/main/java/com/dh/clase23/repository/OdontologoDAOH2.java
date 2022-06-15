package com.dh.clase23.repository;

import com.dh.clase23.dominio.Odontologo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDAOH2 implements IDao<Odontologo> {

    @Override
    public List<Odontologo> listarElementos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo;

        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                listaOdontologos.add(odontologo);
                System.out.println(listaOdontologos);
            }

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

        return listaOdontologos;
    }

    @Override
    public Odontologo buscarXId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS" +
                    "(APELLIDO, NOMBRE, MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,odontologo.getApellido());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getMatricula());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            while(keys.next()){
                odontologo.setId(keys.getInt(1));
            }
        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection= null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE ODONTOLOGOS " +
                    "SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?");
            ps.setString(1, odontologo.getApellido());
            ps.setString(2,odontologo.getNombre());
            ps.setString(3,odontologo.getMatricula());
            ps.setInt(4, odontologo.getId());
            ps.execute();
        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID =?");
            ps.setInt(1, id);
            ps.execute();

        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Odontologo buscarXCriterio(String criterio) { return null; }
}















