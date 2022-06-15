package com.dh.clase23.repository;

import com.dh.clase23.dominio.Domicilio;
import com.dh.clase23.dominio.Paciente;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDAOH2 implements IDao<Paciente>{
    @Override
    public List<Paciente> listarElementos() {
        Connection connection=null;
        List<Paciente> listaPacientes= new ArrayList<>();
        Paciente paciente=null;
        Domicilio domicilio=null;

        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT *" +
                    " FROM PACIENTES");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
                domicilio=domicilioDAOH2.buscarXId(rs.getInt(7));

                paciente=new Paciente(rs.getInt(1),
                        rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getInt(5),
                        rs.getDate(6).toLocalDate(),domicilio
                        );
                //ahora tenemos que agregarlo al listado
                listaPacientes.add(paciente);
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
        return listaPacientes;
    }

    @Override
    public Paciente buscarXCriterio(String criterio) {
        Connection connection=null;
        Paciente paciente=null;
        Domicilio domicilio=null;

        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT *" +
                    " FROM PACIENTES WHERE EMAIL=?");
            ps.setString(1,criterio);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
                domicilio=domicilioDAOH2.buscarXId(rs.getInt(7));

                paciente=new Paciente(rs.getInt(1),
                        rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getInt(5),
                        rs.getDate(6).toLocalDate(),domicilio
                );
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
        return paciente;
    }

    @Override
    public Paciente buscarXId(int id) {
        Connection connection=null;
        Paciente paciente=null;
        Domicilio domicilio=null;

        try{
            connection=H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT *" +
                    " FROM PACIENTES WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
                domicilio=domicilioDAOH2.buscarXId(rs.getInt(7));

                paciente=new Paciente(rs.getInt(1),
                        rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getInt(5),
                        rs.getDate(6).toLocalDate(),domicilio
                );
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
        return paciente;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        try{
            connection = H2Aux.getConnection();
            //guardar el domicilio
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            domicilioDAOH2.guardar(paciente.getDomicilio());
            PreparedStatement ps= connection.prepareStatement("INSERT INTO " +
                    "PACIENTES (NOMBRE, APELLIDO, EMAIL, DNI, FECHA_INGRESO, DOMICILIO_ID)" +
                    "VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getEmail());
            ps.setInt(4,paciente.getDni());
            ps.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(6,paciente.getDomicilio().getId());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            while(keys.next()){
                paciente.setId(keys.getInt(1));
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
        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection= null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES " +
                    "SET APELLIDO=?, NOMBRE=?, EMAIL=?, DNI=?, FECHA_INGRESO=? WHERE ID=?");
            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setString(3,paciente.getEmail());
            ps.setInt(4, paciente.getDni());
            ps.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(6, paciente.getDni());
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
        return paciente;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try{
            connection = H2Aux.getConnection();
            PreparedStatement ps= connection.prepareStatement("DELETE FROM " +
                    "PACIENTES WHERE ID = ?");
            ps.setInt(1,id);
            ps.execute();

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
    }
}
