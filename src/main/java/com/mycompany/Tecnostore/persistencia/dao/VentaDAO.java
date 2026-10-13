
package com.mycompany.Tecnostore.persistencia.dao;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.DetalleVenta;
import com.mycompany.Tecnostore.modelo.Venta;
import java.util.List;

public interface VentaDAO {
    int registrarVenta(Venta venta) throws DAOException;
    void agregarDetalle(int ventaId, DetalleVenta detalle) throws DAOException;
    List<Venta> listarTodas() throws DAOException;
}