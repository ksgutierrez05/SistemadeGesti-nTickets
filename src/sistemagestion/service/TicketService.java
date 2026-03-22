package sistemagestion.service;

import java.util.List;
import sistemagestion.dao.TicketDAO;
import sistemagestion.dao.festivoDAO;
import sistemagestion.model.Pasajero;
import sistemagestion.model.Ticket;

public class TicketService {

    private TicketDAO ticketDAO;
    private festivoDAO festivoDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAO();
        this.festivoDAO = new festivoDAO();
    }

    public void venderTicket(Ticket ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket inválido");
        }

        if (ticket.getCodigo() == null || ticket.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El ticket debe tener un código");
        }

        if (ticket.getPasajero() == null) {
            throw new IllegalArgumentException("El ticket no tiene pasajero");
        }

        if (ticket.getVehiculo() == null) {
            throw new IllegalArgumentException("El ticket no tiene vehículo válido");
        }

        if (ticket.getFechaCompra() == null || ticket.getFechaCompra().trim().isEmpty()) {
            throw new IllegalArgumentException("Fecha de compra inválida");
        }

        if (ticketDAO.buscarTicket(ticket.getCodigo().trim()) != null) {
            throw new IllegalArgumentException("Ya existe un ticket con ese código");
        }

        List<Ticket> ticketsHoy =
                ticketDAO.ticketsPorPasajeroYFecha(ticket.getPasajero(), ticket.getFechaCompra().trim());

        if (ticketsHoy.size() >= 3) {
            throw new IllegalArgumentException(
                    "El pasajero ya tiene " + ticketsHoy.size() + " tickets para esta fecha.");
        }

        ticket.setCodigo(ticket.getCodigo().trim());
        ticket.setFechaCompra(ticket.getFechaCompra().trim());

        boolean esFestivo = festivoDAO.esFestivo(ticket.getFechaCompra());

        double total = ticket.calcularTotal(esFestivo);

        ticketDAO.agregarTicket(ticket);

        System.out.println("Ticket vendido correctamente. Total: $" + total);
    }

    public List<Ticket> listarTickets() {
        return ticketDAO.listarTickets();
    }

    public Ticket buscarTicket(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        return ticketDAO.buscarTicket(codigo.trim());
    }

    public void eliminarTicket(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        ticketDAO.eliminarTicket(codigo.trim());
    }

    public List<Ticket> ticketsPorPasajeroYFecha(Pasajero pasajero, String fechaCompra) {
        if (pasajero == null) {
            throw new IllegalArgumentException("Pasajero inválido");
        }
        if (fechaCompra == null || fechaCompra.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        return ticketDAO.ticketsPorPasajeroYFecha(pasajero, fechaCompra.trim());
    }

    public List<Ticket> ticketsPorTipoPasajero(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de pasajero no puede estar vacío");
        }
        return ticketDAO.ticketsPorTipoPasajero(tipo.trim());
    }

    public List<Ticket> ticketsPorTipoVehiculo(String tipoVehiculo) {
        if (tipoVehiculo == null || tipoVehiculo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehículo no puede estar vacío");
        }
        return ticketDAO.ticketsPorTipoVehiculo(tipoVehiculo.trim());
    }

    public void resumenDia(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        ticketDAO.resumenDia(fecha.trim());
    }
}