/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public class Microbus extends Vehiculo {

    public Microbus(Conductor conductor, Ruta ruta, String placa, boolean disponible, int capacidad, float tarifaBase) {
        super(conductor, ruta, placa, disponible, capacidad, tarifaBase);
        this.capacidad=25;
        this.tarifaBase=10000;
    }
    
   
    
    
    public void imprimirDetalle() {
    System.out.println("=== MICROBUS ===");
    super.imprimirDetalle();
}
   
    
}
