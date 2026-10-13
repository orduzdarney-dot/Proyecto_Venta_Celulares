
package com.mycompany.Tecnostore.servicio;



public class EstrategiaSinImpuesto implements EstrategiaCalculoImpuesto {
    @Override
    public double calcularImpuesto(double subtotal) {
        return 0;
    }
}