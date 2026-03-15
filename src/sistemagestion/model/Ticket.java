package sistemagestion.model;
public class Ticket implements Imprimible,Calculable{
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
public double calcularTotal() {
    this.descuento = pasajero.obtenerDescuento();
    this.valorFinal = precioBase * (1 - this.descuento);
    return valorFinal;
}    public String getCodigo() {
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
        public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
    public void imprimirDetalle() {
        System.out.println("----- TICKET -----");
        System.out.println("Código: " + codigo);
        System.out.println("Pasajero: " + pasajero.getNombre());
        System.out.println("Fecha Compra: " + fechaCompra);
        System.out.println("Precio Base: " + precioBase);
        System.out.println("Descuento aplicado: " + (descuento*100) + "%");
        System.out.println("Valor Final: " + valorFinal);
        System.out.println("-----------------");
    }

}