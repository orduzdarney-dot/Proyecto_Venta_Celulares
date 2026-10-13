package com.mycompany.Tecnostore.persistencia.dao;

import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.EmailInvalidoException;
import com.mycompany.Tecnostore.modelo.Cliente;
import com.mycompany.Tecnostore.persistencia.conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJDBC implements ClienteDAO {

    @Override
    public Cliente buscarPorIdentificacion(String identificacion) throws DAOException {
        String sql = "CALL sp_buscar_cliente_por_identificacion(?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setString(1, identificacion);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setIdentificacion(rs.getString("identificacion"));
                    
                    // 👇 Aquí atrapamos la excepción del correo
                    try {
                        c.setCorreo(rs.getString("correo"));
                    } catch (EmailInvalidoException e) {
                        throw new DAOException("Error de datos en la base de datos (correo inválido)", e);
                    }
                    
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar cliente", e);
        }
        return null;
    }

    @Override
    public void guardar(Cliente cliente) throws DAOException {
        String sql = "CALL sp_registrar_cliente(?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {
            
            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getIdentificacion());
            cs.setString(3, cliente.getCorreo());
            cs.setString(4, cliente.getTelefono());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al guardar cliente", e);
        }
    }

    @Override
    public List<Cliente> listarTodos() throws DAOException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes"; 
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setIdentificacion(rs.getString("identificacion"));
                
                // 👇 Aquí también atrapamos la excepción del correo
                try {
                    c.setCorreo(rs.getString("correo"));
                } catch (EmailInvalidoException e) {
                    throw new DAOException("Error de datos en la base de datos (correo inválido)", e);
                }
                
                c.setTelefono(rs.getString("telefono"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar clientes", e);
        }
        return clientes;
    }
}