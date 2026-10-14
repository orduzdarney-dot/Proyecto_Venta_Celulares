package com.mycompany.Tecnostore.persistencia.dao;

import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.*;
import com.mycompany.Tecnostore.persistencia.conexion.ConexionDB;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOJDBC implements VentaDAO {

    @Override
    public int registrarVenta(Venta venta) throws DAOException {
        String sql = "CALL sp_registrar_venta(?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setInt(1, venta.getCliente().getId());
            cs.setInt(2, venta.getEmpleado().getId());
            cs.setDouble(3, venta.getTotalConIva());
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) return rs.getInt("id_venta");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al registrar venta", e);
        }
        return -1;
    }

    @Override
    public void agregarDetalle(int ventaId, DetalleVenta detalle) throws DAOException {
        String sql = "CALL sp_agregar_detalle_venta(?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setInt(1, ventaId);
            cs.setInt(2, detalle.getCelular().getId());
            cs.setInt(3, detalle.getCantidad());
            cs.setDouble(4, detalle.getPrecioUnitario());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al agregar detalle", e);
        }
    }

    @Override
    public List<Venta> listarTodas() throws DAOException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT v.id, v.fecha, v.total, c.nombre as cliente_nombre " +
                     "FROM ventas v JOIN clientes c ON v.cliente_id = c.id ORDER BY v.fecha DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                v.setTotalConIva(rs.getDouble("total"));
                
                
                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("cliente_nombre"));




                
                cargarDetalles(conn, v);
                ventas.add(v);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar ventas", e);
        }
        return ventas;
    }

    private void cargarDetalles(Connection conn, Venta venta) throws SQLException {
        String sqlDet = "SELECT dv.cantidad, dv.precio_unitario, cel.marca, cel.modelo " +
                        "FROM detalles_venta dv JOIN celulares cel ON dv.celular_id = cel.id " +
                        "WHERE dv.venta_id = ?";
        try (PreparedStatement psDet = conn.prepareStatement(sqlDet)) {
            psDet.setInt(1, venta.getId());
            try (ResultSet rsDet = psDet.executeQuery()) {
                while (rsDet.next()) {
                    Celular cel = new Celular();
                    cel.setMarca(rsDet.getString("marca"));
                    cel.setModelo(rsDet.getString("modelo"));



                    
                    DetalleVenta det = new DetalleVenta(cel, rsDet.getInt("cantidad"), rsDet.getDouble("precio_unitario"));
                    venta.agregarDetalle(det);
                }
            }
        }
    }
}
