package sistemagestion.model;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular() {
    }

    public PasajeroRegular(String tipoDocumento, String documento, String nombre,
                           String apellido, String telefono, String fechaNacimiento) {

        super(tipoDocumento, documento, nombre, apellido, telefono, fechaNacimiento);
    }

    @Override
    public double obtenerDescuento() {

        double descuento;

        descuento = 0;

        return descuento;
    }

}
