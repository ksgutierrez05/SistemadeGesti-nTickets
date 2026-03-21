/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.model;

/**
 *
 * @author Maria Cristina
 */
public class Ruta {
    private String codigo;
    private String origen;
    private String destino;
    private String distancia;
    private int tiempo;

    public Ruta() {
    }

    public Ruta(String codigo, String origen, String destino, String distancia, int tiempo) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getDistancia() {
        return distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    
    
}
