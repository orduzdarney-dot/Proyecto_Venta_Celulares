
package com.mycompany.Tecnostore.vista;



import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.DetalleVenta;
import com.mycompany.Tecnostore.modelo.Venta;
import java.util.List;
import java.util.Map;
import java.time.Month;

public class VentaView {
    private MenuView menuView;

    public VentaView(MenuView menuView) {
        this.menuView = menuView;
    }

    public void mostrarMenuVenta() {
        menuView.mostrarMensaje("\n--- Menú de Ventas ---");
        menuView.mostrarMensaje("1. Realizar nueva venta");
        menuView.mostrarMensaje("2. Listar ventas");
        menuView.mostrarMensaje("3. Volver");
    }

    public void mostrarCarrito(List<DetalleVenta> detalles) {
        menuView.mostrarMensaje("\n--- Carrito de Compras ---");
        double total = 0;
        for (DetalleVenta d : detalles) {
            menuView.mostrarMensaje("- " + d.getCelular().getMarca() + " " + d.getCelular().getModelo() 
                + " | Cant: " + d.getCantidad() + " | Sub: $" + d.getSubtotal());
            total += d.getSubtotal();
        }
        menuView.mostrarMensaje("Subtotal sin IVA: $" + total);
        menuView.mostrarMensaje("IVA (19%): $" + (total * 0.19));
        menuView.mostrarMensaje("TOTAL A PAGAR: $" + (total * 1.19));
    }

    public void mostrarTicketVenta(Venta venta) {
        menuView.mostrarMensaje("\n=================================");
        menuView.mostrarMensaje("       TICKET DE VENTA       ");
        menuView.mostrarMensaje("=================================");
        menuView.mostrarMensaje("Cliente: " + venta.getCliente().getNombre());
        menuView.mostrarMensaje("Fecha: " + venta.getFecha());
        menuView.mostrarMensaje("---------------------------------");
        for (DetalleVenta d : venta.getDetalles()) {
            menuView.mostrarMensaje(d.getCelular().getMarca() + " " + d.getCelular().getModelo() 
                + " x" + d.getCantidad() + " = $" + d.getSubtotal());
        }
        menuView.mostrarMensaje("---------------------------------");
        menuView.mostrarMensaje("Subtotal: $" + venta.getTotalSinIva());
        menuView.mostrarMensaje("IVA: $" + venta.getIva());
        menuView.mostrarMensaje("TOTAL: $" + venta.getTotalConIva());
        menuView.mostrarMensaje("=================================\n");
    }

    public int pedirCantidad() {
        return menuView.pedirEntero("Ingrese la cantidad");
    }

    public boolean confirmarAccion(String mensaje) {
        menuView.mostrarMensaje(mensaje + " (1=Si, 0=No)");
        return menuView.pedirEntero("Opción") == 1;
    }

    public void mostrarReporteStockBajo(List<Celular> celulares) {
        menuView.mostrarMensaje("\n--- Celulares con Stock Bajo (< 5) ---");
        if (celulares.isEmpty()) {
            menuView.mostrarMensaje("Todos los productos tienen stock suficiente.");
            return;
        }
        for (Celular c : celulares) {
            menuView.mostrarMensaje("- " + c.getMarca() + " " + c.getModelo() + " | Stock: " + c.getStock());
        }
    }

    public void mostrarTop3MasVendidos(List<Map.Entry<Celular, Long>> top3) {
        menuView.mostrarMensaje("\n--- Top 3 Celulares Más Vendidos ---");
        int posicion = 1;
        for (Map.Entry<Celular, Long> entry : top3) {
            menuView.mostrarMensaje(posicion + ". " + entry.getKey().getMarca() + " " + entry.getKey().getModelo() 
                + " | Vendidos: " + entry.getValue());
            posicion++;
        }
    }

    public void mostrarVentasPorMes(Map<Month, Double> ventasPorMes) {
        menuView.mostrarMensaje("\n--- Ventas Totales por Mes ---");
        for (Map.Entry<Month, Double> entry : ventasPorMes.entrySet()) {
            menuView.mostrarMensaje(entry.getKey().name() + ": $" + entry.getValue());
        }
    }
}