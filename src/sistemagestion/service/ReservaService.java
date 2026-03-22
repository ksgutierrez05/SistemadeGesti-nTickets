package sistemagestion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sistemagestion.dao.ReservaDAO;
import sistemagestion.dao.TicketDAO;
import sistemagestion.dao.festivoDAO;
import sistemagestion.dao.pasajeroDAO;
import sistemagestion.dao.VehiculoDAO;
import sistemagestion.model.Pasajero;
import sistemagestion.model.Reserva;
import sistemagestion.model.Ticket;
import sistemagestion.model.Vehiculo;

public class ReservaService {

    private ReservaDAO reservaDAO;
    private TicketDAO ticketDAO;
    private pasajeroDAO pasajeroDAO;
    private festivoDAO festivoDAO;
    private VehiculoDAO vehiculoDAO;

    public ReservaService() {
        this.reservaDAO = new ReservaDAO();
        this.ticketDAO = new TicketDAO();
        this.pasajeroDAO = new pasajeroDAO();
        this.festivoDAO = new festivoDAO();
        this.vehiculoDAO = new VehiculoDAO();
    }

    public void crearReserva(String codigo, String documentoPasajero, String placaVehiculo,
                             String fechaViaje, int capacidadVehiculo) {

        if (codigo == null || codigo.trim().isEmpty())
            throw new IllegalArgumentException("El código no puede estar vacío");

        if (documentoPasajero == null || documentoPasajero.trim().isEmpty())
            throw new IllegalArgumentException("El documento del pasajero no puede estar vacío");

        if (placaVehiculo == null || placaVehiculo.trim().isEmpty())
            throw new IllegalArgumentException("La placa del vehículo no puede estar vacía");

        if (fechaViaje == null || fechaViaje.trim().isEmpty())
            throw new IllegalArgumentException("La fecha de viaje no puede estar vacía");

        if (reservaDAO.buscarReserva(codigo) != null)
            throw new IllegalArgumentException("Ya existe una reserva con el código: " + codigo);

        // 🔥 VALIDAR CAPACIDAD (reservas + tickets)
        long totalOcupados = 0;

        for (Reserva r : listarActivas()) {
            if (r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)) {
                totalOcupados++;
            }
        }

        for (Ticket t : ticketDAO.listarTickets()) {
            if (t.getVehiculo().getPlaca().equalsIgnoreCase(placaVehiculo)
                    && t.getFechaViaje().equals(fechaViaje)) {
                totalOcupados++;
            }
        }

        if (totalOcupados >= capacidadVehiculo)
            throw new IllegalArgumentException("El vehículo no tiene cupos disponibles");

        // 🔥 VALIDAR DUPLICADO
        for (Reserva r : listarActivas()) {
            if (r.getDocumentoPasajero().equalsIgnoreCase(documentoPasajero)
                    && r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)) {

                throw new IllegalArgumentException("El pasajero ya tiene una reserva para ese vehículo y fecha");
            }
        }

        // 🔥 FECHA CON HORA
        String fechaCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Reserva reserva = new Reserva(
                codigo,
                documentoPasajero,
                placaVehiculo,
                fechaCreacion,
                fechaViaje,
                "ACTIVA"
        );

        reservaDAO.guardarReserva(reserva);
        System.out.println("Reserva creada exitosamente.");
    }

    public void cancelarReserva(String codigo) {

        if (codigo == null || codigo.trim().isEmpty())
            throw new IllegalArgumentException("El código no puede estar vacío");

        String reserva = reservaDAO.buscarReserva(codigo);

        if (reserva == null)
            throw new IllegalArgumentException("No existe reserva con código: " + codigo);

        if (!reserva.getEstado().equalsIgnoreCase("ACTIVA"))
            throw new IllegalArgumentException("Solo se pueden cancelar reservas ACTIVAS");

        reserva.setEstado("CANCELADA");
        reservaDAO.actualizarReserva(reserva);

        System.out.println("Reserva cancelada exitosamente.");
    }

    public List<Reserva> listarActivas() {
        List<Reserva> activas = new ArrayList<>();

        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getEstado().equalsIgnoreCase("ACTIVA")) {
                activas.add(r);
            }
        }

        return activas;
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listarReservas();
    }

    public String buscarReserva(String codigo) {

        if (codigo == null || codigo.trim().isEmpty())
            throw new IllegalArgumentException("El código no puede estar vacío");

        String reserva = reservaDAO.buscarReserva(codigo);

        if (reserva == null)
            throw new IllegalArgumentException("No se encontró la reserva");

        return reserva;
    }

    public void eliminarReserva(String codigo) {

        if (codigo == null || codigo.trim().isEmpty())
            throw new IllegalArgumentException("El código no puede estar vacío");

        String reserva = reservaDAO.buscarReserva(codigo);

        if (reserva == null)
            throw new IllegalArgumentException("No existe la reserva");

        reservaDAO.eliminarReserva(codigo);

        System.out.println("Reserva eliminada correctamente.");
    }

    public void actualizarReserva(Reserva reserva) {

        if (reserva == null)
            throw new IllegalArgumentException("La reserva no puede ser null");

        if (reservaDAO.buscarReserva(reserva.getCodigo()) == null)
            throw new IllegalArgumentException("No existe la reserva");

        reservaDAO.actualizarReserva(reserva);

        System.out.println("Reserva actualizada correctamente.");
    }

    public List<Reserva> historialPorPasajero(String documentoPasajero) {

        if (documentoPasajero == null || documentoPasajero.trim().isEmpty())
            throw new IllegalArgumentException("El documento no puede estar vacío");

        List<Reserva> historial = new ArrayList<>();

        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getDocumentoPasajero().equalsIgnoreCase(documentoPasajero.trim())) {
                historial.add(r);
            }
        }

        return historial;
    }

    public Ticket convertirATicket(String codigoReserva, String codigoTicket) {

        if (codigoReserva == null || codigoReserva.trim().isEmpty())
            throw new IllegalArgumentException("Código de reserva vacío");

        if (codigoTicket == null || codigoTicket.trim().isEmpty())
            throw new IllegalArgumentException("Código de ticket vacío");

        Reserva reserva = buscarReserva(codigoReserva);

        if (!reserva.getEstado().equalsIgnoreCase("ACTIVA"))
            throw new IllegalArgumentException("Solo reservas ACTIVAS se pueden convertir");

        Pasajero pasajero = pasajeroDAO.buscarPasajero(reserva.getDocumentoPasajero());

        if (pasajero == null)
            throw new IllegalArgumentException("Pasajero no encontrado");

        String vehiculo = vehiculoDAO.buscarVehiculo(reserva.getPlacaVehiculo());

        long ticketsHoy = 0;

        for (Ticket t : ticketDAO.listarTickets()) {
            if (t.getPasajero().getDocumento().equals(pasajero.getDocumento())
                    && t.getFechaViaje().equals(reserva.getFechaViaje())) {
                ticketsHoy++;
            }
        }

        if (ticketsHoy >= 3)
            throw new IllegalArgumentException("Máximo 3 tickets por día");

        boolean esFestivo = festivoDAO.esFestivo(reserva.getFechaViaje());

        Ticket ticket = new Ticket(codigoTicket, pasajero, vehiculo, 0, reserva.getFechaViaje());

        double total = ticket.calcularTotal(esFestivo);

        ticketDAO.agregarTicket(ticket);

        reserva.setEstado("CONVERTIDA");
        reservaDAO.actualizarReserva(reserva);

        System.out.println("Reserva convertida. Total: $" + total);

        return ticket;
    }

    public int verificarVencidas() {

        List<Reserva> lista = reservaDAO.listarReservas();
        int canceladas = 0;

        long ahora = System.currentTimeMillis();

        for (Reserva r : lista) {

            if (!r.getEstado().equalsIgnoreCase("ACTIVA")) continue;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date fechaReserva = sdf.parse(r.getFechaCreacion());

                long diferencia = ahora - fechaReserva.getTime();
                long horas = diferencia / (1000 * 60 * 60);

                if (horas >= 24) {
                    r.setEstado("CANCELADA");
                    reservaDAO.actualizarReserva(r);
                    canceladas++;
                }

            } catch (Exception e) {
                System.out.println("Error en fecha: " + e.getMessage());
            }
        }

        System.out.println("Reservas vencidas canceladas: " + canceladas);
        return canceladas;
    }
}