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
import sistemagestion.model.EstadoReserva;
import sistemagestion.model.Reserva;

/**
 *
 * @author Maria Cristina
 */
public class ReservaDAO {

    private File Archivos;

    public ReservaDAO() {
        Archivos = new File("Reservas.txt");
        try {
            if (!Archivos.exists()) {
                Archivos.createNewFile();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarReserva(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Archivos, true))) {

            bw.write(
                    reserva.getCodigo() + ";"
                    + reserva.getDocumentoPasajero() + ";"
                    + reserva.getPlacaVehiculo() + ";"
                    + reserva.getFechaCreacion() + ";"
                    + reserva.getFechaViaje() + ";"
                    + reserva.getEstado().name()
            );

            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> listarReservas() {
        List<Reserva> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Archivos))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 6) {
                    Reserva r = new Reserva(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            EstadoReserva.valueOf(datos[5].toUpperCase())
                    );

                    lista.add(r);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public String buscarReserva(String codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(Archivos))) {

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

    public void actualizarReserva(Reserva reserva) {
        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(Archivos)); FileWriter fw = new FileWriter(temp)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[0].equals(reserva.getCodigo())) {

                    fw.write(
                            reserva.getCodigo() + ";"
                            + reserva.getDocumentoPasajero() + ";"
                            + reserva.getPlacaVehiculo() + ";"
                            + reserva.getFechaCreacion() + ";"
                            + reserva.getFechaViaje() + ";"
                            + reserva.getEstado().name() + "\n"
                    );

                } else {
                    fw.write(linea + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Archivos.delete();
        temp.renameTo(Archivos);
    }

    public void eliminarReserva(String codigo) {
        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(Archivos)); FileWriter fw = new FileWriter(temp)) {

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

        Archivos.delete();
        temp.renameTo(Archivos);
    }

    public Reserva buscarReservaPorCodigo(String codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(Archivos))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 6 && datos[0].equalsIgnoreCase(codigo)) {
                    return new Reserva(
                        datos[0],
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
                        EstadoReserva.valueOf(datos[5].toUpperCase())
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean existeReservaActivaPasajeroVehiculoFecha(String documentoPasajero, String placaVehiculo, String fechaViaje) {
        for (Reserva r : listarReservas()) {
            if (r.getDocumentoPasajero().equalsIgnoreCase(documentoPasajero)
                    && r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)
                    && r.getEstado() == EstadoReserva.ACTIVA) {
                return true;
            }
        }
        return false;
    }
    public int contarReservasActivasPorVehiculoYFecha(String placaVehiculo, String fechaViaje) {
        int contador = 0;

        for (Reserva r : listarReservas()) {
            if (r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)
                    && r.getEstado() == EstadoReserva.ACTIVA) {
                contador++;
            }
        }

        return contador;
    }




}
