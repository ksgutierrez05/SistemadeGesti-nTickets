package sistemagestion.model;

public class Ticket {

    private String codigo;
    private Pasajero pasajero;
    private double precioBase;
    private double descuento;
    private double valorFinal;
    private String fechaCompra;

    public Ticket() {
    }

    public Ticket(String codigo, Pasajero pasajero, double precioBase, String fechaCompra) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.precioBase = precioBase;
        this.fechaCompra = fechaCompra;
    }

    public void calcularValorFinal() {

        descuento = pasajero.obtenerDescuento();

        valorFinal = precioBase - (precioBase * descuento);

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Codigo Ticket: " + codigo
                + "\nPasajero: " + pasajero.getNombre() + " " + pasajero.getApellido()
                + "\nPrecio Base: " + precioBase
                + "\nDescuento: " + descuento
                + "\nValor Final: " + valorFinal
                + "\nFecha Compra: " + fechaCompra;
    }
}
