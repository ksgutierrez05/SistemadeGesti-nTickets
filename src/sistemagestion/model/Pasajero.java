package sistemagestion.model;

public abstract class Pasajero extends Persona {

    private String fechaNacimiento;

    public Pasajero() {
    }

    public Pasajero(String tipoDocumento, String documento, String nombre,
                    String apellido, String telefono, String fechaNacimiento) {

        super(tipoDocumento, documento, nombre, apellido, telefono);
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public abstract double obtenerDescuento();
}