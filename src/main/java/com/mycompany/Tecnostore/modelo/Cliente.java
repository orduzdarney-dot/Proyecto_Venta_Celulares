package com.mycompany.Tecnostore.modelo;



import com.mycompany.Tecnostore.excepciones.EmailInvalidoException;

public class Cliente {
    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String telefono;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String identificacion, String correo, String telefono) throws EmailInvalidoException {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        setCorreo(correo);
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) throws EmailInvalidoException {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!correo.matches(regex)) {
            throw new EmailInvalidoException("El formato del correo electrónico no es válido");
        }
        this.correo = correo;
    }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", identificacion=" + identificacion + ", correo=" + correo + '}';
    }
}