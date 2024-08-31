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
public class H1 extends Thread {
    private final Sincronizador SINCRONIZADOR;
    
    public H1(Sincronizador sincronizador) {
        this.SINCRONIZADOR = sincronizador;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("A");
                this.SINCRONIZADOR.ejecucionAE();
                Thread.sleep(200);
                System.out.println("B");
            } catch (InterruptedException ex) {
                Logger.getLogger(H1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    

} // end H1
