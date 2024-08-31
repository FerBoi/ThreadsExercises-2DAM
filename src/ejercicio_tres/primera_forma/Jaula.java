/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_tres.primera_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Jaula {
    private  final Loro[] LOROS;
    private byte capacidadComedero;
    
    private boolean machoColumpiandose;
    private boolean hembraColumpiandose;
    
    public Jaula(String cantidadInicialLoros) { 
        int num = 0;
        
        
        try {
             num = Integer.parseInt(cantidadInicialLoros);
             
             if(num < 1)
                 throw new NumberFormatException();
        } catch(NumberFormatException e) {
            throw new RuntimeException("Valor invalido");
        }
        
        this.LOROS = new Loro[num];
        this.capacidadComedero = 3;
        
        for (int i = 0; i < this.LOROS.length; i++)
            this.LOROS[i] = new Loro(this, i % 2 == 0);
    }
    
    public synchronized void entrarComedero(Loro loro) {
        while(this.capacidadComedero == 0)
            this.esperar();
        
        this.capacidadComedero--;
             System.out.println(loro.getName() + " esta comiendo. Estado de comedero: " + this.capacidadComedero);
    }
    
    public synchronized void dejarComedero(Loro loro) {
        this.capacidadComedero++;
        System.out.println(loro.getName() + " ha dejado de comer. Estado de comedero: " + this.capacidadComedero);
        
        this.notifyAll();
    }
    
    public synchronized void entrarColumpio(Loro loro) {
        boolean esHembra = loro.isHembra();
        
        while(esHembra ? this.hembraColumpiandose : this.machoColumpiandose)
            this.esperar();
        
        if(esHembra)
            this.hembraColumpiandose = true;
        else
            this.machoColumpiandose = true;
        
        System.out.println(loro.getName() + " esta columpiandose. Estado de columpio de " + (esHembra ? " hembras " : " machos ") +  "Ocupado");
    }
    
    public synchronized void dejarColumpio(Loro loro) {
        boolean esHembra = loro.isHembra();
        
        if (esHembra)
            this.hembraColumpiandose = false;
        else
            this.machoColumpiandose = false;

        System.out.println(loro.getName() + " deja de columpiarse. Estado de columpio de " + (esHembra ? " hembras " : " machos ") + "Libre");

        this.notifyAll();
    }
     
    private synchronized void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void empezarSimulacion() {
        for (Loro loro : LOROS)
            loro.start();
    }
   
} // end Jaula
