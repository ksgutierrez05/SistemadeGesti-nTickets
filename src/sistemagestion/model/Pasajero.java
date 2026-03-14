package sistemagestion.model;

public abstract class Pasajero extends Persona {

    public Pasajero() {
    }

    public Pasajero(String tipoDocumento, String documento, String nombre,
                    String apellido, String telefono, String fechaNacimiento) {
        super(tipoDocumento, documento, nombre, apellido, telefono, fechaNacimiento);
    }

    public abstract double obtenerDescuento();

}
