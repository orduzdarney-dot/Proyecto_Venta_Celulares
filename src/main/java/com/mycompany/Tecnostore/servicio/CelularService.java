
package com.mycompany.Tecnostore.servicio;


import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.Celular;
import com.mycompany.Tecnostore.persistencia.dao.CelularDAO;
import com.mycompany.Tecnostore.persistencia.dao.DAOFactory;
import java.util.List;

public class CelularService {
    private CelularDAO celularDAO;

    public CelularService() {
        this.celularDAO = DAOFactory.getCelularDAO();
    }

    public List<Celular> listarCelulares() throws DAOException {
        return celularDAO.listarTodos();
    }

    public void registrarCelular(Celular celular) throws DAOException {
        celularDAO.guardar(celular);
    }

    public void actualizarCelular(Celular celular) throws DAOException {
        celularDAO.actualizar(celular);
    }

    public void eliminarCelular(int id) throws DAOException {
        celularDAO.eliminar(id);
    }
    
    public Celular buscarCelular(int id) throws DAOException {
        return celularDAO.buscarPorId(id);
    }
}
