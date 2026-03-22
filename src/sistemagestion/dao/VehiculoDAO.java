/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
 */
public class VehiculoDAO {

    private File archivos;

    private RutaDAO rutaDAO = new RutaDAO();
    private ConductorDAO conductorDAO = new ConductorDAO();

    public VehiculoDAO() {
        archivos = new File("vehiculo.txt");

        try {
            if (!archivos.exists()) {
                archivos.createNewFile();
                System.out.println("Archivo creado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarVehiculo(Vehiculo vehiculo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivos, true))) {
            String linea = vehiculo.getClass().getSimpleName() + ";"
                    + vehiculo.getPlaca() + ";"
                    + (vehiculo.getRuta() != null ? vehiculo.getRuta().getCodigo() : "null") + ";"
                    + vehiculo.isDisponible() + ";"
                    + vehiculo.getCapacidad() + ";"
                    + vehiculo.getTarifaBase() + ";"
                    + (vehiculo.getConductor() != null ? vehiculo.getConductor().getDocumento() : "null");
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                String tipo = datos[0];
                String placa = datos[1];
                String codigoRuta = datos[2];
                boolean disponible = Boolean.parseBoolean(datos[3]);
                int capacidad = Integer.parseInt(datos[4]);
                double tarifa = Double.parseDouble(datos[5]);
                String documentoConductor = datos[6];
                Conductor conductor = conductorDAO.obtenerConductores()
                        .stream()
                        .filter(c -> c.getDocumento().equalsIgnoreCase(documentoConductor))
                        .findFirst()
                        .orElse(null);

                Ruta ruta = rutaDAO.obtenerRutas()
                        .stream()
                        .filter(r -> r.getCodigo().equalsIgnoreCase(codigoRuta))
                        .findFirst()
                        .orElse(null);

                Vehiculo v;
                if (tipo.equalsIgnoreCase("Bus")) {
                    v = new Bus(conductor, ruta, placa, disponible, capacidad, (float) tarifa);
                } else if (tipo.equalsIgnoreCase("Buseta")) {
                    v = new Buseta(conductor, ruta, placa, disponible, capacidad, (float) tarifa);
                } else {
                    v = new Microbus(conductor, ruta, placa, disponible, capacidad, (float) tarifa);
                }

                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void listarVehiculos() {
        List<Vehiculo> lista = obtenerVehiculos();

        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        for (Vehiculo v : lista) {
            System.out.println("---------------");
            System.out.println("Tipo: " + v.getClass().getSimpleName());
            System.out.println("Placa: " + v.getPlaca());
            System.out.println("Disponible: " + v.isDisponible());
            System.out.println("Capacidad: " + v.getCapacidad());
            System.out.println("Tarifa: " + v.getTarifaBase());

            if (v.getRuta() != null) {
                System.out.println("Ruta:");
                System.out.println("  Codigo: " + v.getRuta().getCodigo());
                System.out.println("  Origen: " + v.getRuta().getOrigen());
                System.out.println("  Destino: " + v.getRuta().getDestino());
                System.out.println("  Distancia: " + v.getRuta().getDistancia());
                System.out.println("  Tiempo: " + v.getRuta().getTiempo());
            } else {
                System.out.println("Sin ruta asignada");
            }
        }
    }

    public String buscarVehiculo(String placa) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[1].equals(placa)) {
                    return linea;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivos)); FileWriter fw = new FileWriter(temp)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[1].equals(vehiculo.getPlaca())) {

                    fw.write(
                            vehiculo.getClass().getSimpleName() + ";"
                            + vehiculo.getPlaca() + ";"
                            + vehiculo.getRuta().getCodigo() + ";"
                            + vehiculo.isDisponible() + ";"
                            + vehiculo.getCapacidad() + ";"
                            + vehiculo.getTarifaBase() + "\n"
                    );

                } else {

                    fw.write(linea + "\n");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        archivos.delete();
        temp.renameTo(archivos);
    }

    public void eliminarVehiculo(String placa) {
        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivos)); FileWriter fw = new FileWriter(temp)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (!datos[1].equals(placa)) {
                    fw.write(linea + "\n");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        archivos.delete();
        temp.renameTo(archivos);
    }

}
