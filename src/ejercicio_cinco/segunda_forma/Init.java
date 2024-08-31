/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package ejercicio_cinco.segunda_forma;

/**
 *
 * @author Fernando GJ
 */
public class Init {

    public static void main(String[] args) {
        Sincronizador sincronizador = new Sincronizador();
        
        new H1(sincronizador).start();
        new H2(sincronizador).start();
        new H3(sincronizador).start();

    } // end main

} // end Init
