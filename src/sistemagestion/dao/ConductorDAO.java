package sistemagestion.dao;

import sistemagestion.model.Conductor;
import java.util.ArrayList;

public class ConductorDAO {

    private ArrayList<Conductor> listaConductores;

    public ConductorDAO() {
        listaConductores = new ArrayList<>();
    }

    public void agregarConductor(Conductor conductor) {
        listaConductores.add(conductor);
    }

    public ArrayList<Conductor> listarConductores() {
        return listaConductores;
    }

    public Conductor buscarConductor(String documento) {

        for (Conductor c : listaConductores) {

            if (c.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }

        }

        return null;
    }

    public void eliminarConductor(String documento) {

        Conductor conductor = buscarConductor(documento);

        if (conductor != null) {
            listaConductores.remove(conductor);
        }

    }

}