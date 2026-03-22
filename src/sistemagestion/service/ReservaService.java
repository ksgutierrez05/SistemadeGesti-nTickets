
package sistemagestion.service;

import sistemagestion.dao.ReservaDAO;
import sistemagestion.model.Reserva;
import sistemagestion.model.Vehiculo;

import java.util.List;
import java.util.UUID;

import sistemagestion.dao.ReservaDAO;

import sistemagestion.dao.ReservaDAO;

import sistemagestion.dao.ReservaDAO;

import sistemagestion.dao.ReservaDAO;

public class ReservaService {

    private ReservaDAO reservaDAO;
    private VehiculoService vehiculoService;
    private PersonaService personaService;

    public ReservaService(VehiculoService vehiculoService, PersonaService personaService) {
        this.reservaDAO = new ReservaDAO();
        this.vehiculoService = vehiculoService;
        this.personaService = personaService;
    }

    // Crear reserva con fechas manuales
// Crear reserva con fechas manuales y código manual
public void crearReserva(String codigo, String docPasajero, String placaVehiculo, String fechaCreacion, String fechaViaje) {
    // Verificar pasajero
    try {
        personaService.buscarPasajero(docPasajero);
    } catch (IllegalArgumentException e) {
        System.out.println("Pasajero no encontrado: " + docPasajero);
        return;
    }

    // Verificar vehículo
    Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorPlaca(placaVehiculo);
    if (vehiculo == null) {
        System.out.println("Vehículo no encontrado: " + placaVehiculo);
        return;
    }

    // Evitar duplicados
    List<Reserva> reservas = reservaDAO.listarReservas();
    boolean duplicado = reservas.stream()
        .anyMatch(r -> r.getDocumentoPasajero().equalsIgnoreCase(docPasajero)
                    && r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)
                    && r.getEstado().equalsIgnoreCase("Activa"));
    if (duplicado) {
        System.out.println("Ya existe una reserva activa para este pasajero en este vehículo y fecha.");
        return;
    }

    // Verificar capacidad
    long activas = reservas.stream()
            .filter(r -> r.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                    && r.getEstado().equalsIgnoreCase("Activa"))
            .count();
    if (activas >= vehiculo.getCapacidad()) {
        System.out.println("No hay cupos disponibles en el vehículo.");
        return;
    }

    // Crear reserva usando el código manual
    Reserva r = new Reserva(
            codigo,        // <-- código manual
            docPasajero,
            placaVehiculo,
            fechaCreacion,
            fechaViaje,
            "Activa"
    );
    reservaDAO.guardarReserva(r);
    System.out.println("Reserva creada exitosamente. Código: " + r.getCodigo());
}

public List<Reserva> listarReservas() {
    return reservaDAO.listarReservas();
}
    // Listar reservas activas
    public void listarReservasActivas() {
        List<Reserva> reservas = reservaDAO.listarReservas();
        reservas.stream()
                .filter(r -> r.getEstado().equalsIgnoreCase("Activa"))
                .forEach(r -> System.out.println(r.getCodigo() + " - " + r.getDocumentoPasajero() + " - " + r.getPlacaVehiculo() + " - " + r.getFechaViaje()));
    }

    // Cancelar reserva
    public void cancelarReserva(String codigo) {
        Reserva r = reservaDAO.buscarReservaPorCodigo(codigo);
        if (r == null) {
            System.out.println("Reserva no encontrada: " + codigo);
            return;
        }
        r.setEstado("Cancelada");
        reservaDAO.actualizarReserva(r);
        System.out.println("Reserva cancelada: " + codigo);
    }

    // Convertir a ticket
    public void convertirReserva(String codigo) {
        Reserva r = reservaDAO.buscarReservaPorCodigo(codigo);
        if (r == null) {
            System.out.println("Reserva no encontrada: " + codigo);
            return;
        }
        r.setEstado("Convertida");
        reservaDAO.actualizarReserva(r);
        System.out.println("Reserva convertida a ticket: " + codigo);
    }
    public void verificarReservasVencidas(String fechaActualStr) {
    // Convertir la fecha actual ingresada a LocalDate
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
    java.time.LocalDate fechaActual;
    try {
        fechaActual = java.time.LocalDate.parse(fechaActualStr, formatter);
    } catch (Exception e) {
        System.out.println("Formato de fecha inválido. Use: yyyy-MM-dd");
        return;
    }

    List<Reserva> reservas = reservaDAO.listarReservas();
    int contadorVencidas = 0;

    for (Reserva r : reservas) {
        if (r.getEstado().equalsIgnoreCase("Activa")) {
            try {
                java.time.LocalDate fechaViaje = java.time.LocalDate.parse(r.getFechaViaje(), formatter);
                if (fechaViaje.isBefore(fechaActual)) {
                    r.setEstado("Vencida");
                    reservaDAO.actualizarReserva(r);
                    contadorVencidas++;
                    System.out.println("Reserva vencida: " + r.getCodigo() + " - Pasajero: " + r.getDocumentoPasajero());
                }
            } catch (Exception e) {
                System.out.println("Error al verificar la reserva: " + r.getCodigo() + " Fecha inválida");
            }
        }
    }

    System.out.println("Total de reservas vencidas actualizadas: " + contadorVencidas);
}
}
