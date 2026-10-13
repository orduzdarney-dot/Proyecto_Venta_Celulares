package com.mycompany.Tecnostore.controlador;


import com.mycompany.Tecnostore.modelo.Empleado;
import com.mycompany.Tecnostore.modelo.RolEmpleado;
import com.mycompany.Tecnostore.persistencia.conexion.ConexionDB;
import com.mycompany.Tecnostore.vista.LoginView;
import com.mycompany.Tecnostore.vista.MenuView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private LoginView view;
    private MenuView menuView;

    public LoginController() {
        this.view = new LoginView(new MenuView());
        this.menuView = new MenuView();
    }

    public Empleado ejecutar() {
        Empleado empleado = null;
        boolean loginExitoso = false;

        menuView.mostrarMensaje("\n=================================");
        menuView.mostrarMensaje("   BIENVENIDO A TECNOSTORE   ");
        menuView.mostrarMensaje("=================================");

        while (!loginExitoso) {
            String usuario = view.pedirUsuario();
            String password = view.pedirPassword();

            try (Connection conn = ConexionDB.getConexion();
                 PreparedStatement ps = conn.prepareStatement(
                     "SELECT id, nombre, rol FROM empleados WHERE usuario = ? AND password = ?")) {
                
                ps.setString(1, usuario);
                ps.setString(2, password);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        empleado = new Empleado();
                        empleado.setId(rs.getInt("id"));
                        empleado.setNombre(rs.getString("nombre"));
                        empleado.setRol(RolEmpleado.valueOf(rs.getString("rol")));
                        loginExitoso = true;
                        menuView.mostrarExito("Bienvenido, " + empleado.getNombre() + " (" + empleado.getRol() + ")");
                    } else {
                        view.mostrarErrorLogin();
                    }
                }
            } catch (SQLException e) {
                menuView.mostrarError("Error de conexión: " + e.getMessage());
            }
        }
        return empleado;
    }
}