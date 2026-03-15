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
import sistemagestion.model.Ticket;


public class TicketDAO {

    private final String archivoTickets = "tickets.txt";


    public void agregarTicket(Ticket ticket) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTickets, true))) {

            Pasajero p = ticket.getPasajero();


            String linea = ticket.getCodigo() + ";" +
                           p.getClass().getSimpleName() + ";" +
                           p.getTipoDocumento() + ";" +
                           p.getDocumento() + ";" +
                           p.getNombre() + ";" +
                           p.getApellido() + ";" +
                           p.getTelefono() + ";" +
                           p.getFechaNacimiento() + ";" +
                           ticket.getPrecioBase() + ";" +
                           ticket.getDescuento() + ";" +
                           ticket.getValorFinal() + ";" +
                           ticket.getFechaCompra();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando ticket: " + e.getMessage());
        }
    }


    public List<Ticket> listarTickets() {

        List<Ticket> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoTickets))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(";");

                if (partes.length < 12) continue;

                String codigo          = partes[0];
                String tipoPasajero    = partes[1];
                String tipoDoc         = partes[2];
                String documento       = partes[3];
                String nombre          = partes[4];
                String apellido        = partes[5];
                String telefono        = partes[6];
                String fechaNacimiento = partes[7];
                double precioBase      = Double.parseDouble(partes[8]);
                double descuento       = Double.parseDouble(partes[9]);
                double valorFinal      = Double.parseDouble(partes[10]);
                String fechaCompra     = partes[11];

                Pasajero p;

                switch (tipoPasajero) {

                    case "PasajeroEstudiante":
                        p = new PasajeroEstudiante(tipoDoc, documento, nombre, apellido, telefono, fechaNacimiento);
                        break;

                    case "PasajeroAdultoMayor":
                        p = new PasajeroAdultoMayor(tipoDoc, documento, nombre, apellido, telefono, fechaNacimiento);
                        break;

                    default:
                        p = new PasajeroRegular(tipoDoc, documento, nombre, apellido, telefono, fechaNacimiento);
                        break;
                }

                Ticket t = new Ticket(codigo, p, precioBase, fechaCompra);
                t.setDescuento(descuento);
                t.setValorFinal(valorFinal);
                lista.add(t);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo tickets.txt no encontrado, se creará al guardar.");
        } catch (IOException e) {
            System.out.println("Error leyendo tickets: " + e.getMessage());
        }

        return lista;
    }


    public Ticket buscarTicket(String codigo) {

        List<Ticket> lista = listarTickets();

        for (Ticket t : lista) {

            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return t;
            }

        }

        return null;
    }


    public void eliminarTicket(String codigo) {

        List<Ticket> lista = listarTickets();

        lista.removeIf(t -> t.getCodigo().equalsIgnoreCase(codigo));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTickets, false))) {

            for (Ticket ticket : lista) {

                Pasajero p = ticket.getPasajero();

                String linea = ticket.getCodigo() + ";" +
                               p.getClass().getSimpleName() + ";" +
                               p.getTipoDocumento() + ";" +
                               p.getDocumento() + ";" +
                               p.getNombre() + ";" +
                               p.getApellido() + ";" +
                               p.getTelefono() + ";" +
                               p.getFechaNacimiento() + ";" +
                               ticket.getPrecioBase() + ";" +
                               ticket.getDescuento() + ";" +
                               ticket.getValorFinal() + ";" +
                               ticket.getFechaCompra();

                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error eliminando ticket: " + e.getMessage());
        }
    }


    public List<Ticket> ticketsPorFecha(String fecha) {

        List<Ticket> resultado = new ArrayList<>();

        for (Ticket t : listarTickets()) {

            if (t.getFechaCompra().equals(fecha)) {
                resultado.add(t);
            }

        }

        return resultado;
    }


    public List<Ticket> ticketsPorTipoPasajero(String tipo) {

        List<Ticket> resultado = new ArrayList<>();

        for (Ticket t : listarTickets()) {

            if (t.getPasajero().getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                resultado.add(t);
            }

        }

        return resultado;
    }


    public void resumenDia(String fecha) {

        int totalTickets = 0;
        double totalRecaudado = 0;

        for (Ticket t : listarTickets()) {

            if (t.getFechaCompra().equals(fecha)) {

                totalTickets++;

                totalRecaudado += t.getValorFinal();
            }

        }

        System.out.println("Tickets vendidos: " + totalTickets);
        System.out.println("Total recaudado: $" + totalRecaudado);
    }

    public List<Ticket> ticketsPorPasajeroYFecha(Pasajero pasajero, String fechaCompra) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}