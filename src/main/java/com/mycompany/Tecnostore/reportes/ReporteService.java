
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

    
    public List<Celular> obtenerCelularesStockBajo(List<Celular> celulares) {
    return celulares.stream()
            .filter(c -> c.getStock() < 5)
            .sorted(Comparator.comparingInt(Celular::getStock))
            .collect(Collectors.toList());
}

public void generarArchivoStockBajo(List<Celular> celulares, String nombreArchivo) throws IOException {
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)))) {
        writer.println("--- ALERTA DE STOCK BAJO ---");
        for (Celular c : celulares) {
            writer.println("ID: " + c.getId() + " | Modelo: " + c.getModelo() + 
                          " | Marca: " + c.getMarca() + 
                          " | Stock: " + c.getStock() + 
                          " | Precio: " + c.getPrecio());
        }
    }
}
  
    public List<Map.Entry<Celular, Long>> obtenerTop3MasVendidos(List<Venta> ventas) {
        return ventas.stream()
                .flatMap(v -> v.getDetalles().stream()) 
                .collect(Collectors.groupingBy(
                        DetalleVenta::getCelular,       
                        Collectors.summingLong(DetalleVenta::getCantidad) 
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<Celular, Long>comparingByValue().reversed())
                .limit(3) 
                .collect(Collectors.toList());
    }

    
    public Map<Month, Double> obtenerVentasPorMes(List<Venta> ventas) {
        return ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth(), 
                        Collectors.summingDouble(Venta::getTotalConIva) 
                ));
    }

   
    public void generarReporteTxt(List<Venta> ventas, String nombreArchivo) throws IOException {
       
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
