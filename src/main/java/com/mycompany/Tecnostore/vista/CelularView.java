package com.mycompany.Tecnostore.vista;

import com.mycompany.Tecnostore.excepciones.PrecioInvalidoException;
import com.mycompany.Tecnostore.excepciones.StockInvalidoException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.Gama;
import com.mycompany.Tecnostore.modelo.SistemaOperativo;
import java.util.List;

public class CelularView {
    private MenuView menuView;

    public CelularView(MenuView menuView) {
        this.menuView = menuView;
    }

    // 👇 AQUÍ ESTÁ EL CAMBIO CLAVE: se agregaron los "throws"
    public Celular pedirDatosCelular() throws PrecioInvalidoException, StockInvalidoException {
        menuView.mostrarMensaje("--- Registrar Nuevo Celular ---");
        String marca = menuView.pedirTexto("Marca");
        String modelo = menuView.pedirTexto("Modelo");
        double precio = menuView.pedirNumero("Precio");
        int stock = menuView.pedirEntero("Stock");
        
        menuView.mostrarMensaje("Sistemas Operativos disponibles:");
        for (int i = 0; i < SistemaOperativo.values().length; i++) {
            menuView.mostrarMensaje((i + 1) + ". " + SistemaOperativo.values()[i].name());
        }
        int opSO = menuView.pedirEntero("Seleccione el Sistema Operativo (número)");
        SistemaOperativo so = SistemaOperativo.values()[opSO - 1];

        menuView.mostrarMensaje("Gamas disponibles:");
        for (int i = 0; i < Gama.values().length; i++) {
            menuView.mostrarMensaje((i + 1) + ". " + Gama.values()[i].name());
        }
        int opGama = menuView.pedirEntero("Seleccione la Gama (número)");
        Gama gama = Gama.values()[opGama - 1];

        return new Celular(0, marca, modelo, precio, stock, so, gama);
    }

    public void mostrarListaCelulares(List<Celular> celulares) {
        menuView.mostrarMensaje("\n--- Catálogo de Celulares ---");
        if (celulares.isEmpty()) {
            menuView.mostrarMensaje("No hay celulares registrados.");
            return;
        }
        System.out.printf("%-5s %-10s %-15s %-10s %-6s %-10s %-5s%n", 
            "ID", "Marca", "Modelo", "Precio", "Stock", "S.O.", "Gama");
        System.out.println("--------------------------------------------------------------------------");
        for (Celular c : celulares) {
            System.out.printf("%-5d %-10s %-15s $%-9.2f %-6d %-10s %-5s%n",
                c.getId(), c.getMarca(), c.getModelo(), c.getPrecio(), 
                c.getStock(), c.getSistemaOperativo().name(), c.getGama().name());
        }
    }

    public int pedirIdCelular() {
        return menuView.pedirEntero("Ingrese el ID del celular");
    }
}