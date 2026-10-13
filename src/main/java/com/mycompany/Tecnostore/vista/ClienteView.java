package com.mycompany.Tecnostore.vista;

import com.mycompany.Tecnostore.excepciones.EmailInvalidoException;
import com.mycompany.Tecnostore.modelo.Cliente;
import java.util.List;

public class ClienteView {
    private MenuView menuView;

    public ClienteView(MenuView menuView) {
        this.menuView = menuView;
    }

    // 👇 AQUÍ ESTÁ EL CAMBIO: se agregó "throws EmailInvalidoException"
    public Cliente pedirDatosCliente() throws EmailInvalidoException {
        menuView.mostrarMensaje("--- Registrar Nuevo Cliente ---");
        String nombre = menuView.pedirTexto("Nombre");
        String identificacion = menuView.pedirTexto("Identificación");
        String correo = menuView.pedirTexto("Correo electrónico");
        String telefono = menuView.pedirTexto("Teléfono");
        
        return new Cliente(0, nombre, identificacion, correo, telefono);
    }

    public void mostrarListaClientes(List<Cliente> clientes) {
        menuView.mostrarMensaje("\n--- Lista de Clientes ---");
        if (clientes.isEmpty()) {
            menuView.mostrarMensaje("No hay clientes registrados.");
            return;
        }
        System.out.printf("%-5s %-20s %-15s %-25s %-15s%n", 
            "ID", "Nombre", "Identificación", "Correo", "Teléfono");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Cliente c : clientes) {
            System.out.printf("%-5d %-20s %-15s %-25s %-15s%n",
                c.getId(), c.getNombre(), c.getIdentificacion(), c.getCorreo(), c.getTelefono());
        }
    }

    public String pedirIdentificacion() {
        return menuView.pedirTexto("Ingrese la identificación del cliente");
    }
}