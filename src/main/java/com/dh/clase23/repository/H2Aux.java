package com.dh.clase23.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Aux {
    private static final String DRIVER="org.h2.Driver";
    private static final String URL="jdbc:h2:~/clinicaOdontologicaMackEmely";
    private static final String WITH_SCRIPT = ";INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String USER = "sa";
    private static final String PASS = "sa";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASS);
    }

    public static Connection getConnectionInit() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL+WITH_SCRIPT,USER,PASS);
    }

}
