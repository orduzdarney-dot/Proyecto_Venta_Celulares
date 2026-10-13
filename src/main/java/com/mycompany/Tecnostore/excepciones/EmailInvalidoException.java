package com.mycompany.Tecnostore.excepciones;



public class EmailInvalidoException extends Exception {
    
    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
    
    public EmailInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}