
package com.mycompany.Tecnostore.persistencia.dao;



public class DAOFactory {

    public static CelularDAO getCelularDAO() {
        return new CelularDAOJDBC();
    }

    public static ClienteDAO getClienteDAO() {
        return new ClienteDAOJDBC();
    }

    public static VentaDAO getVentaDAO() {
        return new VentaDAOJDBC();
    }
}