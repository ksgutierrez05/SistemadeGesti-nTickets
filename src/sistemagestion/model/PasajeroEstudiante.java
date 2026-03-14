package sistemagestion.model;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante() {
    }

    public PasajeroEstudiante(String tipoDocumento, String documento, String nombre,
                              String apellido, String telefono, String fechaNacimiento) {

        super(tipoDocumento, documento, nombre, apellido, telefono, fechaNacimiento);
    }

    @Override
    public double obtenerDescuento() {

        double descuento;

        descuento = 0.15;

        return descuento;
    }

}