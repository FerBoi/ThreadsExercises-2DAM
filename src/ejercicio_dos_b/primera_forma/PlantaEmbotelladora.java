/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos_b.primera_forma;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class PlantaEmbotelladora {
    private String[] caja;
    private boolean cajaLlena;
    
    public synchronized void avisarEmpaquetador(String[] caja) {
        while(this.cajaLlena)
            this.esperar();
        
        System.out.println("Se avisa al empaquetador para llevarse una caja");
        this.caja = caja;
        this.cajaLlena = true;
        this.notifyAll();
    }
    
    public synchronized void reponerCaja() {
        while(!this.cajaLlena)
            this.esperar();
        
        System.out.println("El empaquetador ha repuesto una caja");
        this.caja = new String[10];
        this.cajaLlena = false;
        this.notifyAll();
    }
    
    public synchronized String[] tomarCajaEmbotellador() {
        while(this.cajaLlena)
            this.esperar();
        
        System.out.println("El embotellador toma una nueva caja vacia");
        this.caja = new String[10];
        return this.caja;
    }
    
    public synchronized String[] tomarCajaEmpaquetador() {
        while(!this.cajaLlena)
            this.esperar();
        
        System.out.println("El empaquetador toma una nueva caja llena - " + Arrays.toString(this.caja));
        this.notifyAll();
        
        return this.caja;
    }
    
    private synchronized void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void empezarSimulacion() {
        new Embotellador(this).start();
        new Empaquetador(this).start();
    }

} // end PlantaEmbotelladora
