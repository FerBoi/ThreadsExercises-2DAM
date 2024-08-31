/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cuatro.segunda_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class PlantaEmbotelladora {
    private byte capacidadCaja;
    
    public PlantaEmbotelladora() {
        this.capacidadCaja = 10;
    }
    
    public synchronized void ponerBotella() {
        while(this.capacidadCaja == 0)
            this.esperar();
        
        System.out.println("Se ha puesto una botella en la caja. Capacidad restante: " + --this.capacidadCaja);
        
        if(this.capacidadCaja == 0)
            this.notifyAll();
    }
    
    public synchronized void empaquetarCaja() {
        while (this.capacidadCaja != 0)
            this.esperar();
            
        System.out.println("El empaquetador ha empaquetado la caja y respuesto otra");
        this.capacidadCaja = 10;
        
        this.notifyAll();
    }
    
    private synchronized void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void empezarSimulacion() {
        new Empaquetador(this).start();
        
        for (int i = 0; i < 10; i++) {
            new Embotellador(this).start();
        }
    }

} // end PlantaEmbotelladora
