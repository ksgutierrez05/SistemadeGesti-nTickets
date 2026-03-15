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

    Pasajero pasajero = ticket.getPasajero();

    if (pasajero == null)
        throw new IllegalArgumentException("El ticket no tiene pasajero");

    if (ticket.getFechaCompra() == null || ticket.getFechaCompra().isEmpty())
        throw new IllegalArgumentException("Fecha de compra inválida");

    if (ticketDAO.buscarTicket(ticket.getCodigo()) != null) {
        throw new IllegalArgumentException("Ya existe un ticket con ese código");
    }

    List<Ticket> ticketsHoy =
            ticketDAO.ticketsPorPasajeroYFecha(pasajero, ticket.getFechaCompra());

    if (ticketsHoy.size() >= 3) {
        throw new IllegalArgumentException(
                "El pasajero ya tiene " + ticketsHoy.size() +
                " tickets para esta fecha.");
    }

    double precioBase = ticket.getPrecioBase();

    if (festivoDAO.esFestivo(ticket.getFechaCompra())) {
        precioBase = precioBase * 1.2;
    }

    ticket.setPrecioBase(precioBase);

    double total = ticket.calcularTotal();

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

public void resumenDia(String fecha) {
    ticketDAO.resumenDia(fecha);
}


}