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
public void imprimirDetalle() {
        System.out.println("----- PASAJERO ESTUDIANTE -----");
        System.out.println("Nombre: " + getNombre() + " " + getApellido());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("Fecha Nacimiento: " + getFechaNacimiento());
        System.out.println("Tipo: Estudiante (15% descuento)");
        System.out.println("-------------------------------");
    }
}
