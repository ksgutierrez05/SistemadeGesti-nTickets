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
    
    public Microbus(String placa, String ruta, boolean disponible, int capacidad, float tarifaBase) {
        super(placa, ruta, disponible, capacidad, tarifaBase);
        this.capacidad=25;
        this.tarifaBase=10000;
    }
    
}
