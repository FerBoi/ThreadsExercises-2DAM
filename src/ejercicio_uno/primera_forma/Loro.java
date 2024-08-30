/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_uno.primera_forma;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Loro extends Thread {
    private final Jaula MI_JAULA;
    
    public Loro(Jaula jaula) {
        this.MI_JAULA = jaula;
    }
    
    private void dormir(long tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Loro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        Random r = new Random();
        
        while (true) {
            this.MI_JAULA.entrarComedero(this);
            this.dormir(r.nextInt(200, 1001));
            this.MI_JAULA.dejarComedero(this);
            this.MI_JAULA.entrarColumpio(this);
            this.dormir(r.nextInt(100, 201));
            this.MI_JAULA.dejarColumpio(this);
        }
    }

} // end Loro
