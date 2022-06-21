package com.dh.clase23;

import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.dominio.Turno;
import com.dh.clase23.repository.H2Aux;
import com.dh.clase23.repository.OdontologoDAOH2;
import com.dh.clase23.repository.PacienteDAOH2;
import com.dh.clase23.repository.TurnoDAO;
import com.dh.clase23.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		try {
			H2Aux.getConnectionInit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
