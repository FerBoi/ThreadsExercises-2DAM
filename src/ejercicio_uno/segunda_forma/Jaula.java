/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_uno.segunda_forma;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Jaula {
    private  final Loro[] LOROS;
    private final Semaphore CAPACIDAD_COMEDERO;
    private byte lorosComedero;
    private final Semaphore CAPACIDAD_COLUMPIO;
    
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
        this.CAPACIDAD_COMEDERO = new Semaphore(3);
        this.lorosComedero = 3;
        this.CAPACIDAD_COLUMPIO = new Semaphore(1);
        
        for (int i = 0; i < this.LOROS.length; i++)
            this.LOROS[i] = new Loro(this);
    }
    
    public void entrarComedero(Loro loro) {
        this.adquirirSemaforo(this.CAPACIDAD_COMEDERO);
        System.out.println(loro.getName() + " esta comiendo. Estado de comedero: " + --this.lorosComedero);
    }
    
    public void dejarComedero(Loro loro) {
        this.CAPACIDAD_COMEDERO.release();
        System.out.println(loro.getName() + " ha dejado de comer. Estado de comedero: " + ++this.lorosComedero);
    }
    
    public synchronized void entrarColumpio(Loro loro) {
        this.adquirirSemaforo(this.CAPACIDAD_COLUMPIO);
        System.out.println(loro.getName() + " esta columpiandose. Estado de columpio: Ocupado");
    }
    
    public void dejarColumpio(Loro loro) {
        System.out.println(loro.getName() + " ha dejado de columpiarse. Estado de comedero: libre");
        this.CAPACIDAD_COLUMPIO.release();
    }
    
    private void adquirirSemaforo(Semaphore semaforoAdquirir) {
        try {
            semaforoAdquirir.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void empezarSimulacion() {
        for (Loro loro : LOROS)
            loro.start();
    }

} // end Jaula