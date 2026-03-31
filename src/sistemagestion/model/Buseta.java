/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public class Buseta extends Vehiculo {

    public Buseta(Conductor conductor, Ruta ruta, String placa, boolean disponible, int capacidad, float tarifaBase) {
        super(conductor, ruta, placa, disponible, 19, 8000);
    }

    
    
    
    
  public void imprimirDetalle() {
    System.out.println("=== BUSETA ===");
    super.imprimirDetalle();
}
    
}
