package sistemagestion.model;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor() {
    }

    public PasajeroAdultoMayor(String tipoDocumento, String documento, String nombre,
                               String apellido, String telefono, String fechaNacimiento) {

        super(tipoDocumento, documento, nombre, apellido, telefono, fechaNacimiento);
    }

    @Override
    public double obtenerDescuento() {

        double descuento;

        descuento = 0.30;

        return descuento;
    }
public void imprimirDetalle() {
        System.out.println("----- PASAJERO ADULTO MAYOR -----");
        System.out.println("Nombre: " + getNombre() + " " + getApellido());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Fecha Nacimiento: " + getFechaNacimiento());
        System.out.println("Tipo: Adulto Mayor (30% descuento si edad >= 60)");
        System.out.println("---------------------------------");
    }
}