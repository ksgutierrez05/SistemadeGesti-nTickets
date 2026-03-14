package sistemagestion.dao;

import sistemagestion.model.Ticket;
import java.util.ArrayList;

public class TicketDAO {

    private ArrayList<Ticket> listaTickets;

    public TicketDAO() {
        listaTickets = new ArrayList<>();
    }

    public void agregarTicket(Ticket ticket) {
        listaTickets.add(ticket);
    }

    public ArrayList<Ticket> listarTickets() {
        return listaTickets;
    }

    public Ticket buscarTicket(String codigo) {

        for (Ticket t : listaTickets) {

            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return t;
            }

        }

        return null;
    }

    public void eliminarTicket(String codigo) {

        Ticket ticket = buscarTicket(codigo);

        if (ticket != null) {
            listaTickets.remove(ticket);
        }

    }

}