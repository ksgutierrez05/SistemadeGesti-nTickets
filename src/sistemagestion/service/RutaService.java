/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.service;

import java.util.List;
import sistemagestion.dao.RutaDAO;
import sistemagestion.model.Ruta;

/**
 *
 * @author Maria Cristina
 */
public class RutaService {

    private RutaDAO rutaDAO;

    public RutaService() {
        this.rutaDAO = new RutaDAO();
    }

    public RutaService(RutaDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
    }

    //ValidarRuta
    public boolean validarRuta(String codigo) {
        String resultado;
        resultado = rutaDAO.buscarRuta(codigo);

        if (resultado == null) {
            return true;

        }
        return false;
    }

    public Ruta obtenerRutaPorCodigo(String codigo) {
        Ruta ruta = rutaDAO.obtenerRutaPorCodigo(codigo);

        if (ruta == null) {
            throw new IllegalArgumentException("No existe una ruta con código: " + codigo);
        }

        return ruta;
    }

    //RegistraRuta
    public void registrarruta(Ruta ruta) {
        if (validarRuta(ruta.getCodigo())) {
            rutaDAO.guardarRuta(ruta);
            System.out.println("Ruta registrada correctamente");
        } else {
            System.out.println("La ruta ya existe");
        }

    }

    public Ruta obtenerPrimeraRuta() {
        List<Ruta> rutas = rutaDAO.obtenerRutas();

        if (rutas.isEmpty()) {
            return null;
        }

        return rutas.get(0);
    }

    public List<Ruta> listarRutas() {
        return rutaDAO.obtenerRutas();
    }

    public String buscarRuta(String codigo) {

        String resultado = rutaDAO.buscarRuta(codigo);

        if (resultado == null) {
            System.out.println("La ruta no existe");
        } else {
            System.out.println("Ruta encontrada: " + resultado);
        }

        return resultado;
    }
    //actualizarRuta

    public void actualizarRuta(Ruta ruta) {

        if (!validarRuta(ruta.getCodigo())) {
            rutaDAO.actualizarRuta(ruta);
            System.out.println("Ruta modificada");
        } else {
            System.out.println("La ruta no existe");
        }

    }
    //eliminarRuta

    public void eliminarRuta(String codigo) {

        if (!validarRuta(codigo)) {
            rutaDAO.eliminarRuta(codigo);
            System.out.println("Ruta eliminada");
        } else {
            System.out.println("La ruta no existe");
        }

    }

}
