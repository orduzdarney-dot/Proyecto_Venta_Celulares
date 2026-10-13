
package com.mycompany.Tecnostore.persistencia.dao;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.Cliente;
import java.util.List;

public interface ClienteDAO {
    Cliente buscarPorIdentificacion(String identificacion) throws DAOException;
    void guardar(Cliente cliente) throws DAOException;
    List<Cliente> listarTodos() throws DAOException;
}