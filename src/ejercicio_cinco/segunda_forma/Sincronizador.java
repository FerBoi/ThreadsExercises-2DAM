/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cinco.segunda_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Sincronizador {
    private int ejecucionesD;
    
    public synchronized void ejecucionAE() {
        this.ejecucionesD++;
        this.notifyAll();
    }
    
    public synchronized void ejecucionD() {
        while(this.ejecucionesD == 0)
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        this.ejecucionesD--;
        this.notifyAll();
    }

} // end Sincronizador
