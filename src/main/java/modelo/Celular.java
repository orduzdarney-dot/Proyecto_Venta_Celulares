
package modelo;


public class Celular {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private SistemaOperativo sistemaOperativo;
    private Gama gama;
    
    public Celular(){
}

    public Celular(int id, String marca, String modelo, double precio, int stock, SistemaOperativo sistemaOperativo, Gama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        setPrecio(precio);
        setStock(stock);
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("el precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0 ){
            throw new IllegalArgumentException("el stock no puede ser negativo");
        }
        this.stock = stock;
    }

    public SistemaOperativo getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }  
        
    @Override
    public String toString(){
        return "Celular{" + "id=" + id +", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", stock=" + stock + ", gama=" + gama +  '}';
    }
}

