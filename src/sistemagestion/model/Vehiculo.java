/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;



/**
 *
 * @author Maria Cristina
 */
public abstract class Vehiculo implements Imprimible  {
    protected Ruta ruta;
    protected String placa;
    protected boolean disponible;
    protected int capacidad;
    protected float tarifaBase;

    public Vehiculo(Ruta ruta, String placa, boolean disponible, int capacidad, float tarifaBase) {
        this.ruta = ruta;
        this.placa = placa;
        this.disponible = disponible;
        this.capacidad = capacidad;
        this.tarifaBase = tarifaBase;
    }


    public String getPlaca() {
        return placa;
    }

    public Ruta getRuta() {
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
    @Override
    
public void imprimirDetalle() {

    System.out.println("=== DETALLE DEL VEHICULO ===");
    System.out.println("Placa: " + placa);
    System.out.println("Ruta: " + ruta.getOrigen() + " - " + ruta.getDestino());
    System.out.println("Capacidad: " + capacidad);
    System.out.println("Disponible: " + disponible);
    System.out.println("Tarifa: " + tarifaBase);

}
    
}
