/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cinco.primera_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class H2 extends Thread {

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("C");
                Thread.sleep(200);
                Init.ACUMULADOR_D.acquire();
                System.out.println("D");
            } catch (InterruptedException ex) {
                Logger.getLogger(H2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

} // end h2
