package com.mycompany.Tecnostore.persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
       private static final String URL = "jdbc:mysql://localhost:3306/tecnostore?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    //  TUS CREDENCIALES REALES DE MYSQL
    private static final String USUARIO = "campus2023"; 
    private static final String PASSWORD = "campus2023"; // ️ Pon aquí la contraseña exacta que usas en la terminal (entre comillas)
    private static Connection instancia;

    private ConexionDB() {
    }

    public static synchronized Connection getConexion() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instancia = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver de MySQL no encontrado", e);
            }
        }
        return instancia;
    }
}