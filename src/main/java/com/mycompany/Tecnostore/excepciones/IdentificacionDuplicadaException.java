package com.mycompany.Tecnostore.excepciones;




public class IdentificacionDuplicadaException extends Exception {
    
    public IdentificacionDuplicadaException(String mensaje) {
        super(mensaje);
    }
    
    public IdentificacionDuplicadaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}