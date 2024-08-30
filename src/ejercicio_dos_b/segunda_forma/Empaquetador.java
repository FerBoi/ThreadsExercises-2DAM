/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_dos_b.segunda_forma;

/**
 *
 * @author Fernando GJ
 */
public class Empaquetador extends Thread {

    @Override
    public void run() {
        while(true)
            PlantaEmbotelladora.reponerCaja();
    }
    
    

} // end Empaquetador
