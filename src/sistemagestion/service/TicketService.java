package sistemagestion.service;

import java.util.List;
import sistemagestion.dao.TicketDAO;
import sistemagestion.dao.festivoDAO;
import sistemagestion.model.Pasajero;
import sistemagestion.model.Ticket;

public class TicketService {

    private TicketDAO ticketDAO;
    private festivoDAO festivoDAO;
    private int cuposDisponibles;

    public TicketService(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
        this.ticketDAO = new TicketDAO();
        this.festivoDAO = new festivoDAO();
    }

    public void venderTicket(Ticket ticket) {
        if (ticket == null) throw new IllegalArgumentException("Ticket inválido");

        Pasajero pasajero = ticket.getPasajero();

        
        if (cuposDisponibles <= 0) {
            throw new IllegalArgumentException("No hay cupos disponibles");
        }

      
        List<Ticket> ticketsHoy = ticketDAO.ticketsPorPasajeroYFecha(pasajero, ticket.getFechaCompra());
        if (ticketsHoy.size() >= 3) {
            throw new IllegalArgumentException(
                "El pasajero ya tiene " + ticketsHoy.size() + " tickets para esta fecha."
            );
        }

      
        if (festivoDAO.esFestivo(ticket.getFechaCompra())) {
            ticket.setPrecioBase(ticket.getPrecioBase() * 1.2); 
        }

       
        double total = ticket.calcularTotal();

        ticketDAO.agregarTicket(ticket);


        cuposDisponibles--;

        System.out.println(" Ticket vendido correctamente. Total: $" + total);
    }
}