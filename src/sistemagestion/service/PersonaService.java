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
        if (pasajero == null) throw new IllegalArgumentException("Pasajero nulo");
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
    }
    public void registrarConductor(Conductor conductor) {
        if (conductor == null) throw new IllegalArgumentException("Conductor nulo");
        if (conductor.getNumeroLicencia() == null || conductor.getNumeroLicencia().isEmpty())
            throw new IllegalArgumentException("Conductor sin licencia");
        conductorDAO.agregarConductor(conductor);
    }
    public Pasajero buscarPasajero(String documento) {
        Pasajero p = pasajeroDAO.buscarPasajero(documento);
        if (p == null) throw new IllegalArgumentException("Pasajero no encontrado: " + documento);
        return p;
    }
    public Conductor buscarConductor(String documento) {
        Conductor c = conductorDAO.buscarConductor(documento);
        if (c == null) throw new IllegalArgumentException("Conductor no encontrado: " + documento);
        return c;
    }
    public List<Pasajero> listarPasajeros() {
        return pasajeroDAO.listarPasajeros();
    }
    public void eliminarPasajero(String documento) {
        pasajeroDAO.eliminarPasajero(documento);
    }
    public void modificarPasajero(Pasajero pasajero) {
        if (pasajero == null) throw new IllegalArgumentException("Pasajero nulo");
        pasajeroDAO.modificarPasajero(pasajero);
    }
    public List<Conductor> listarConductores() {
        return conductorDAO.listarConductores();
    }
    public void eliminarConductor(String documento) {
        conductorDAO.eliminarConductor(documento);
    }
}