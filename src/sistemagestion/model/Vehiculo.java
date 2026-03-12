/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public abstract class Vehiculo {
    protected String placa;
    protected String ruta;
    protected boolean disponible;
    protected int capacidad;
    protected float tarifaBase;

   

    public Vehiculo(String placa, String ruta, boolean disponible, int capacidad, float tarifaBase) {
        this.placa = placa;
        this.ruta = ruta;
        this.disponible = disponible;
        this.capacidad = capacidad;
        this.tarifaBase = tarifaBase;
    }

    public String getPlaca() {
        return placa;
    }

    public String getRuta() {
        return ruta;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public float getTarifaBase() {
        return tarifaBase;
    }
    
    
    
}
