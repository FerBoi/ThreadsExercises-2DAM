/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos.segunda_forma;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Fabrica {
    private final Cocinero[] COCINEROS;
    private final Repartidor[] REPARTIDORES;
    
    private final Lock MUTEX; 
    private Semaphore pedidosRealizados;
    private Semaphore pedidosListos;
    private Semaphore huecosBandejaPizzas;

    private int numBandejaPizzas;
    private int numPedidosRealizados;
    private int indexProductor;
    private int indexConsumidor;
    
    public Fabrica(String numeroCocineros, String numeroRepartidores, String capacidadBandeja) {
        int numCocineros = 0;
        int numRepartidores = 0;
        
        try {
            numCocineros = Integer.parseInt(numeroCocineros);
            numRepartidores = Integer.parseInt(numeroRepartidores);
            this.numBandejaPizzas = Integer.parseInt(capacidadBandeja);
            
            if(numCocineros < 1 || numRepartidores < 1 || this.numBandejaPizzas < 1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valor invalido de cocineros, repartidores o capacidad de bandeja");
        }
        
        this.COCINEROS = new Cocinero[numCocineros];
        this.REPARTIDORES = new Repartidor[numRepartidores];
        
        this.pedidosRealizados = new Semaphore(0);
        this.huecosBandejaPizzas = new Semaphore(this.numBandejaPizzas);
        this.pedidosListos = new Semaphore(0);
        
        this.MUTEX = new ReentrantLock();
    }
    
    public void empezarSimulacion() {
        for (int i = 0; i < Math.max(this.COCINEROS.length, this.REPARTIDORES.length); i++) {
            if(i < this.COCINEROS.length) {
                this.COCINEROS[i] = new Cocinero(this);
                this.COCINEROS[i].start();
            }
            
            if(i < this.REPARTIDORES.length) {
                this.REPARTIDORES[i] = new Repartidor(this);
                this.REPARTIDORES[i].start();
            }
        }
        
        new Thread(() -> {
            Random r = new Random();
            
            while(true) {
                try {
                    Thread.sleep(r.nextInt(100, 1000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.MUTEX.lock();
                System.out.println("- NUEVO PEDIDO GENERADO -. Numero de pedidos generados: " + ++this.numPedidosRealizados);
                this.pedidosRealizados.release();
                this.MUTEX.unlock();
            }
            
        }).start();
    }
    
    public void pillarPedido(Cocinero cocinero) {
        this.adquirirSemaforo(this.pedidosRealizados);
        System.out.println("El cocinero " + cocinero.getName() + " ha pillado un pedido");
    }
    
    public void dejarPedidoBandeja() {
        this.adquirirSemaforo(this.huecosBandejaPizzas);
        this.MUTEX.lock();
        this.numPedidosRealizados--;
        System.out.println("Se ha dejado un pedido en la posicion " + this.indexProductor);
        this.indexProductor = (this.indexProductor + 1) % this.numBandejaPizzas;
        
        this.MUTEX.unlock();
        this.pedidosListos.release();
    }
    
    public synchronized void repartirPedido(Repartidor repartidor) {
        this.adquirirSemaforo(this.pedidosListos);
        
        this.MUTEX.lock();
        System.out.println("El repartidor " + repartidor.getName() + " se ha llevado el pedido de la posicion " + this.indexConsumidor);
        this.indexConsumidor = (this.indexConsumidor + 1) % this.numBandejaPizzas;
        
        this.MUTEX.unlock();
        this.huecosBandejaPizzas.release();
    }
    
    private synchronized void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adquirirSemaforo(Semaphore semaforoAdquirir) {
        try {
            semaforoAdquirir.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} // end Fabrica
