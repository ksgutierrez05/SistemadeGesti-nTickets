package sistemagestion.service;

import sistemagestion.dao.PasajeroDAO;
import sistemagestion.dao.ConductorDAO;
import sistemagestion.model.Pasajero;
import sistemagestion.model.Conductor;

public class PersonaService {

    private PasajeroDAO pasajeroDAO;
    private ConductorDAO conductorDAO;

    public PersonaService() {
        pasajeroDAO = new PasajeroDAO();
        conductorDAO = new ConductorDAO();
    }

    // REGISTRAR PASAJERO
    public void registrarPasajero(Pasajero pasajero) {

        if (pasajero == null) {
            System.out.println("El pasajero no puede ser nulo");
            return;
        }

        pasajeroDAO.agregarPasajero(pasajero);
    }

    // REGISTRAR CONDUCTOR
    public void registrarConductor(Conductor conductor) {

        if (conductor == null) {
            System.out.println("El conductor no puede ser nulo");
            return;
        }

       if (conductor.getNumeroLicencia() == null || conductor.getNumeroLicencia().isEmpty()) {
    System.out.println("El conductor debe tener número de licencia");
    return;
}

    if (conductor.getCategoriaLicencia() == null || conductor.getCategoriaLicencia().isEmpty()) {
    System.out.println("El conductor debe tener categoría de licencia");
    return;
    }

    if (conductor.getVencimientoLicencia() == null || conductor.getVencimientoLicencia().isEmpty()) {
    System.out.println("Debe indicar el vencimiento de la licencia");
    return;
    }

        conductorDAO.agregarConductor(conductor);
    }

    public Pasajero buscarPasajero(String documento) {
        return pasajeroDAO.buscarPasajero(documento);
    }

    public Conductor buscarConductor(String documento) {
        return conductorDAO.buscarConductor(documento);
    }

}