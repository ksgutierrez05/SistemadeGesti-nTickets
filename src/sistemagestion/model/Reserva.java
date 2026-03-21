/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public class Reserva {

    private String codigo;
    private String documentoPasajero;
    private String placaVehiculo;
    private String fechaCreacion;
    private String fechaViaje;
    private String estado;

    public Reserva() {
    }

    public Reserva(String codigo, String documentoPasajero, String placaVehiculo, String fechaCreacion, String fechaViaje, String estado) {
        this.codigo = codigo;
        this.documentoPasajero = documentoPasajero;
        this.placaVehiculo = placaVehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumentoPasajero() {
        return documentoPasajero;
    }

    public void setDocumentoPasajero(String documentoPasajero) {
        this.documentoPasajero = documentoPasajero;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
