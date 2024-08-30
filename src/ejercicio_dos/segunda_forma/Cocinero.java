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
public class Cocinero extends Thread {
    private final Fabrica MI_TRABAJO;
    
    public Cocinero(Fabrica trabajo) {
        this.MI_TRABAJO = trabajo;
    }

    @Override
    public void run() {
        Random r = new Random();
        
        while(true) {
            this.MI_TRABAJO.pillarPedido(this);
            
            try {
                Thread.sleep(r.nextInt(100, 1001));
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.MI_TRABAJO.dejarPedidoBandeja();
        }
    }

} // end Cocinero
