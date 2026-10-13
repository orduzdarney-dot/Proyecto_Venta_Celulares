package com.mycompany.Tecnostore.servicio;

import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.StockInsuficienteException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.DetalleVenta;
import com.mycompany.Tecnostore.modelo.Venta;
import com.mycompany.Tecnostore.persistencia.dao.CelularDAO;
import com.mycompany.Tecnostore.persistencia.dao.DAOFactory;
import com.mycompany.Tecnostore.persistencia.dao.VentaDAO;
import java.util.List;

public class VentaService {
    private VentaDAO ventaDAO;
    private CelularDAO celularDAO;
    private EstrategiaCalculoImpuesto estrategiaImpuesto;

    public VentaService(EstrategiaCalculoImpuesto estrategiaImpuesto) {
        this.ventaDAO = DAOFactory.getVentaDAO();
        this.celularDAO = DAOFactory.getCelularDAO();
        this.estrategiaImpuesto = estrategiaImpuesto;
    }

    public void registrarVenta(Venta venta) throws DAOException, StockInsuficienteException {
        if (venta.getDetalles().isEmpty()) {
            throw new DAOException("La venta no tiene detalles");
        }

        for (DetalleVenta detalle : venta.getDetalles()) {
            Celular celularEnBD = celularDAO.buscarPorId(detalle.getCelular().getId());
            if (celularEnBD == null || celularEnBD.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para el modelo: " + detalle.getCelular().getModelo());
            }
        }

        venta.calcularTotales();
        int idVenta = ventaDAO.registrarVenta(venta);
        
        for (DetalleVenta detalle : venta.getDetalles()) {
            ventaDAO.agregarDetalle(idVenta, detalle);
        }
    }
    
    public List<Venta> listarVentas() throws DAOException {
        return ventaDAO.listarTodas();
    }
}