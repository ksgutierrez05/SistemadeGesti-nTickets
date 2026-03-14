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

}