

package com.mycompany.Tecnostore.controlador;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.PrecioInvalidoException;
import com.mycompany.Tecnostore.excepciones.StockInvalidoException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.servicio.CelularService;
import com.mycompany.Tecnostore.vista.CelularView;
import com.mycompany.Tecnostore.vista.MenuView;

public class CelularController {
    private CelularService service;
    private CelularView view;
    private MenuView menuView;

    public CelularController() {
        this.service = new CelularService();
        this.view = new CelularView(new MenuView());
        this.menuView = new MenuView();
    }

    public void ejecutar() {
        int opcion;
        do {
            menuView.mostrarMensaje("\n--- Gestión de Celulares ---");
            menuView.mostrarMensaje("1. Listar Celulares");
            menuView.mostrarMensaje("2. Registrar Celular");
            menuView.mostrarMensaje("3. Actualizar Celular");
            menuView.mostrarMensaje("4. Eliminar Celular");
            menuView.mostrarMensaje("5. Volver");
            opcion = menuView.pedirEntero("Seleccione una opción");

            try {
                switch (opcion) {
                    case 1: listarCelulares(); break;
                    case 2: registrarCelular(); break;
                    case 3: actualizarCelular(); break;
                    case 4: eliminarCelular(); break;
                    case 5: menuView.mostrarMensaje("Saliendo..."); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            } catch (DAOException e) {
                menuView.mostrarError("Error de base de datos: " + e.getMessage());
            } catch (Exception e) {
                menuView.mostrarError("Error general: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private void listarCelulares() throws DAOException {
        view.mostrarListaCelulares(service.listarCelulares());
        menuView.pausar();
    }

    private void registrarCelular() throws DAOException, PrecioInvalidoException, StockInvalidoException {
        Celular celular = view.pedirDatosCelular();
        service.registrarCelular(celular);
        menuView.mostrarExito("Celular registrado exitosamente.");
    }

    private void actualizarCelular() throws DAOException, PrecioInvalidoException, StockInvalidoException {
        int id = view.pedirIdCelular();
        Celular existente = service.buscarCelular(id);
        if (existente == null) {
            menuView.mostrarError("Celular no encontrado.");
            return;
        }
        Celular nuevosDatos = view.pedirDatosCelular();
        nuevosDatos.setId(id); 
        service.actualizarCelular(nuevosDatos);
        menuView.mostrarExito("Celular actualizado exitosamente.");
    }

    private void eliminarCelular() throws DAOException {
        int id = view.pedirIdCelular();
        if (menuView.confirmarAccion("¿Está seguro de eliminar el celular con ID " + id + "?")) {
            service.eliminarCelular(id);
            menuView.mostrarExito("Celular eliminado.");
        }
    }
}
