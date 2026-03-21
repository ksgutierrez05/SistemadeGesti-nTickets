package sistemagestion.service;
import sistemagestion.dao.ConductorDAO;
import sistemagestion.dao.pasajeroDAO;
import sistemagestion.model.Conductor;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroAdultoMayor;
import java.util.List;
public class PersonaService {
    private pasajeroDAO pasajeroDAO;
    private ConductorDAO conductorDAO;
    public PersonaService() {
        this.pasajeroDAO = new pasajeroDAO();
        this.conductorDAO = new ConductorDAO();
    }
        public void registrarPasajero(Pasajero pasajero, String fechaCompra) {
        if (pasajero == null) {
            throw new IllegalArgumentException("Pasajero nulo");
        }
        if (pasajero.getDocumento() == null || pasajero.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        if (!pasajero.getDocumento().trim().matches("\\d+")) {
            throw new IllegalArgumentException("Documento inválido: solo se permiten números");
        }
        if (pasajero.getNombre() == null || pasajero.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (pasajero.getApellido() == null || pasajero.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (pasajero.getFechaNacimiento() == null || pasajero.getFechaNacimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía");
        }
        if (!fechaValida(pasajero.getFechaNacimiento())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida: " + pasajero.getFechaNacimiento());
        }
        if (pasajeroDAO.existePasajero(pasajero.getDocumento().trim())) {
            throw new IllegalArgumentException("Ya existe un pasajero con el documento: " + pasajero.getDocumento().trim());
        }
        int edad = pasajero.calcularEdad(fechaCompra);
        if (edad >= 60 && !(pasajero instanceof PasajeroAdultoMayor)) {
            pasajero = new PasajeroAdultoMayor(
                    pasajero.getTipoDocumento(),
                    pasajero.getDocumento(),
                    pasajero.getNombre(),
                    pasajero.getApellido(),
                    pasajero.getTelefono(),
                    pasajero.getFechaNacimiento()
            );
        }
        pasajeroDAO.agregarPasajero(pasajero);
        System.out.println("Pasajero registrado exitosamente.");
    }
        
    private boolean fechaValida(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return false;
        String[] partes = fecha.split("-");
        if (partes.length != 3) return false;
        try {
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);
            if (mes < 1 || mes > 12) return false;
            if (dia < 1 || dia > 31) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public Pasajero buscarPasajero(String documento) {
        Pasajero p = pasajeroDAO.buscarPasajero(documento);
        if (p == null) {
            throw new IllegalArgumentException("No se encontró pasajero con documento: " + documento);
        }
        return p;
    }
    public List<Pasajero> listarPasajeros() {
        List<Pasajero> lista = pasajeroDAO.listarPasajeros();
        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        }
        return lista;
    }
    public void eliminarPasajero(String documento) {
        if (!pasajeroDAO.existePasajero(documento)) {
            throw new IllegalArgumentException("No existe pasajero con documento: " + documento);
        }
        pasajeroDAO.eliminarPasajero(documento);
        System.out.println("Pasajero eliminado exitosamente.");
    }
    public void modificarPasajero(Pasajero pasajero) {
        if (pasajero == null) {
            throw new IllegalArgumentException("Pasajero nulo");
        }
        if (!pasajeroDAO.existePasajero(pasajero.getDocumento())) {
            throw new IllegalArgumentException("No existe pasajero con documento: " + pasajero.getDocumento());
        }
        if (!pasajero.getFechaNacimiento().trim().isEmpty() && !fechaValida(pasajero.getFechaNacimiento())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida: " + pasajero.getFechaNacimiento());
        }
        pasajeroDAO.modificarPasajero(pasajero);
        System.out.println("Pasajero modificado exitosamente.");
    }
    public void registrarConductor(Conductor conductor) {
        if (conductor == null) throw new IllegalArgumentException("Conductor nulo");
        if (conductor.getDocumento() == null || conductor.getDocumento().trim().isEmpty())
            throw new IllegalArgumentException("El documento no puede estar vacío");
        if (conductor.getNombre() == null || conductor.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (conductor.getApellido() == null || conductor.getApellido().trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        if (conductor.getTelefono() == null || conductor.getTelefono().trim().isEmpty())
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        if (conductor.getNumeroLicencia() == null || conductor.getNumeroLicencia().trim().isEmpty())
            throw new IllegalArgumentException("El número de licencia no puede estar vacío");
        if (conductor.getCategoriaLicencia() == null || conductor.getCategoriaLicencia().trim().isEmpty())
            throw new IllegalArgumentException("La categoría de licencia no puede estar vacía");
        if (!conductor.getCategoriaLicencia().trim().equalsIgnoreCase("C1") &&
            !conductor.getCategoriaLicencia().trim().equalsIgnoreCase("C2"))
            throw new IllegalArgumentException("Categoría de licencia no válida: solo se acepta C1 o C2");
        if (conductor.getVencimientoLicencia() == null || conductor.getVencimientoLicencia().trim().isEmpty())
            throw new IllegalArgumentException("El vencimiento de licencia no puede estar vacío");
        conductorDAO.agregarConductor(conductor);
            if (!fechaValida(conductor.getVencimientoLicencia())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida: " + conductor.getVencimientoLicencia());
        }
    }
    public Conductor buscarConductor(String documento) {
        Conductor c = conductorDAO.buscarConductor(documento);
        if (c == null) throw new IllegalArgumentException("Conductor no encontrado: " + documento);
        return c;
    }
    public List<Conductor> listarConductores() {
        List<Conductor> lista = conductorDAO.listarConductores();
        if (lista.isEmpty()) {
            System.out.println("No hay conductores registrados.");
        }
        return lista;
    }
    public void modificarConductor(Conductor conductor) {
        if (conductor == null) throw new IllegalArgumentException("Conductor nulo");
        if (conductor.getNombre() == null || conductor.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (conductor.getApellido() == null || conductor.getApellido().trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        if (conductor.getTelefono() == null || conductor.getTelefono().trim().isEmpty())
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        if (conductor.getNumeroLicencia() == null || conductor.getNumeroLicencia().trim().isEmpty())
            throw new IllegalArgumentException("El número de licencia no puede estar vacío");
        if (conductor.getCategoriaLicencia() == null || conductor.getCategoriaLicencia().trim().isEmpty())
            throw new IllegalArgumentException("La categoría de licencia no puede estar vacía");
        if (conductor.getVencimientoLicencia() == null || conductor.getVencimientoLicencia().trim().isEmpty())
            throw new IllegalArgumentException("El vencimiento de licencia no puede estar vacío");
        if (conductorDAO.existeConductor(conductor.getDocumento()) == null)
            throw new IllegalArgumentException("No existe conductor con documento: " + conductor.getDocumento());
        conductorDAO.modificarConductor(conductor);
        System.out.println("Conductor modificado exitosamente.");
    }
    public void eliminarConductor(String documento) {
        conductorDAO.eliminarConductor(documento);
    }
}