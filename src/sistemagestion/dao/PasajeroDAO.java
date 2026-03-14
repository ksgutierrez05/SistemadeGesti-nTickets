package sistemagestion.dao;

import sistemagestion.model.Pasajero;
import java.util.ArrayList;

public class PasajeroDAO {

    private ArrayList<Pasajero> listaPasajeros;

    public PasajeroDAO() {
        listaPasajeros = new ArrayList<>();
    }

    public void agregarPasajero(Pasajero pasajero) {
        listaPasajeros.add(pasajero);
    }

    public ArrayList<Pasajero> listarPasajeros() {
        return listaPasajeros;
    }

    public Pasajero buscarPasajero(String documento) {

        for (Pasajero p : listaPasajeros) {

            if (p.getDocumento().equalsIgnoreCase(documento)) {
                return p;
            }

        }

        return null;
    }

    public void eliminarPasajero(String documento) {

        Pasajero pasajero = buscarPasajero(documento);

        if (pasajero != null) {
            listaPasajeros.remove(pasajero);
        }

    }

}