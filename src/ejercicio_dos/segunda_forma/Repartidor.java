/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos.segunda_forma;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Repartidor extends Thread {
    private Fabrica miTrabajo;
    
    public Repartidor(Fabrica trabajo) {
        this.miTrabajo = trabajo;
    }

    @Override
    public void run() {
        Random r = new Random();
        
        while(true) {
            try {
                this.miTrabajo.repartirPedido(this);
                
                Thread.sleep(r.nextInt(1000, 1501));
            } catch (InterruptedException ex) {
                Logger.getLogger(Repartidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
} // end Repartidor
