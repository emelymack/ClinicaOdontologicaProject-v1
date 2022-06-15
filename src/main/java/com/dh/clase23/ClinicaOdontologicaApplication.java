package com.dh.clase23;

import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.dominio.Turno;
import com.dh.clase23.repository.OdontologoDAOH2;
import com.dh.clase23.repository.PacienteDAOH2;
import com.dh.clase23.repository.TurnoDAO;
import com.dh.clase23.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		prepararBD();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

	private static void prepararBD(){
		Connection connection=null;
		try{
			Class.forName("org.h2.Driver");
			connection= DriverManager.getConnection("jdbc:h2:~/clinicaOdontologicaM;INIT=RUNSCRIPT " +
					" FROM 'create.sql'","sa","sa");
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
	}

}
