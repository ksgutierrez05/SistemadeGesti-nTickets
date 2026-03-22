/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.service;

import java.util.ArrayList;
import java.util.List;
import sistemagestion.dao.VehiculoDAO;
import sistemagestion.model.Conductor;
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

    
    public boolean validarPlaca(String placa) {
        Vehiculo v = vehiculoDAO.buscarVehiculo(placa);
        return v == null; 
    }

    
    public List<Ruta> obtenerRutasDisponibles(List<Ruta> rutas) {
        List<Vehiculo> vehiculos = vehiculoDAO.obtenerVehiculos();
        List<Ruta> disponibles = new ArrayList<>();

        for (Ruta ruta : rutas) {
            boolean usada = false;

            for (Vehiculo v : vehiculos) {
                if (v.getRuta().getCodigo().equalsIgnoreCase(ruta.getCodigo())) {
                    usada = true;
                    break;
                }
            }

            if (!usada) {
                disponibles.add(ruta);
            }
        }

        return disponibles;
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
 public boolean estaDisponible(String placa) {
    Vehiculo v = vehiculoDAO.buscarVehiculo(placa);

    if (v == null) {
        System.out.println("Vehiculo no existe");
        return false;
    }

    return v.isDisponible();
}
    
    public void registrarVehiculo(Vehiculo vehiculo) {
        if (validarPlaca(vehiculo.getPlaca())) {
            vehiculoDAO.guardarVehiculo(vehiculo);
            System.out.println("Vehiculo registrado correctamente");
        } else {
            System.out.println("La placa ya existe");
        }
    }

   
   public void listarVehiculos() {
    List<Vehiculo> lista = vehiculoDAO.obtenerVehiculos();

    if (lista.isEmpty()) {
        System.out.println("No hay vehículos registrados.");
        return;
    }

    System.out.println("\n===== LISTADO DE VEHICULOS =====");
    for (Vehiculo v : lista) {
        System.out.println("-------------------------------");
        System.out.println("Tipo de vehículo: " + v.getClass().getSimpleName());
        System.out.println("Placa: " + v.getPlaca());
        System.out.println("Disponible: " + (v.isDisponible() ? "Sí" : "No"));
        System.out.println("Capacidad: " + v.getCapacidad());
        System.out.println("Tarifa: " + v.getTarifaBase());

        // Mostrar ruta
        if (v.getRuta() != null) {
            System.out.println("---- Ruta ----");
            System.out.println("Código: " + v.getRuta().getCodigo());
            System.out.println("Origen: " + v.getRuta().getOrigen());
            System.out.println("Destino: " + v.getRuta().getDestino());
            System.out.println("Distancia: " + v.getRuta().getDistancia());
            System.out.println("Tiempo estimado: " + v.getRuta().getTiempo());
        } else {
            System.out.println("Ruta: Sin asignar");
        }

        
        if (v.getConductor() != null) {
            Conductor c = v.getConductor();
            System.out.println("---- Conductor ----");
            System.out.println("Documento: " + c.getDocumento());
            System.out.println("Nombre: " + c.getNombre() + " " + c.getApellido());
            System.out.println("Teléfono: " + c.getTelefono());
            System.out.println("Número Licencia: " + c.getNumeroLicencia());
            System.out.println("Categoría Licencia: " + c.getCategoriaLicencia());
            System.out.println("Vencimiento Licencia: " + c.getVencimientoLicencia());
        } else {
            System.out.println("Conductor: Sin asignar");
        }

        System.out.println("-------------------------------\n");
    }
}

    
  public Vehiculo buscarVehiculo(String placa) {

        Vehiculo resultado = vehiculoDAO.buscarVehiculo(placa);

        if (resultado == null) {
            System.out.println("El vehiculo no existe");
        } else {
            System.out.println("Vehiculo encontrado: " 
                    + resultado);
        }

        return resultado;
    }
    
    public void eliminarVehiculo(String placa) {
        Vehiculo v = vehiculoDAO.buscarVehiculo(placa);

        if (v == null) {
            System.out.println("El vehiculo no existe");
        } else {
            vehiculoDAO.eliminarVehiculo(placa);
            System.out.println("Vehiculo eliminado correctamente");
        }
    }

    
    public void actualizarVehiculo(Vehiculo vehiculo) {
        Vehiculo existente = vehiculoDAO.buscarVehiculo(vehiculo.getPlaca());

        if (existente == null) {
            System.out.println("El vehiculo no existe");
        } else {
            vehiculoDAO.eliminarVehiculo(vehiculo.getPlaca());
            vehiculoDAO.guardarVehiculo(vehiculo);
            System.out.println("Vehiculo actualizado correctamente");
        }
    }
}