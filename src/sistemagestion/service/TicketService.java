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

        if (ticket == null)
            throw new IllegalArgumentException("Ticket inválido");

        if (ticket.getCodigo() == null || ticket.getCodigo().isEmpty())
            throw new IllegalArgumentException("El ticket debe tener un código");

        if (ticket.getPasajero() == null)
            throw new IllegalArgumentException("El ticket no tiene pasajero");

        if (ticket.getVehiculo() == null)
            throw new IllegalArgumentException("El ticket no tiene vehículo válido");

        if (ticket.getFechaCompra() == null || ticket.getFechaCompra().isEmpty())
            throw new IllegalArgumentException("Fecha de compra inválida");

        if (ticketDAO.buscarTicket(ticket.getCodigo()) != null)
            throw new IllegalArgumentException("Ya existe un ticket con ese código");

        List<Ticket> ticketsHoy =
                ticketDAO.ticketsPorPasajeroYFecha(ticket.getPasajero(), ticket.getFechaCompra());

        if (ticketsHoy.size() >= 3) {
            throw new IllegalArgumentException(
                    "El pasajero ya tiene " + ticketsHoy.size() + " tickets para esta fecha.");
        }

        boolean esFestivo = festivoDAO.esFestivo(ticket.getFechaCompra());

        double total = ticket.calcularTotal(esFestivo);

        // Guardar ticket
        ticketDAO.agregarTicket(ticket);

        System.out.println("Ticket vendido correctamente. Total: $" + total);
    }

    public List<Ticket> listarTickets() {
        return ticketDAO.listarTickets();
    }

    public Ticket buscarTicket(String codigo) {
        return ticketDAO.buscarTicket(codigo);
    }

    public void eliminarTicket(String codigo) {
        ticketDAO.eliminarTicket(codigo);
    }

    public List<Ticket> ticketsPorPasajeroYFecha(Pasajero pasajero, String fechaCompra) {
        return ticketDAO.ticketsPorPasajeroYFecha(pasajero, fechaCompra);
    }

    public List<Ticket> ticketsPorTipoPasajero(String tipo) {
        return ticketDAO.ticketsPorTipoPasajero(tipo);
    }

    public List<Ticket> ticketsPorTipoVehiculo(String tipoVehiculo) {
        return ticketDAO.ticketsPorTipoVehiculo(tipoVehiculo);
    }

    public void resumenDia(String fecha) {
        ticketDAO.resumenDia(fecha);
    }
}