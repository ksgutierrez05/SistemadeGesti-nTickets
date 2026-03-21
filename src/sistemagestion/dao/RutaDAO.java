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
import sistemagestion.model.Ruta;

/**
 *
 * @author Maria Cristina
 */
public class RutaDAO {

    private File archivos;

    public RutaDAO() {

        archivos = new File("rutas.txt");

        try {
            if (!archivos.exists()) {
                archivos.createNewFile();
                System.out.println("Archivo de rutas creado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //GuardarRutas

    public void guardarRuta(Ruta ruta) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivos, true))) {

            bw.write(
                    ruta.getCodigo() + ";"
                    + ruta.getOrigen() + ";"
                    + ruta.getDestino() + ";"
                    + ruta.getDistancia() + ";"
                    + ruta.getTiempo()
            );

            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//ListarRutas

   
    
   public List<Ruta> obtenerRutas() {
    List<Ruta> lista = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");

            // Validar que tenga todos los datos
            if (datos.length == 5) {
                try {
                    Ruta ruta = new Ruta(
                            datos[0],
                            datos[1],
                            datos[2],
                            Double.parseDouble(datos[3].replace(",", ".")),
                            Integer.parseInt(datos[4])
                    );

                    lista.add(ruta);

                } catch (NumberFormatException e) {
                    System.out.println("Error en números: " + linea);
                }
            } else {
                System.out.println("Línea incompleta: " + linea);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return lista;
}
    //BuscarRutas

    public String buscarRuta(String codigo) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[0].equals(codigo)) {
                    return linea;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualizarRuta(Ruta ruta) {

        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivos)); FileWriter fw = new FileWriter(temp)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[0].equals(ruta.getCodigo())) {

                    fw.write(
                            ruta.getCodigo() + ";"
                            + ruta.getOrigen() + ";"
                            + ruta.getDestino() + ";"
                            + ruta.getDistancia() + ";"
                            + ruta.getTiempo() + "\n"
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

    public void eliminarRuta(String codigo) {

        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivos)); FileWriter fw = new FileWriter(temp)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (!datos[0].equals(codigo)) {
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
