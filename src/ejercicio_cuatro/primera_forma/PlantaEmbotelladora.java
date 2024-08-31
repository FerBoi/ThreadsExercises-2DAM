/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_cuatro.primera_forma;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class PlantaEmbotelladora {
    private static final Semaphore CAPACIDAD_CAJA;
    private static final Semaphore AVISO_EMPAQUETADOR;
    
    private static final Lock MUTEX;
    private static byte capacidadCaja;
    
    static {
        CAPACIDAD_CAJA = new Semaphore(10);
        AVISO_EMPAQUETADOR = new Semaphore(0);
        MUTEX = new ReentrantLock();
        capacidadCaja = 10;
    }
    
    public static void ponerBotella() {
        adquirirSemaforo(CAPACIDAD_CAJA);
        
        try {
            MUTEX.lock();            
            System.out.println("Se ha puesto una botella en la caja. Capacidad caja: " + --capacidadCaja);
            
            if(capacidadCaja == 0)
                AVISO_EMPAQUETADOR.release();
        } finally {
            MUTEX.unlock();
        }
    }
    
    public static void empaquetarCaja() {
        adquirirSemaforo(AVISO_EMPAQUETADOR);
        capacidadCaja = 10;
        System.out.println("El empaquetador ha empaquetado la caja y respuesto otra");
        CAPACIDAD_CAJA.release(10);
    }

    private static void adquirirSemaforo(Semaphore semaforoAdquirir) {
        try {
            semaforoAdquirir.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void empezarSimulacion() {
        new Empaquetador().start();
        
        for (int i = 0; i < 10; i++)
            new Embotellador().start();
    }
    

} // end PlantaEmbotelladora
