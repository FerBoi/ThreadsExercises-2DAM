/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cuatro.segunda_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Embotellador extends Thread {
    private PlantaEmbotelladora miTrabajo;
    
    public Embotellador(PlantaEmbotelladora trabajo) {
        this.miTrabajo = trabajo;
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.miTrabajo.ponerBotella();
                
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Embotellador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
} // end Embotellador
