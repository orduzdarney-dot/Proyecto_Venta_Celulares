
package com.mycompany.Tecnostore.servicio;



public class EstrategiaIvaGeneral implements EstrategiaCalculoImpuesto {
    @Override
    public double calcularImpuesto(double subtotal) {
        return subtotal * 0.19;
    }
}