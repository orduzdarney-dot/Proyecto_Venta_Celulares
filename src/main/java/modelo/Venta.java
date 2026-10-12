package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int id;
    private LocalDateTime fecha;
    private Cliente cliente;
    private Empleado empleado;
    private List<DetalleVenta> detalles;
    private double totalSinIva;
    private double iva;
    private double totalConIva;

    public Venta() {
        this.detalles = new ArrayList<>();
        this.fecha = LocalDateTime.now();
    }

    public Venta(Cliente cliente, Empleado empleado) {
        this(); // Llama al constructor vacío para inicializar lista y fecha
        this.cliente = cliente;
        this.empleado = empleado;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
    }

    public void calcularTotales() {
        this.totalSinIva = 0;
        for (DetalleVenta detalle : detalles) {
            this.totalSinIva += detalle.getSubtotal();
        }
        this.iva = this.totalSinIva * 0.19;
        this.totalConIva = this.totalSinIva + this.iva;
    }

    // Getters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public List<DetalleVenta> getDetalles() { return detalles; }
    public double getTotalSinIva() { return totalSinIva; }
    public double getIva() { return iva; }
    public double getTotalConIva() { return totalConIva; }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", totalConIva=" + totalConIva + '}';
    }
}
