package sistemagestion.model;

public abstract class Persona {

    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;

    public Persona() {
    }

    public Persona(String tipoDocumento, String documento, String nombre, String apellido, String telefono) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Tipo Documento: " + tipoDocumento
                + "\nDocumento: " + documento
                + "\nNombre: " + nombre
                + "\nApellido: " + apellido
                + "\nTelefono: " + telefono;
    }
}