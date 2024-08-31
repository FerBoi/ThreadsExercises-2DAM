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
public class H2 extends Thread {
    private final Sincronizador SINCRONIZADOR;
    
    public H2(Sincronizador sincronizador) {
        this.SINCRONIZADOR = sincronizador;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("C");
                Thread.sleep(200);
                this.SINCRONIZADOR.ejecucionD();
                System.out.println("D");
            } catch (InterruptedException ex) {
                Logger.getLogger(H1.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    

} // end H2
