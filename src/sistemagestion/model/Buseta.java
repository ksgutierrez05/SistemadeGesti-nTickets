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
    
    public Buseta(String placa, String ruta, boolean disponible, int capacidad, float tarifaBase) {
        super(placa, ruta, disponible, capacidad, tarifaBase);
        this.capacidad=19;
        this.tarifaBase=8000;
    }
    
}
