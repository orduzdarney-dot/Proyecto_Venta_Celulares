
package com.mycompany.Tecnostore.controlador;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.Venta;
import com.mycompany.Tecnostore.reportes.ReporteService;
import com.mycompany.Tecnostore.servicio.CelularService;
import com.mycompany.Tecnostore.servicio.VentaService;
import com.mycompany.Tecnostore.servicio.EstrategiaIvaGeneral;
import com.mycompany.Tecnostore.vista.MenuView;
import com.mycompany.Tecnostore.vista.VentaView;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.time.Month;

public class ReporteController {
    private ReporteService reporteService;
    private CelularService celularService;
    private VentaService ventaService;
    private VentaView view;
    private MenuView menuView;

    public ReporteController() {
        this.reporteService = new ReporteService();
        this.celularService = new CelularService();
        this.ventaService = new VentaService(new EstrategiaIvaGeneral());
        this.view = new VentaView(new MenuView());
        this.menuView = new MenuView();
    }

    public void ejecutar() {
        int opcion;
        do {
            menuView.mostrarMensaje("\n--- Reportes y Análisis ---");
            menuView.mostrarMensaje("1. Celulares con Stock Bajo");
            menuView.mostrarMensaje("2. Top 3 Más Vendidos");
            menuView.mostrarMensaje("3. Ventas por Mes");
            menuView.mostrarMensaje("4. Generar Archivo TXT");
            menuView.mostrarMensaje("5. Volver");
            opcion = menuView.pedirEntero("Seleccione una opción");

            try {
                switch (opcion) {
                    case 1: mostrarStockBajo(); break;
                    case 2: mostrarTop3(); break;
                    case 3: mostrarVentasPorMes(); break;
                    case 4: generarArchivo(); break;
                    case 5: menuView.mostrarMensaje("Saliendo..."); break;
                    default: menuView.mostrarError("Opción no válida");
                }
            } catch (DAOException e) {
                menuView.mostrarError("Error al generar reporte: " + e.getMessage());
            } catch (Exception e) {
                menuView.mostrarError("Error general: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private void mostrarStockBajo() throws DAOException {
        List<Celular> stockBajo = reporteService.obtenerCelularesStockBajo(celularService.listarCelulares());
        view.mostrarReporteStockBajo(stockBajo);
        menuView.pausar();
    }

    private void mostrarTop3() throws DAOException {
        List<Entry<Celular, Long>> top3 = reporteService.obtenerTop3MasVendidos(ventaService.listarVentas());
        view.mostrarTop3MasVendidos(top3);
        menuView.pausar();
    }

    private void mostrarVentasPorMes() throws DAOException {
        Map<Month, Double> porMes = reporteService.obtenerVentasPorMes(ventaService.listarVentas());
        view.mostrarVentasPorMes(porMes);
        menuView.pausar();
    }

    private void generarArchivo() throws DAOException {
        try {
            reporteService.generarReporteTxt(ventaService.listarVentas(), "reporte_ventas.txt");
            menuView.mostrarExito("Archivo 'reporte_ventas.txt' generado en la raíz del proyecto.");
        } catch (Exception e) {
            menuView.mostrarError("Error al escribir el archivo: " + e.getMessage());
        }
        menuView.pausar();
    }
}