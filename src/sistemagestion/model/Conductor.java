package sistemagestion.model;

public class Conductor extends Persona {

    private String numeroLicencia;
    private String categoriaLicencia;
    private String vencimientoLicencia;

    public Conductor() {
        super();
    }

    public Conductor(String tipoDocumento, String documento, String nombre,
                     String apellido, String telefono,
                     String numeroLicencia, String categoriaLicencia, String vencimientoLicencia) {

        super(tipoDocumento, documento, nombre, apellido, telefono);

        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public void setVencimientoLicencia(String vencimientoLicencia) {
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public void imprimirDetalle() {
        System.out.println("=== CONDUCTOR ===");
        System.out.println("Documento: " + getDocumento());
        System.out.println("Nombre: " + getNombre() + " " + getApellido());
        System.out.println("Teléfono: " + getTelefono());
        System.out.println("Número Licencia: " + numeroLicencia);
        System.out.println("Categoría Licencia: " + categoriaLicencia);
        System.out.println("Vencimiento Licencia: " + vencimientoLicencia);
    }

}