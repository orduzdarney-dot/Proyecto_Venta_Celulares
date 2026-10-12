package modelo;

public class DetalleVenta {
    private Celular celular;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVenta(Celular celular, int cantidad, double precioUnitario) {
        this.celular = celular;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public Celular getCelular() { return celular; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    private double calcularSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {
        return "Detalle{" + celular.getModelo() + " x" + cantidad + " = $" + subtotal + '}';
    }
}
