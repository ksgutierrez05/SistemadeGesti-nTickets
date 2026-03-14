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

    public int calcularEdad(String fechaCompra) {
        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) return 0;

        String[] partesNac = fechaNacimiento.split("-"); // ["yyyy","MM","dd"]
        int anioNac = Integer.parseInt(partesNac[0]);
        int mesNac  = Integer.parseInt(partesNac[1]);
        int diaNac  = Integer.parseInt(partesNac[2]);

        String[] partesCompra = fechaCompra.split("-"); // ["yyyy","MM","dd"]
        int anioCompra = Integer.parseInt(partesCompra[0]);
        int mesCompra  = Integer.parseInt(partesCompra[1]);
        int diaCompra  = Integer.parseInt(partesCompra[2]);

        int edad = anioCompra - anioNac;

        if (mesCompra < mesNac || (mesCompra == mesNac && diaCompra < diaNac)) {
            edad--;
        }

        return edad;
    }
    public abstract double obtenerDescuento();
}