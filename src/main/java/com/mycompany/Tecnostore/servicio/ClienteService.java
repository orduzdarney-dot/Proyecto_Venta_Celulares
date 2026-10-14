
package com.mycompany.Tecnostore.servicio;



import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.excepciones.IdentificacionDuplicadaException;
import com.mycompany.Tecnostore.modelo.Cliente;
import com.mycompany.Tecnostore.persistencia.dao.ClienteDAO;
import com.mycompany.Tecnostore.persistencia.dao.DAOFactory;
import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = DAOFactory.getClienteDAO();
    }

    public Cliente buscarCliente(String identificacion) throws DAOException {
        return clienteDAO.buscarPorIdentificacion(identificacion);
    }

    public void registrarCliente(Cliente cliente) throws DAOException, IdentificacionDuplicadaException {
       
        Cliente existente = clienteDAO.buscarPorIdentificacion(cliente.getIdentificacion());
        if (existente != null) {
            throw new IdentificacionDuplicadaException("Ya existe un cliente con la identificación: " + cliente.getIdentificacion());
        }
        clienteDAO.guardar(cliente);
    }

    public List<Cliente> listarClientes() throws DAOException {
        return clienteDAO.listarTodos();
    }
}
