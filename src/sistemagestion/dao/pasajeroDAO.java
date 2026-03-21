package sistemagestion.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroAdultoMayor;
import sistemagestion.model.PasajeroEstudiante;
import sistemagestion.model.PasajeroRegular;
public class pasajeroDAO {

    private final String archivoPasajeros = "pasajeros.txt";

    public void agregarPasajero(Pasajero pasajero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPasajeros, true))) {
            String linea = pasajero.getClass().getSimpleName() + ";"
                    + pasajero.getTipoDocumento() + ";"
                    + pasajero.getDocumento() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getApellido() + ";"
                    + pasajero.getTelefono() + ";"
                    + pasajero.getFechaNacimiento();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> listarPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPasajeros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 7) {
                    continue;
                }
                String tipo = partes[0].trim(); 
                String tipoDoc = partes[1].trim();
                String doc = partes[2].trim();
                String nombre = partes[3].trim();
                String apellido = partes[4].trim();
                String telefono = partes[5].trim();
                String fechaNacimiento = partes[6].trim();
                Pasajero p;
                switch (tipo) {
                    case "PasajeroEstudiante":
                        p = new PasajeroEstudiante(tipoDoc, doc, nombre, apellido, telefono, fechaNacimiento);
                        break;
                    case "PasajeroAdultoMayor":
                        p = new PasajeroAdultoMayor(tipoDoc, doc, nombre, apellido, telefono, fechaNacimiento);
                        break;
                    default:
                        p = new PasajeroRegular(tipoDoc, doc, nombre, apellido, telefono, fechaNacimiento);
                        break;
                }
                lista.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo pasajeros.txt no encontrado, se creará al guardar.");
        } catch (IOException e) {
            System.out.println("Error leyendo pasajeros: " + e.getMessage());
        }
        return lista;
    }

    public Pasajero buscarPasajero(String documento) {
        List<Pasajero> lista = listarPasajeros();
        for (Pasajero p : lista) {
            if (p.getDocumento().trim().equals(documento.trim())) {
                return p;
            }
        }
        return null;
    }

    public boolean modificarPasajero(Pasajero pasajeroActualizado) {
        List<Pasajero> lista = listarPasajeros();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getDocumento().trim().equals(pasajeroActualizado.getDocumento().trim())) { 
                lista.set(i, pasajeroActualizado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPasajeros, false))) {
            for (Pasajero p : lista) {
                String linea = p.getClass().getSimpleName() + ";"
                        + p.getTipoDocumento() + ";"
                        + p.getDocumento() + ";"
                        + p.getNombre() + ";"
                        + p.getApellido() + ";"
                        + p.getTelefono() + ";"
                        + p.getFechaNacimiento();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error modificando pasajero: " + e.getMessage());
        }
        return true;
    }

    public void eliminarPasajero(String documento) {
        List<Pasajero> lista = listarPasajeros();
        lista.removeIf(p -> p.getDocumento().trim().equals(documento.trim()));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPasajeros, false))) {
            for (Pasajero p : lista) {
                String linea = p.getClass().getSimpleName() + ";"
                        + p.getTipoDocumento() + ";"
                        + p.getDocumento() + ";"
                        + p.getNombre() + ";"
                        + p.getApellido() + ";"
                        + p.getTelefono() + ";"
                        + p.getFechaNacimiento();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error eliminando pasajero: " + e.getMessage());
        }
    }

    public boolean existePasajero(String documento) {
        return buscarPasajero(documento) != null;
    }
}