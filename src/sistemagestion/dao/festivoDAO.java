
package sistemagestion.dao;
import java.io.*;
import java.util.ArrayList;

public class festivoDAO {

    private ArrayList<String> listaFestivos = new ArrayList<>();

    public festivoDAO() {
        cargarFestivos();
    }

    private void cargarFestivos() {
        try (BufferedReader br = new BufferedReader(new FileReader("festivos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    listaFestivos.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo festivos: " + e.getMessage());
        }
    }

    // Validar si la fecha de compra está en la lista
    public boolean esFestivo(String fechaCompra) {
        // fechaCompra debe estar en formato DD-MM-AAAA
        return listaFestivos.contains(fechaCompra);
    }

    public ArrayList<String> getListaFestivos() {
        return listaFestivos;
    }
}