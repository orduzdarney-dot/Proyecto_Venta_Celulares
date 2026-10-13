
package com.mycompany.Tecnostore.vista;



public class LoginView {
    private MenuView menuView;

    public LoginView(MenuView menuView) {
        this.menuView = menuView;
    }

    public String pedirUsuario() {
        return menuView.pedirTexto("Ingrese su usuario");
    }

    public String pedirPassword() {
        return menuView.pedirTexto("Ingrese su contraseña");
    }

    public void mostrarErrorLogin() {
        menuView.mostrarError("Usuario o contraseña incorrectos. Intente de nuevo.");
    }
}