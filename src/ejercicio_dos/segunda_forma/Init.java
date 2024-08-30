/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package ejercicio_dos.segunda_forma;

/**
 * Cocineros y repartidores. Cada cocinero se dedica a preparar pizzas, según van llegando pedidos.
 * Cada vez que una pizza está hecha, el cocinero la coloca sobre una bandeja para su reparto. A su
 * vez, cada repartidor espera a que haya una pizza sobre la bandeja, la retira y se la lleva al cliente
 * correspondiente. Tras ello regresa a la pizzeria y espera por una nueva pizza en la bandeja. Como
 * añadido, la bandeja tiene una capacidad limitada: no puede haber mas de MAX pizzas pendientes
 * de reparto.
 * 
 * @author Fernando GJ
 */
public class Init {

    public static void main(String[] args) {
        new Fabrica("5","3", "7").empezarSimulacion();

    } // end main

} // end Init
