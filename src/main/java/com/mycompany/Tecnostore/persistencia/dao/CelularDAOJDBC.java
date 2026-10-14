package com.mycompany.Tecnostore.persistencia.dao;

import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.PrecioInvalidoException;
import com.mycompany.Tecnostore.excepciones.StockInvalidoException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.modelo.Gama;
import com.mycompany.Tecnostore.modelo.SistemaOperativo;
import com.mycompany.Tecnostore.persistencia.conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAOJDBC implements CelularDAO {

    @Override
    public List<Celular> listarTodos() throws DAOException {
        List<Celular> celulares = new ArrayList<>();
        String sql = "CALL sp_listar_celulares()";
        
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {
                Celular c = new Celular();
                c.setId(rs.getInt("id"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                
                
                try {
                    c.setPrecio(rs.getDouble("precio"));
                    c.setStock(rs.getInt("stock"));
                } catch (PrecioInvalidoException | StockInvalidoException e) {
                    throw new DAOException("Error de datos en la base de datos (precio/stock inválido)", e);
                }
                
                c.setSistemaOperativo(SistemaOperativo.valueOf(rs.getString("sistema_operativo")));
                c.setGama(Gama.valueOf(rs.getString("gama")));
                celulares.add(c);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar celulares", e);
        }
        return celulares;
    }

    @Override
    public void guardar(Celular celular) throws DAOException {
        String sql = "CALL sp_registrar_celular(?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setString(1, celular.getMarca());
            cs.setString(2, celular.getModelo());
            cs.setDouble(3, celular.getPrecio());
            cs.setInt(4, celular.getStock());
            cs.setString(5, celular.getSistemaOperativo().name());
            cs.setString(6, celular.getGama().name());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al guardar celular", e);
        }
    }

    @Override
    public void actualizar(Celular celular) throws DAOException {
        String sql = "CALL sp_actualizar_celular(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setInt(1, celular.getId());
            cs.setString(2, celular.getMarca());
            cs.setString(3, celular.getModelo());
            cs.setDouble(4, celular.getPrecio());
            cs.setInt(5, celular.getStock());
            cs.setString(6, celular.getSistemaOperativo().name());
            cs.setString(7, celular.getGama().name());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar celular", e);
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "CALL sp_eliminar_celular(?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar celular", e);
        }
    }
    
    @Override
    public Celular buscarPorId(int id) throws DAOException {
        String sql = "SELECT * FROM celulares WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Celular c = new Celular();
                    c.setId(rs.getInt("id"));
                    c.setMarca(rs.getString("marca"));
                    c.setModelo(rs.getString("modelo"));
                    
                  
                try {
                        c.setPrecio(rs.getDouble("precio"));
                        c.setStock(rs.getInt("stock"));
                    } catch (PrecioInvalidoException | StockInvalidoException e) {
                        throw new DAOException("Error de datos en la base de datos (precio/stock inválido)", e);
                    }
                    
                    c.setSistemaOperativo(SistemaOperativo.valueOf(rs.getString("sistema_operativo")));
                    c.setGama(Gama.valueOf(rs.getString("gama")));
                    return c;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar celular por ID", e);
        }
        return null;
    }
}
