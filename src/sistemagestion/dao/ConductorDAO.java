package sistemagestion.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import sistemagestion.model.Conductor;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO { //CORECCCION CON CONDUCTOR DAO

    private final String archivoConductores = "conductores.txt";

    public void agregarConductor(Conductor conductor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConductores, true))) {
            String linea = conductor.getTipoDocumento() + ";" +
                           conductor.getDocumento() + ";" +
                           conductor.getNombre() + ";" +
                           conductor.getApellido() + ";" +
                           conductor.getTelefono() + ";" +
                           conductor.getNumeroLicencia() + ";" +
                           conductor.getCategoriaLicencia() + ";" +
                           conductor.getVencimientoLicencia();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando conductor: " + e.getMessage());
        }
    }

    public List<Conductor> listarConductores() {
        List<Conductor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoConductores))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 8) continue;

                String tipoDoc = partes[0].trim();
                String doc = partes[1].trim();
                String nombre = partes[2].trim();
                String apellido = partes[3].trim();
                String telefono = partes[4].trim();
                String numeroLicencia = partes[5].trim();
                String categoriaLicencia = partes[6].trim();
                String vencimientoLicencia = partes[7].trim();

                Conductor c = new Conductor(
                        tipoDoc, doc, nombre, apellido, telefono,
                        numeroLicencia, categoriaLicencia, vencimientoLicencia
                );
                lista.add(c);
            }
        } catch (FileNotFoundException e) {
    
        } catch (IOException e) {
            System.out.println("Error leyendo conductores: " + e.getMessage());
        }
        return lista;
    }

    public Conductor buscarConductor(String documento) {
        List<Conductor> lista = listarConductores();
        for (Conductor c : lista) {
            if (c.getDocumento().trim().equals(documento.trim())) {
                return c;
            }
        }
        return null;
    }
    public List<Conductor> obtenerConductores() {
    List<Conductor> lista = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {
        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split(";");

            if (datos.length < 3) continue; // evita errores

            String documento = datos[0];
            String nombre = datos[1];
            String telefono = datos[2];

            Conductor c = new Conductor(documento, nombre, telefono);
            lista.add(c);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return lista;
}

    public Conductor buscarPorLicencia(String numeroLicencia) {
        List<Conductor> lista = listarConductores();
        for (Conductor c : lista) {
            if (c.getNumeroLicencia().trim().equalsIgnoreCase(numeroLicencia.trim())) {
                return c;
            }
        }
        return null;
    }

    public void eliminarConductor(String documento) {
        List<Conductor> lista = listarConductores();
        lista.removeIf(c -> c.getDocumento().trim().equals(documento.trim()));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConductores, false))) {
            for (Conductor c : lista) {
                String linea = c.getTipoDocumento() + ";" +
                               c.getDocumento() + ";" +
                               c.getNombre() + ";" +
                               c.getApellido() + ";" +
                               c.getTelefono() + ";" +
                               c.getNumeroLicencia() + ";" +
                               c.getCategoriaLicencia() + ";" +
                               c.getVencimientoLicencia();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error eliminando conductor: " + e.getMessage());
        }
    }

    public Conductor existeConductor(String documento) {
        return buscarConductor(documento);
    }

    public Conductor existeLicencia(String numeroLicencia) {
        return buscarPorLicencia(numeroLicencia);
    }

    public void modificarConductor(Conductor conductor) {
        List<Conductor> lista = listarConductores();
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getDocumento().trim().equals(conductor.getDocumento().trim())) {
                lista.set(i, conductor);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró el conductor para modificar.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConductores, false))) {
            for (Conductor c : lista) {
                String linea = c.getTipoDocumento() + ";" +
                               c.getDocumento() + ";" +
                               c.getNombre() + ";" +
                               c.getApellido() + ";" +
                               c.getTelefono() + ";" +
                               c.getNumeroLicencia() + ";" +
                               c.getCategoriaLicencia() + ";" +
                               c.getVencimientoLicencia();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error modificando conductor: " + e.getMessage());
        }
    }
}