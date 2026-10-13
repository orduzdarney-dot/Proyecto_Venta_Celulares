
package com.mycompany.Tecnostore.reportes;


import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.DetalleVenta;
import com.mycompany.Tecnostore.modelo.Venta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteService {

    // 1. Celulares con stock bajo (< 5)
    public List<Celular> obtenerCelularesStockBajo(List<Celular> celulares) {
        return celulares.stream()
                .filter(c -> c.getStock() < 5)
                .sorted(Comparator.comparingInt(Celular::getStock))
                .collect(Collectors.toList());
    }

    // 2. Top 3 de celulares más vendidos
    public List<Map.Entry<Celular, Long>> obtenerTop3MasVendidos(List<Venta> ventas) {
        return ventas.stream()
                .flatMap(v -> v.getDetalles().stream()) // Aplanar: sacar todos los detalles de todas las ventas
                .collect(Collectors.groupingBy(
                        DetalleVenta::getCelular,       // Agrupar por objeto Celular
                        Collectors.summingLong(DetalleVenta::getCantidad) // Sumar cantidades
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Celular, Long>comparingByValue().reversed()) // Ordenar de mayor a menor
                .limit(3) // Solo los 3 primeros
                .collect(Collectors.toList());
    }

    // 3. Ventas totales por mes
    public Map<Month, Double> obtenerVentasPorMes(List<Venta> ventas) {
        return ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth(), // Agrupar por mes de la fecha
                        Collectors.summingDouble(Venta::getTotalConIva) // Sumar totales
                ));
    }

    // 4. Generar archivo reporte_ventas.txt
    public void generarReporteTxt(List<Venta> ventas, String nombreArchivo) throws IOException {
        // TRY-WITH-RESOURCES: El archivo se cierra solo al terminar el bloque
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)))) {
            
            writer.println("=========================================");
            writer.println("       REPORTE DE VENTAS - TECNOSTORE    ");
            writer.println("=========================================");
            writer.println("Fecha de generación: " + java.time.LocalDateTime.now());
            writer.println();

            if (ventas.isEmpty()) {
                writer.println("No hay ventas registradas en el sistema.");
            } else {
                for (Venta venta : ventas) {
                    writer.println("Venta ID: " + venta.getId());
                    writer.println("Fecha: " + venta.getFecha());
                    writer.println("Detalles:");
                    
                    for (DetalleVenta detalle : venta.getDetalles()) {
                        writer.println("  - " + detalle.getCelular().getMarca() + " " + detalle.getCelular().getModelo() 
                                     + " | Cant: " + detalle.getCantidad() + " | Subtotal: $" + detalle.getSubtotal());
                    }
                    writer.println("TOTAL CON IVA: $" + venta.getTotalConIva());
                    writer.println("-----------------------------------------");
                }
            }
        }
    }
}