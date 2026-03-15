/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import sistemagestion.model.Vehiculo;

/**
 *
 * @author Lenovo
 */
public class VehiculoDAO {
    private File archivos;
    
    public VehiculoDAO () {
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
    
    public void guardarVehiculo(Vehiculo vehiculo){
         try (FileWriter fw = new FileWriter(archivos, true)) {

            fw.write(
                vehiculo.getPlaca() + ";" +
                vehiculo.getRuta().getCodigo() + ";" +
                vehiculo.isDisponible() + ";" +
                vehiculo.getCapacidad() + ";" +
                vehiculo.getTarifaBase() + "\n"
            );
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ListarVehiculos(){
        try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {

        String linea;

        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    
    public String BuscarVehiculo(String placa ){
         try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos[0].equals(placa)) {
                return linea;
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
    }
    
    
    
    public void actualizarVehiculo(Vehiculo vehiculo){
    File temp = new File("temp.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(archivos));
         FileWriter fw = new FileWriter(temp)) {

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (datos[0].equals(vehiculo.getPlaca())) {

                fw.write(
                    vehiculo.getPlaca() + "," +
                    vehiculo.getRuta().getCodigo() + "," +
                    vehiculo.isDisponible() + "," +
                    vehiculo.getCapacidad() + "," +
                    vehiculo.getTarifaBase() + "\n"
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
    public void eliminarVehiculo(String placa){
    File temp = new File("temp.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(archivos));
         FileWriter fw = new FileWriter(temp)) {

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(",");

            if (!datos[0].equals(placa)) {
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
