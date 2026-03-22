/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagestion.model.Bus;
import sistemagestion.model.Buseta;
import sistemagestion.model.Conductor;
import sistemagestion.model.Microbus;
import sistemagestion.model.Ruta;
import sistemagestion.model.Vehiculo;

/**
 *
 * @author Lenovo
 */public class VehiculoDAO {

    private final String archivoVehiculos = "vehiculos.txt";
    private RutaDAO rutaDAO = new RutaDAO();
    private ConductorDAO conductorDAO = new ConductorDAO();

    
    public void guardarVehiculo(Vehiculo v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoVehiculos, true))) {

            String documentoConductor = (v.getConductor() != null)
                    ? v.getConductor().getDocumento()
                    : "null";

            String linea = v.getClass().getSimpleName() + ";"
                    + v.getPlaca() + ";"
                    + v.getRuta().getCodigo() + ";"
                    + v.isDisponible() + ";"
                    + v.getCapacidad() + ";"
                    + v.getTarifaBase() + ";"
                    + documentoConductor;

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando vehículo: " + e.getMessage());
        }
    }

    
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoVehiculos))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                // 🔴 VALIDACIÓN CLAVE
                if (datos.length < 6) {
                    System.out.println("Línea inválida: " + linea);
                    continue;
                }

                String tipo = datos[0];
                String placa = datos[1];
                String codigoRuta = datos[2];
                boolean disponible = Boolean.parseBoolean(datos[3]);
                int capacidad = Integer.parseInt(datos[4]);
                float tarifa = Float.parseFloat(datos[5]);

                // 🟡 Conductor opcional
                Conductor conductor = null;
                if (datos.length > 6 && !datos[6].equalsIgnoreCase("null")) {
                    String documentoConductor = datos[6];

                    conductor = conductorDAO.listarConductores()
                            .stream()
                            .filter(c -> c.getDocumento().equalsIgnoreCase(documentoConductor))
                            .findFirst()
                            .orElse(null);
                }

                // 🔵 Buscar ruta
                Ruta ruta = rutaDAO.obtenerRutas()
                        .stream()
                        .filter(r -> r.getCodigo().equalsIgnoreCase(codigoRuta))
                        .findFirst()
                        .orElse(null);

                if (ruta == null) {
                    System.out.println("Ruta no encontrada para vehículo: " + placa);
                    continue;
                }

                // 🔥 Crear vehículo
                Vehiculo v;

                if (tipo.equalsIgnoreCase("Bus")) {
                    v = new Bus(conductor, ruta, placa, disponible, capacidad, tarifa);
                } else if (tipo.equalsIgnoreCase("Buseta")) {
                    v = new Buseta(conductor, ruta, placa, disponible, capacidad, tarifa);
                } else if (tipo.equalsIgnoreCase("Microbus")) {
                    v = new Microbus(conductor, ruta, placa, disponible, capacidad, tarifa);
                } else {
                    System.out.println("Tipo desconocido: " + tipo);
                    continue;
                }

                lista.add(v);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo vehiculos.txt no existe aún.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : obtenerVehiculos()) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

   
    public void eliminarVehiculo(String placa) {
        List<Vehiculo> lista = obtenerVehiculos();

        lista.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoVehiculos, false))) {

            for (Vehiculo v : lista) {

                String documentoConductor = (v.getConductor() != null)
                        ? v.getConductor().getDocumento()
                        : "null";

                String linea = v.getClass().getSimpleName() + ";"
                        + v.getPlaca() + ";"
                        + v.getRuta().getCodigo() + ";"
                        + v.isDisponible() + ";"
                        + v.getCapacidad() + ";"
                        + v.getTarifaBase() + ";"
                        + documentoConductor;

                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error eliminando vehículo: " + e.getMessage());
        }
    }
}