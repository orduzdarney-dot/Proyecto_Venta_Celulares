package com.mycompany.Tecnostore.persistencia.dao;




import com.mycompany.Tecnostore.excepciones.DAOException;
import com.mycompany.Tecnostore.modelo.Celular;
import java.util.List;

public interface CelularDAO {
    List<Celular> listarTodos() throws DAOException;
    void guardar(Celular celular) throws DAOException;
    void actualizar(Celular celular) throws DAOException;
    void eliminar(int id) throws DAOException;
    
    Celular buscarPorId(int id) throws DAOException;
}