package com.dh.clase23.repository;

import com.dh.clase23.dominio.Domicilio;
import com.dh.clase23.dominio.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDAOH2 implements IDao<Domicilio> {
    @Override
    public List<Domicilio> listarElementos() {
        Connection connection=null;
        List<Domicilio> listaDomicilios= new ArrayList<>();
        Domicilio domicilio=null;

        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT *" +
                    " FROM DOMICILIOS");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                domicilio=new Domicilio(rs.getInt(1),rs.getString(2),rs.getInt(3),
                        rs.getString(4),rs.getString(5));
                //ahora tenemos que agregarlo al listado
                listaDomicilios.add(domicilio);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return listaDomicilios;
    }

    @Override
    public Domicilio buscarXCriterio(String criterio) {
        return null;
    }

    @Override
    public Domicilio buscarXId(int id) {
        Connection connection=null;
        Domicilio domicilio=null;

        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT *" +
                    " FROM DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(rs.getInt(1),
                        rs.getString(2),rs.getInt(3),
                        rs.getString(4),rs.getString(5));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "DOMICILIOS(CALLE, NUMERO, LOCALIDAD, PROVINCIA) " +
                    "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            while(keys.next()){
                domicilio.setId(keys.getInt(1));
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
        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " +
                    "DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;
        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE DOMICILIOS " +
                    "SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? " +
                    "WHERE ID=?");
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2,domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.setInt(5, domicilio.getId());
            ps.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return domicilio;
    }
}
