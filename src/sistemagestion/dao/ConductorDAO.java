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

public class ConductorDAO {

    private final String archivoConductores = "conductores.txt";

    // Guardar un conductor
    public void agregarConductor(Conductor conductor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoConductores, true))) {
            // Guardamos: tipoDocumento;documento;nombre;apellido;telefono;numeroLicencia;categoriaLicencia;vencimientoLicencia
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

    // Leer todos los conductores
    public List<Conductor> listarConductores() {
        List<Conductor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoConductores))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 8) continue;

                String tipoDoc = partes[0];
                String doc = partes[1];
                String nombre = partes[2];
                String apellido = partes[3];
                String telefono = partes[4];
                String numeroLicencia = partes[5];
                String categoriaLicencia = partes[6];
                String vencimientoLicencia = partes[7];

                Conductor c = new Conductor(tipoDoc, doc, nombre, apellido, telefono,
                                            numeroLicencia, categoriaLicencia, vencimientoLicencia);
                lista.add(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo conductores.txt no encontrado, se creará al guardar.");
        } catch (IOException e) {
            System.out.println("Error leyendo conductores: " + e.getMessage());
        }
        return lista;
    }

    // Buscar conductor por documento
    public Conductor buscarConductor(String documento) {
        List<Conductor> lista = listarConductores();
        for (Conductor c : lista) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }
}