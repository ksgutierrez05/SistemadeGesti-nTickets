/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.service;

import java.util.List;
import sistemagestion.dao.VehiculoDAO;
import sistemagestion.model.Ruta;
import sistemagestion.model.Vehiculo;

/**
 *
 * @author Maria Cristina
 */
public class VehiculoService {

    private VehiculoDAO vehiculoDAO;

    
    public VehiculoService() {
        this.vehiculoDAO = new VehiculoDAO();
    }


    public VehiculoService(VehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
    }

    //Valida si la placa ingresada ya existe 
    public boolean validarplaca(String placa) {
        String resultado;
        resultado = vehiculoDAO.buscarVehiculo(placa);
        if (resultado == null) {
            return true;
        }
        return false;
    }

    //Registrar lo vehiculos 
    public void registrarVehiculo(Vehiculo vehiculo) {
        if (validarplaca(vehiculo.getPlaca())) {
            vehiculoDAO.guardarVehiculo(vehiculo);
            System.out.println("Vehiculo registrado correctamente");

        } else {
            System.out.println("La placa ya existe");
        }

    }
    public List<Ruta> obtenerRutasDisponibles(RutaService rutaService) {
    List<Ruta> rutas = rutaService.listarRutas();
    
    if (rutas.isEmpty()) {
        System.out.println("No hay rutas registradas.");
    }

    return rutas; 
}
     public Vehiculo obtenerVehiculoPorPlaca(String placa) {
    List<Vehiculo> vehiculos = vehiculoDAO.obtenerVehiculos();
    for (Vehiculo v : vehiculos) {
        if (v.getPlaca().equalsIgnoreCase(placa)) {
            return v;
        }
    }
    return null; 
}
    
   public void listarVehiculos(){
    if (vehiculoDAO != null) {
        vehiculoDAO.listarVehiculos();
    } else {
        System.out.println("Error: DAO no inicializado");
    }
}
    public String buscarVehiculo(String placa) {

        String resultado = vehiculoDAO.buscarVehiculo(placa);

        if (resultado == null) {
            System.out.println("El vehiculo no existe");
        } else {
            System.out.println("Vehiculo encontrado: " 
                    + resultado);
        }

        return resultado;
    }

    // ACtualizarvehiculos
    public void actualizarVehiculo(Vehiculo vehiculo) {

        if (!validarplaca(vehiculo.getPlaca())) {
            vehiculoDAO.actualizarVehiculo(vehiculo);
            System.out.println("Vehiculo modificado");
        } else {
            System.out.println("El vehiculo no existe");
        }

    }

    //Eliminar
    public void eliminarVehiculo(String placa) {

        if (!validarplaca(placa)) {
            vehiculoDAO.eliminarVehiculo(placa);
            System.out.println("Vehiculo eliminado");
        } else {
            System.out.println("El vehiculo no existe");
        }

    }

}
