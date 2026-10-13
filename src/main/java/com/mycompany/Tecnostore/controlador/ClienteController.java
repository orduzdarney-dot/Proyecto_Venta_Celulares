package com.mycompany.Tecnostore.controlador;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.EmailInvalidoException;
import com.mycompany.Tecnostore.excepciones.IdentificacionDuplicadaException;
import com.mycompany.Tecnostore.modelo.Cliente;
import com.mycompany.Tecnostore.servicio.ClienteService;
import com.mycompany.Tecnostore.vista.ClienteView;
import com.mycompany.Tecnostore.vista.MenuView;

public class ClienteController {
    private ClienteService service;
    private ClienteView view;
    private MenuView menuView;

    public ClienteController() {
        this.service = new ClienteService();
        this.view = new ClienteView(new MenuView());
        this.menuView = new MenuView();
    }

    public void ejecutar() {
        int opcion;
        do {
            menuView.mostrarMensaje("\n--- Gestión de Clientes ---");
            menuView.mostrarMensaje("1. Listar Clientes");
            menuView.mostrarMensaje("2. Registrar Cliente");
            menuView.mostrarMensaje("3. Buscar Cliente");
            menuView.mostrarMensaje("4. Volver");
            opcion = menuView.pedirEntero("Seleccione una opción");

            try {
                switch (opcion) {
                    case 1: listarClientes(); break;
                    case 2: registrarCliente(); break;
                    case 3: buscarCliente(); break;
                    case 4: menuView.mostrarMensaje("Saliendo..."); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            } catch (DAOException e) {
                menuView.mostrarError("Error de base de datos: " + e.getMessage());
            } catch (Exception e) {
                menuView.mostrarError("Error general: " + e.getMessage());
            }
        } while (opcion != 4);
    }

    private void listarClientes() throws DAOException {
        view.mostrarListaClientes(service.listarClientes());
        menuView.pausar();
    }

    private void registrarCliente() throws DAOException, EmailInvalidoException, IdentificacionDuplicadaException {
        Cliente cliente = view.pedirDatosCliente();
        service.registrarCliente(cliente);
        menuView.mostrarExito("Cliente registrado exitosamente.");
    }

    private void buscarCliente() throws DAOException {
        String id = view.pedirIdentificacion();
        Cliente cliente = service.buscarCliente(id);
        if (cliente != null) {
            menuView.mostrarMensaje("Cliente encontrado: " + cliente.getNombre() + " (" + cliente.getCorreo() + ")");
        } else {
            menuView.mostrarError("Cliente no encontrado.");
        }
        menuView.pausar();
    }
}