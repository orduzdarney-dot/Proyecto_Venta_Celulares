
package com.mycompany.Tecnostore.controlador;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.StockInsuficienteException;
import com.mycompany.Tecnostore.modelo.*;
import com.mycompany.Tecnostore.servicio.CelularService;
import com.mycompany.Tecnostore.servicio.ClienteService;
import com.mycompany.Tecnostore.servicio.EstrategiaIvaGeneral;
import com.mycompany.Tecnostore.servicio.VentaService;
import com.mycompany.Tecnostore.vista.CelularView;
import com.mycompany.Tecnostore.vista.ClienteView;
import com.mycompany.Tecnostore.vista.MenuView;
import com.mycompany.Tecnostore.vista.VentaView;
import java.util.List;

public class VentaController {
    private VentaService ventaService;
    private CelularService celularService;
    private ClienteService clienteService;
    private VentaView ventaView;
    private MenuView menuView;
    private Empleado empleadoActual;

    public VentaController(Empleado empleadoActual) {
        this.empleadoActual = empleadoActual;
        this.ventaService = new VentaService(new EstrategiaIvaGeneral());
        this.celularService = new CelularService();
        this.clienteService = new ClienteService();
        this.ventaView = new VentaView(new MenuView());
        this.menuView = new MenuView();
    }

    public void ejecutar() {
        int opcion;
        do {
            ventaView.mostrarMenuVenta();
            opcion = menuView.pedirEntero("Seleccione una opción");

            try {
                switch (opcion) {
                    case 1: realizarVenta(); break;
                    case 2: listarVentas(); break;
                    case 3: menuView.mostrarMensaje("Saliendo..."); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            } catch (DAOException | StockInsuficienteException e) {
                menuView.mostrarError("Error en la venta: " + e.getMessage());
            } catch (Exception e) {
                menuView.mostrarError("Error general: " + e.getMessage());
            }
        } while (opcion != 3);
    }

    private void realizarVenta() throws DAOException, StockInsuficienteException {
        // 1. Identificar Cliente
        String idCliente = menuView.pedirTexto("Ingrese la identificación del cliente");
        Cliente cliente = clienteService.buscarCliente(idCliente);
        if (cliente == null) {
            menuView.mostrarError("Cliente no encontrado. Regístrelo primero.");
            return;
        }

        // 2. Crear Venta
        Venta venta = new Venta(cliente, empleadoActual);
        
        // 3. Agregar Productos (Carrito)
        boolean seguirAgregando = true;
        while (seguirAgregando) {
            List<Celular> catalogo = celularService.listarCelulares();
            CelularView cView = new CelularView(menuView);
            cView.mostrarListaCelulares(catalogo);
            
            int idCelular = menuView.pedirEntero("Ingrese el ID del celular a vender");
            Celular celular = celularService.buscarCelular(idCelular);
            
            if (celular == null) {
                menuView.mostrarError("Celular no encontrado.");
                continue;
            }
            
            int cantidad = ventaView.pedirCantidad();
            // Creamos el detalle y lo agregamos a la venta
            DetalleVenta detalle = new DetalleVenta(celular, cantidad, celular.getPrecio());
            venta.agregarDetalle(detalle);
            
            ventaView.mostrarCarrito(venta.getDetalles());
            seguirAgregando = ventaView.confirmarAccion("¿Desea agregar otro producto?");
        }

        // 4. Confirmar y Guardar
        if (ventaView.confirmarAccion("¿Confirma la venta?")) {
            ventaService.registrarVenta(venta);
            ventaView.mostrarTicketVenta(venta);
            menuView.mostrarExito("Venta registrada exitosamente.");
        } else {
            menuView.mostrarMensaje("Venta cancelada.");
        }
        menuView.pausar();
    }

    private void listarVentas() throws DAOException {
        List<Venta> ventas = ventaService.listarVentas();
        menuView.mostrarMensaje("\n--- Historial de Ventas ---");
        if (ventas.isEmpty()) {
            menuView.mostrarMensaje("No hay ventas registradas.");
        } else {
            for (Venta v : ventas) {
                menuView.mostrarMensaje("ID: " + v.getId() + " | Fecha: " + v.getFecha() + " | Total: $" + v.getTotalConIva());
            }
        }
        menuView.pausar();
    }
}