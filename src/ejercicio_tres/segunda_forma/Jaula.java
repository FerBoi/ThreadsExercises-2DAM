/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_tres.segunda_forma;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Jaula {
    private final Loro[] LOROS;
    private final Semaphore COMEDERO;
    private final Semaphore COLUMPIO_MACHOS;
    private final Semaphore COLUMPIO_HEMBRAS;
    
    private byte capacidadComedero;
    private Lock mutexCapacidadComedero;
    
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
        this.COMEDERO = new Semaphore(3);
        this.COLUMPIO_MACHOS = new Semaphore(1);
        this.COLUMPIO_HEMBRAS = new Semaphore(1);
        this.capacidadComedero = 3;
        this.mutexCapacidadComedero = new ReentrantLock();
        
        for (int i = 0; i < this.LOROS.length; i++)
            this.LOROS[i] = new Loro(this, i % 2 == 0);
    }
    
    public void entrarComedero(Loro loro) {
        this.adquirirSemaforo(this.COMEDERO);
        this.mutexCapacidadComedero.lock();

        System.out.println(loro.getName() + " esta comiendo. Estado de comedero: " + --this.capacidadComedero);
        this.mutexCapacidadComedero.unlock();
    }
    
    public void dejarComedero(Loro loro) {
        this.mutexCapacidadComedero.lock();
        System.out.println(loro.getName() + " ha dejado de comer. Estado de comedero: " + ++this.capacidadComedero);
        this.mutexCapacidadComedero.unlock();
        this.COMEDERO.release();
    }
    
    public void entrarColumpio(Loro loro) {
        boolean esHembra = loro.isHembra();
        
        if(esHembra)
            this.adquirirSemaforo(this.COLUMPIO_HEMBRAS);
        else
            this.adquirirSemaforo(this.COLUMPIO_MACHOS);
        
        System.out.println(loro.getName() + " esta columpiandose. Estado de columpio de " + (esHembra ? " hembras " : " machos ") +  "Ocupado");
    }
    
    public void dejarColumpio(Loro loro) {
        boolean esHembra = loro.isHembra();
        
        if(esHembra)
            this.COLUMPIO_HEMBRAS.release();
        else
            this.COLUMPIO_MACHOS.release();

        System.out.println(loro.getName() + " deja de columpiarse. Estado de columpio de " + (esHembra ? " hembras " : " machos ") + "Libre");
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
