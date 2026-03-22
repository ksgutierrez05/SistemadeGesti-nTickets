package sistemagestion.model;
public class Ticket implements Imprimible,Calculable{
    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private double precioBase;
    private double descuento;
    private double valorFinal;
    private String fechaCompra;
    public Ticket() {
    }
    public Ticket(String codigo, Pasajero pasajero,Vehiculo vehiculo, double precioBase, String fechaCompra) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.precioBase = vehiculo.getTarifaBase();
        this.fechaCompra = fechaCompra;
    }

    public Ticket(String codigoTicket, Pasajero pasajero, String vehiculo, int i, String fechaViaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public double calcularTotal(boolean esFestivo) {
    double base = precioBase;
    if (esFestivo) {
        base *= 1.2;
    }
    this.descuento = pasajero.obtenerDescuento();
    this.valorFinal = base * (1 - this.descuento);
    return valorFinal;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.precioBase = vehiculo.getTarifaBase();
    }
    public String getCodigo() {
    return codigo;
}
    @Override
public double calcularTotal() {
    return calcularTotal(false);
}
    
public void imprimirDetalle() {
        System.out.println("===== TICKET =====");
        System.out.println("Código: " + codigo);
        System.out.println("Fecha de Compra: " + fechaCompra);

        System.out.println("\n--- PASAJERO ---");
        pasajero.imprimirDetalle(); 

        System.out.println("\n--- VEHÍCULO ---");
        vehiculo.imprimirDetalle();  

        System.out.println("\nPrecio Base: $" + precioBase);
        System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
        System.out.println("Valor Final: $" + valorFinal);
        System.out.println("=================\n");
    }

    public Object getFechaViaje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}