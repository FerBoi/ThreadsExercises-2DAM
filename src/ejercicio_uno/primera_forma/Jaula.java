/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_uno.primera_forma;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Jaula {
    private  final Loro[] LOROS;
    private byte capacidadComedero;
    private boolean columpioOcupado;
    
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
            this.LOROS[i] = new Loro(this);
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
        while(this.columpioOcupado)
            this.esperar();
        
        this.columpioOcupado = true;
        System.out.println(loro.getName() + " esta columpiandose. Estado de columpio: Ocupado");
    }
    
    public synchronized void dejarColumpio(Loro loro) {
        this.columpioOcupado = false;
        System.out.println(loro.getName() + " ha dejado de columpiarse. Estado de comedero: libre");

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
