/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public class Bus extends Vehiculo {

    public Bus(Conductor conductor, Ruta ruta, String placa, boolean disponible, int capacidad, float tarifaBase) {
        super(conductor, ruta, placa, disponible, 45 , 15000);
    }

    
    
    
    
    public void imprimirDetalle() {
    System.out.println("=== BUS====");
    super.imprimirDetalle();
}
    
}
