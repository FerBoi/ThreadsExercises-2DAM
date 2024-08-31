/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cuatro.segunda_forma;

/**
 *
 * @author Fernando GJ
 */
public class Empaquetador extends Thread {
    private final PlantaEmbotelladora miTrabajo;
    
    public Empaquetador(PlantaEmbotelladora trabajo) {
        this.miTrabajo = trabajo;
    }

    @Override
    public void run() {
        while(true)
            this.miTrabajo.empaquetarCaja();
    }

} // end Empaquetador
