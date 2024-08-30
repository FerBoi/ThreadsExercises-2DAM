/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos_b.segunda_forma;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando GJ
 */
public class PlantaEmbotelladora {
    private static final Semaphore BOTELLAS_DISPONIBLES;
    private static final Semaphore AVISO;
    
    static {
        BOTELLAS_DISPONIBLES = new Semaphore(0);
        AVISO = new Semaphore(1);
    }
    
    public static void avisarEmpaquetador() {
        adquirirSemaforo(BOTELLAS_DISPONIBLES, 10);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Se avisa al empaquetador para llevarse una caja");
        AVISO.release();
    }

    public static void reponerCaja() {
        adquirirSemaforo(AVISO, 1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("El empaquetador ha repuesto una caja");
        BOTELLAS_DISPONIBLES.release(10);
    }

    private static void adquirirSemaforo(Semaphore semaforoAdquirir, int numPermisos) {
        try {
            semaforoAdquirir.acquire(numPermisos);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantaEmbotelladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void empezarSimulacion() {
        new Embotellador().start();
        new Empaquetador().start();
    }

} // end PlantaEmbotelladora
