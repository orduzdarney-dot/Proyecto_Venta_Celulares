package com.mycompany.Tecnostore.vista;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }

    public void mostrarExito(String mensaje) {
        System.out.println("EXITO: " + mensaje);
    }

    public int pedirOpcion(String mensaje) {
        System.out.print(mensaje + " -> ");
        int op = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea restante
        return op;
    }

    // 👇 AQUÍ ESTABA EL ERROR: Ahora solo lee una vez
    public String pedirTexto(String mensaje) {
        System.out.print(mensaje + " -> ");
        return scanner.nextLine(); 
    }

    public double pedirNumero(String mensaje) {
        System.out.print(mensaje + " -> ");
        double val = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el salto de línea restante
        return val;
    }

    public int pedirEntero(String mensaje) {
        System.out.print(mensaje + " -> ");
        int val = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea restante
        return val;
    }

    public void pausar() {
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    public boolean confirmarAccion(String mensaje) {
        System.out.println(mensaje + " (1=Si, 0=No)");
        System.out.print("Opción -> ");
        int op = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea restante
        return op == 1;
    }
}