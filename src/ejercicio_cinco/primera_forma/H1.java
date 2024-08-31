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
public class H1 extends Thread {

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("A");
                Init.ACUMULADOR_D.release();
                Thread.sleep(200);
                System.out.println("B");
            } catch (InterruptedException ex) {
                Logger.getLogger(H1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

} // end h1
