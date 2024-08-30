/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos.primera_forma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class Fabrica {
    private final Cocinero[] COCINEROS;
    private final Repartidor[] REPARTIDORES;
    private final ArrayList<String> PEDIDOS;
    private final String[] BANDEJA_PEDIDOS_LISTOS;

    private int indexProductor;
    private int indexConsumidor;
    
    public Fabrica(String numeroCocineros, String numeroRepartidores, String capacidadBandeja) {
        int numCocineros = 0;
        int numRepartidores = 0;
        int numBandeja = 0;
        
        try {
            numCocineros = Integer.parseInt(numeroCocineros);
            numRepartidores = Integer.parseInt(numeroRepartidores);
            numBandeja = Integer.parseInt(capacidadBandeja);
            
            if(numCocineros < 1 || numRepartidores < 1 || numBandeja < 1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Valor invalido de cocineros, repartidores o capacidad de bandeja");
        }
        
        this.COCINEROS = new Cocinero[numCocineros];
        this.REPARTIDORES = new Repartidor[numRepartidores];
        this.PEDIDOS = new ArrayList<>();
        this.BANDEJA_PEDIDOS_LISTOS = new String[numBandeja];
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
                
                synchronized (this) {
                    PEDIDOS.add("PIZZA");
                    System.out.println("- NUEVO PEDIDO GENERADO -. Numero de pedidos generados: " + PEDIDOS.size());
                    this.notifyAll();
                }                
            }
            
        }).start();
    }
    
    public synchronized String pillarPedido(Cocinero cocinero) {
        while(this.PEDIDOS.size() < 1)
            this.esperar();
        
        String pedido =  this.PEDIDOS.get(0);
        this.PEDIDOS.remove(0);
        System.out.println("El cocinero " + cocinero.getName() + " ha pillado un pedido: " + pedido);
        return pedido;
    }
    
    public synchronized void dejarPedidoBandeja(String pedido) {
        while(this.BANDEJA_PEDIDOS_LISTOS[this.indexProductor] != null)
            this.esperar();
        
        this.BANDEJA_PEDIDOS_LISTOS[this.indexProductor] = pedido;
        System.out.println("Se ha dejado un pedido en la posicion " + this.indexProductor + " --> " + Arrays.toString(this.BANDEJA_PEDIDOS_LISTOS));
        this.indexProductor = (this.indexProductor + 1) % this.BANDEJA_PEDIDOS_LISTOS.length;
        this.notifyAll();
    }
    
    public synchronized void repartirPedido(Repartidor repartidor) {
        while(this.BANDEJA_PEDIDOS_LISTOS[this.indexConsumidor] == null)
            this.esperar();
        
        this.BANDEJA_PEDIDOS_LISTOS[this.indexConsumidor] = null;
        System.out.println("El repartidor " + repartidor.getName() + " se ha llevado el pedido de la posicion " + this.indexConsumidor + " --> " + Arrays.toString(this.BANDEJA_PEDIDOS_LISTOS));
        this.indexConsumidor = (this.indexConsumidor + 1) % this.BANDEJA_PEDIDOS_LISTOS.length;
        this.notifyAll();
    }
    
    private synchronized void esperar() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} // end Fabrica
