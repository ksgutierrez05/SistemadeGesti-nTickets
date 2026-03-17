package sistemagestion.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagestion.model.Bus;
import sistemagestion.model.Buseta;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroAdultoMayor;
import sistemagestion.model.PasajeroEstudiante;
import sistemagestion.model.PasajeroRegular;
import sistemagestion.model.Ruta;
import sistemagestion.model.Ticket;
import sistemagestion.model.Vehiculo;


public class TicketDAO {

    private final String archivoTickets = "tickets.txt";

    public void agregarTicket(Ticket ticket) {
        if (ticket.getVehiculo() == null) {
            System.out.println("No se puede guardar ticket: Vehículo no válido.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTickets, true))) {

            Pasajero p = ticket.getPasajero();
            Vehiculo v = ticket.getVehiculo();

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
                           ticket.getFechaCompra() + ";" +
                           v.getClass().getSimpleName() + ";" +
                           v.getPlaca() + ";" +
                           v.getRuta().getCodigo() + ";" +
                           v.getRuta().getOrigen() + ";" +
                           v.getRuta().getDestino();

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
                if (partes.length < 18) continue; 

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

                String tipoVehiculo = partes[12];
                String placa        = partes[13];
                String codigoRuta   = partes[14];
                String origen       = partes[15];
                String destino      = partes[16];
                double distancia    = 0; 
                Ruta ruta = new Ruta(codigoRuta, origen, destino, distancia);

                Vehiculo vehiculo = null;
                if (tipoVehiculo.equalsIgnoreCase("Bus")) {
                    vehiculo = new Bus(ruta, placa, true, 45, 15000f);
                } else if (tipoVehiculo.equalsIgnoreCase("Buseta")) {
                    vehiculo = new Buseta(ruta, placa, true, 19, 8000f);
                } else if (tipoVehiculo.equalsIgnoreCase("MicroBus")) {
                    vehiculo = new MicroBus(ruta, placa, true, 25, 10000f);
                } else {
                    System.out.println("Tipo de vehículo desconocido: " + tipoVehiculo + " — Ticket ignorado.");
                    continue;
                }

                Ticket t = new Ticket(codigo, p, vehiculo, fechaCompra);
                t.setPrecioBase(precioBase);
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
        for (Ticket t : listarTickets()) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) return t;
        }
        return null;
    }

    public void eliminarTicket(String codigo) {
        List<Ticket> lista = listarTickets();
        lista.removeIf(t -> t.getCodigo().equalsIgnoreCase(codigo));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTickets, false))) {
            for (Ticket ticket : lista) {
                Pasajero p = ticket.getPasajero();
                Vehiculo v = ticket.getVehiculo();
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
                               ticket.getFechaCompra() + ";" +
                               v.getClass().getSimpleName() + ";" +
                               v.getPlaca() + ";" +
                               v.getRuta().getCodigo() + ";" +
                               v.getRuta().getOrigen() + ";" +
                               v.getRuta().getDestino();
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
            if (t.getFechaCompra().equals(fecha)) resultado.add(t);
        }
        return resultado;
    }

    public List<Ticket> ticketsPorTipoPasajero(String tipo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : listarTickets()) {
            if (t.getPasajero().getClass().getSimpleName().equalsIgnoreCase(tipo)) resultado.add(t);
        }
        return resultado;
    }

    public List<Ticket> ticketsPorTipoVehiculo(String tipoVehiculo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : listarTickets()) {
            if (t.getVehiculo() != null &&
                t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipoVehiculo)) {
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
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : listarTickets()) {
            if (t.getPasajero().getDocumento().equalsIgnoreCase(pasajero.getDocumento()) &&
                t.getFechaCompra().equals(fechaCompra)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}