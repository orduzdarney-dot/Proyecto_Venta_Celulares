
package modelo;


public class Empleado {
    
    private int id;
    private String nombre;
    private String usuario;
    private String contraseña;
    private RolEmpleado rol;
   
public Empleado(){
}

    public Empleado(int id, String nombre, String usuario, String contraseña, RolEmpleado rol) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public RolEmpleado getRol() {
        return rol;
    }

    public void setRol(RolEmpleado rol) {
        this.rol = rol;
    }

@Override 
public String toString(){
    return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", rol=" + rol + '}';
}
}