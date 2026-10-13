
package com.mycompany.Tecnostore;



import com.mycompany.Tecnostore.controlador.CelularController;
import com.mycompany.Tecnostore.controlador.ClienteController;
import com.mycompany.Tecnostore.controlador.LoginController;
import com.mycompany.Tecnostore.controlador.ReporteController;
import com.mycompany.Tecnostore.controlador.VentaController;
import com.mycompany.Tecnostore.modelo.Empleado;
import com.mycompany.Tecnostore.modelo.RolEmpleado;
import com.mycompany.Tecnostore.vista.MenuView;

public class Main {
    public static void main(String[] args) {
        MenuView menuView = new MenuView();
        
        // 1. Iniciar sesión
        LoginController loginController = new LoginController();
        Empleado empleadoActual = loginController.ejecutar();

        // 2. Menú Principal según el Rol
        int opcion;
        do {
            menuView.mostrarMensaje("\n--- MENÚ PRINCIPAL (" + empleadoActual.getRol() + ") ---");
            
            if (empleadoActual.getRol() == RolEmpleado.ADMINISTRADOR) {
                menuView.mostrarMensaje("1. Gestión de Celulares");
                menuView.mostrarMensaje("2. Gestión de Clientes");
                menuView.mostrarMensaje("3. Realizar Ventas");
                menuView.mostrarMensaje("4. Reportes y Análisis");
                menuView.mostrarMensaje("5. Salir");
                opcion = menuView.pedirEntero("Seleccione una opción");

                switch (opcion) {
                    case 1: new CelularController().ejecutar(); break;
                    case 2: new ClienteController().ejecutar(); break;
                    case 3: new VentaController(empleadoActual).ejecutar(); break;
                    case 4: new ReporteController().ejecutar(); break;
                    case 5: menuView.mostrarMensaje("¡Hasta luego!"); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            } else { // VENDEDOR
                menuView.mostrarMensaje("1. Gestión de Clientes");
                menuView.mostrarMensaje("2. Realizar Ventas");
                menuView.mostrarMensaje("3. Salir");
                opcion = menuView.pedirEntero("Seleccione una opción");

                switch (opcion) {
                    case 1: new ClienteController().ejecutar(); break;
                    case 2: new VentaController(empleadoActual).ejecutar(); break;
                    case 3: menuView.mostrarMensaje("¡Hasta luego!"); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            }
        } while (opcion != 5 && !(empleadoActual.getRol() == RolEmpleado.VENDEDOR && opcion == 3));
    }
}