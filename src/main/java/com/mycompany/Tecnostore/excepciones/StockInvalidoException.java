package com.mycompany.Tecnostore.excepciones;



public class StockInvalidoException extends Exception {
    
    public StockInvalidoException(String mensaje) {
        super(mensaje);
    }
    
    public StockInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}