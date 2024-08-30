/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos_b.primera_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Empaquetador extends Thread {
    private final PlantaEmbotelladora MI_TRABAJO;
    
    public Empaquetador(PlantaEmbotelladora trabajo) {
        this.MI_TRABAJO = trabajo;
    }

    @Override
    public void run() {
        while(true) {
            try {
                 this.MI_TRABAJO.tomarCajaEmpaquetador();
                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Empaquetador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.MI_TRABAJO.reponerCaja();
        }
    }
    
    

} // end Empaquetador
