/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package ejercicio_cinco.primera_forma;

import java.util.concurrent.Semaphore;

/**
 * Por cada ejecución de «a» o de «e» se debe permitir ejecutar una iteración de «d». Es decir, si
 * hasta ahora hemos ejecutado dos veces «a» y cinco veces «e», entonces podremos ejecutar hasta
 * siete veces la instrucción «d».
 * @author Fernando GJ
 */
public class Init {
    public final static Semaphore ACUMULADOR_D = new Semaphore(0);

    public static void main(String[] args) {
        new H1().start();
        new H2().start();
        new H3().start();

    } // end main

} // end Init
