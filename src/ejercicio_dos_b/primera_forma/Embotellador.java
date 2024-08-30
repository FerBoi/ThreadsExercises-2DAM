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
public class Embotellador extends Thread {
    private final PlantaEmbotelladora MI_TRABAJO;
    
    public Embotellador(PlantaEmbotelladora trabajo) {
        this.MI_TRABAJO = trabajo;
    }

    @Override
    public void run() {        
        while(true) {
            String[] caja = this.MI_TRABAJO.tomarCajaEmbotellador();
            
            for (int i = 0; i < caja.length; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Embotellador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                caja[i] = "BOTELLA";
            } 
            
            this.MI_TRABAJO.avisarEmpaquetador(caja);
        }
    }
    
    

} // end Embotellador
