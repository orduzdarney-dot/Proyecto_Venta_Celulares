package com.mycompany.Tecnostore.excepciones;




public class PrecioInvalidoException extends Exception {
    
    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
    
    public PrecioInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}