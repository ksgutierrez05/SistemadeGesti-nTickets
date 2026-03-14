package sistemagestion.service;

import sistemagestion.dao.TicketDAO;
import sistemagestion.model.Ticket;
public class TicketService {

    private TicketDAO ticketDAO;
    private int cuposDisponibles;

    public TicketService(int cuposDisponibles) {
        ticketDAO = new TicketDAO();
        this.cuposDisponibles = cuposDisponibles;
    }

    public void venderTicket(Ticket ticket) {

        if (ticket == null) {
            System.out.println("Ticket inválido");
            return;
        }

        // VALIDAR CUPOS
        if (cuposDisponibles <= 0) {
            System.out.println("No hay cupos disponibles");
            return;
        }

        ticket.calcularValorFinal();

        ticketDAO.agregarTicket(ticket);

        cuposDisponibles--;

        System.out.println("Ticket vendido correctamente");
    }

}