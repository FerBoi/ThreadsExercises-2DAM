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
public class H3 extends Thread {
    private final Sincronizador SINCRONIZADOR;
    
    public H3(Sincronizador sincronizador) {
        this.SINCRONIZADOR = sincronizador;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("E");
                this.SINCRONIZADOR.ejecucionAE();
                Thread.sleep(200);
                System.out.println("F");
            } catch (InterruptedException ex) {
                Logger.getLogger(H1.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    

} // end H3
