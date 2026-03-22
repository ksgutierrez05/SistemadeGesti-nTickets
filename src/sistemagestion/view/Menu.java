/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.view;

import java.util.List;
import java.util.Scanner;
import sistemagestion.dao.ConductorDAO;
import sistemagestion.model.Bus;
import sistemagestion.model.Buseta;
import sistemagestion.model.Conductor;
import sistemagestion.model.Microbus;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroEstudiante;
import sistemagestion.model.PasajeroRegular;
import sistemagestion.model.Reserva;
import sistemagestion.model.Ruta;
import sistemagestion.model.Ticket;
import sistemagestion.model.Vehiculo;
import sistemagestion.service.PersonaService;
import sistemagestion.service.ReservaService;
import sistemagestion.service.RutaService;
import sistemagestion.service.TicketService;
import sistemagestion.service.VehiculoService;

/**
 *
 * @author Lenovo
 */
public class Menu {

    private Scanner scanner;
    private PersonaService personaService;
    private VehiculoService vehiculoService;
    private RutaService rutaService;
    private TicketService ticketService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.personaService = new PersonaService();
        this.vehiculoService = new VehiculoService();
        this.rutaService = new RutaService();
        this.ticketService = new TicketService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("|         SISTEMA TRANSCESAR     |");
            System.out.println("=================================");
            System.out.println("| 1. Gestion de Pasajeros        |");
            System.out.println("| 2. Gestion de Conductores      |");
            System.out.println("| 3. Gestion de Vehiculos        |");
            System.out.println("| 4. Gestion de Rutas            |");
            System.out.println("| 5. Gestion de Tickets          |");
            System.out.println("| 6. Gestion de Reserva          |");
            System.out.println("| 0. Salir                       |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuPasajeros();
                    break;
                case 2:
                    menuConductores();
                    break;
                case 3:
                    menuVehiculos();
                    break;
                case 4:
                    menuRutas();
                    break;
                case 5:
                    menuTickets();
                    break;
                case 6:
                    menuReservas();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    // ================= PASAJEROS =================
    private void menuPasajeros() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("|      GESTION DE PASAJEROS      |");
            System.out.println("=================================");
            System.out.println("| 1. Registrar pasajero          |");
            System.out.println("| 2. Listar pasajeros            |");
            System.out.println("| 3. Buscar pasajero             |");
            System.out.println("| 4. Eliminar pasajero           |");
            System.out.println("| 5. Modificar pasajero          |");
            System.out.println("| 0. Volver                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPasajero();
                    break;
                case 2:
                    listarPasajeros();
                    break;
                case 3:
                    buscarPasajero();
                    break;
                case 4:
                    eliminarPasajero();
                    break;
                case 5:
                    modificarPasajero();
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void registrarPasajero() {
        try {
            System.out.println("\n===== REGISTRAR PASAJERO =====");

            System.out.print("Tipo documento: ");
            String tipoDoc = scanner.nextLine().trim();

            System.out.print("Documento: ");
            String doc = scanner.nextLine().trim();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine().trim();

            System.out.print("Telefono: ");
            String telefono = scanner.nextLine().trim();

            System.out.print("Fecha nacimiento (yyyy-MM-dd): ");
            String fechaNac = scanner.nextLine().trim();

            System.out.print("Tipo (1.Regular / 2.Estudiante): ");
            int tipo = leerEntero();

            System.out.print("Fecha de registro (yyyy-MM-dd): ");
            String fechaCompra = scanner.nextLine().trim();

            Pasajero pasajero;
            if (tipo == 2) {
                pasajero = new PasajeroEstudiante(tipoDoc, doc, nombre, apellido, telefono, fechaNac);
            } else {
                pasajero = new PasajeroRegular(tipoDoc, doc, nombre, apellido, telefono, fechaNac);
            }

            personaService.registrarPasajero(pasajero, fechaCompra);

        } catch (Exception e) {
            System.out.println("Error al registrar pasajero: " + e.getMessage());
        }
    }

    private void listarPasajeros() {
        List<Pasajero> lista = personaService.listarPasajeros();

        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }

        for (Pasajero p : lista) {
            p.imprimirDetalle();
        }
    }

    private void buscarPasajero() {
        try {
            System.out.print("Documento del pasajero: ");
            String doc = scanner.nextLine().trim();

            Pasajero p = personaService.buscarPasajero(doc);
            p.imprimirDetalle();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarPasajero() {
        try {
            System.out.print("Documento del pasajero a eliminar: ");
            String doc = scanner.nextLine().trim();

            personaService.eliminarPasajero(doc);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void modificarPasajero() {
        System.out.println("\n===== MODIFICAR PASAJERO =====");
        System.out.print("Documento del pasajero a modificar: ");
        String doc = scanner.nextLine();

        try {
            Pasajero existente = personaService.buscarPasajero(doc);

            System.out.println("\nDatos actuales:");
            existente.imprimirDetalle();

            System.out.println("\nIngrese los nuevos datos:");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();

            System.out.print("Fecha nacimiento (yyyy-MM-dd): ");
            String fechaNac = scanner.nextLine();

            System.out.print("Fecha actual / de modificacion (yyyy-MM-dd): ");
            String fechaCompra = scanner.nextLine();

            Pasajero actualizado;

            if (existente instanceof sistemagestion.model.PasajeroEstudiante) {
                actualizado = new sistemagestion.model.PasajeroEstudiante(
                        existente.getTipoDocumento(), doc, nombre, apellido, telefono, fechaNac);
            } else if (existente instanceof sistemagestion.model.PasajeroAdultoMayor) {
                actualizado = new sistemagestion.model.PasajeroAdultoMayor(
                        existente.getTipoDocumento(), doc, nombre, apellido, telefono, fechaNac);
            } else {
                actualizado = new sistemagestion.model.PasajeroRegular(
                        existente.getTipoDocumento(), doc, nombre, apellido, telefono, fechaNac);
            }

            personaService.modificarPasajero(actualizado, fechaCompra);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= CONDUCTORES =================
    private void menuConductores() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("|     GESTION DE CONDUCTORES     |");
            System.out.println("=================================");
            System.out.println("| 1. Registrar conductor         |");
            System.out.println("| 2. Listar conductores          |");
            System.out.println("| 3. Buscar conductor            |");
            System.out.println("| 4. Eliminar conductor          |");
            System.out.println("| 5. Modificar conductor         |");
            System.out.println("| 0. Volver                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarConductor();
                    break;
                case 2:
                    listarConductores();
                    break;
                case 3:
                    buscarConductor();
                    break;
                case 4:
                    eliminarConductor();
                    break;
                case 5:
                    modificarConductor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void registrarConductor() {
        try {
            System.out.println("\n===== REGISTRAR CONDUCTOR =====");

            System.out.print("Tipo documento: ");
            String tipoDoc = scanner.nextLine().trim();

            System.out.print("Documento: ");
            String doc = scanner.nextLine().trim();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine().trim();

            System.out.print("Telefono: ");
            String telefono = scanner.nextLine().trim();

            System.out.print("Numero de licencia: ");
            String licencia = scanner.nextLine().trim();

            System.out.print("Categoria licencia (C1/C2): ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Vencimiento licencia (yyyy-MM-dd): ");
            String vencimiento = scanner.nextLine().trim();

            Conductor conductor = new Conductor(
                    tipoDoc, doc, nombre, apellido, telefono,
                    licencia, categoria, vencimiento
            );

            personaService.registrarConductor(conductor);

        } catch (Exception e) {
            System.out.println("Error al registrar conductor: " + e.getMessage());
        }
    }

    private void listarConductores() {
        List<Conductor> lista = personaService.listarConductores();

        if (lista.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }

        for (Conductor c : lista) {
            c.imprimirDetalle();
        }
    }

    private void buscarConductor() {
        try {
            System.out.print("Documento del conductor: ");
            String doc = scanner.nextLine().trim();

            Conductor c = personaService.buscarConductor(doc);
            c.imprimirDetalle();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarConductor() {
        try {
            System.out.print("Documento del conductor a eliminar: ");
            String doc = scanner.nextLine().trim();

            personaService.eliminarConductor(doc);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void modificarConductor() {
        System.out.println("\n===== MODIFICAR CONDUCTOR =====");
        System.out.print("Documento del conductor a modificar: ");
        String doc = scanner.nextLine();

        try {
            Conductor existente = personaService.buscarConductor(doc);

            System.out.println("\nDatos actuales:");
            existente.imprimirDetalle();

            System.out.println("\nIngrese los nuevos datos:");

            System.out.print("Tipo documento: ");
            String tipoDoc = scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();

            System.out.print("Numero de licencia: ");
            String licencia = scanner.nextLine();

            System.out.print("Categoria licencia (C1/C2): ");
            String categoria = scanner.nextLine();

            System.out.print("Vencimiento licencia (yyyy-MM-dd): ");
            String vencimiento = scanner.nextLine();

            Conductor actualizado = new Conductor(
                    tipoDoc,
                    doc,
                    nombre,
                    apellido,
                    telefono,
                    licencia,
                    categoria,
                    vencimiento
            );

            personaService.modificarConductor(actualizado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================= VEHICULOS =================
    private void menuVehiculos() {
        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("|       GESTION VEHICULOS        |");
            System.out.println("=================================");
            System.out.println("| 1. Registrar vehiculo          |");
            System.out.println("| 2. Listar vehiculos            |");
            System.out.println("| 3. Buscar vehiculo             |");
            System.out.println("| 4. Eliminar vehiculo           |");
            System.out.println("| 0. Volver                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    buscarVehiculo();
                    break;
                case 4:
                    eliminarVehiculo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    private void registrarVehiculo() {
        try {
            System.out.println("\n===== REGISTRAR VEHICULO =====");

            System.out.print("Placa: ");
            String placa = scanner.nextLine().trim();
            List<Ruta> rutas = rutaService.listarRutas();
            System.out.print("¿Está disponible? (1.Si / 2.No): ");
            int estado = leerEntero();

            boolean disponible;

            if (estado == 1) {
                disponible = true;
            } else {
                disponible = false;
            }

            if (rutas.isEmpty()) {
                System.out.println("No hay rutas registradas.");
                return;
            }

            List<Ruta> disponibles = vehiculoService.obtenerRutasDisponibles(rutas);

            if (disponibles.isEmpty()) {
                System.out.println("Todas las rutas ya están asignadas.");
                return;
            }

            Ruta ruta = disponibles.get(0);

            System.out.print("Tipo (1.Bus 2.Buseta 3.Microbus): ");
            int tipo = leerEntero();
            System.out.print("Documento del conductor: ");
            String documentoConductor = scanner.nextLine().trim();

            ConductorDAO conductorDAO = new ConductorDAO();
            Conductor conductor = conductorDAO.buscarConductor(documentoConductor);

            if (conductor == null) {
                System.out.println("Conductor no encontrado. No se puede registrar el vehículo.");
                return;
            }

            Vehiculo v;

            switch (tipo) {
                case 1:
                    v = new Bus(conductor, ruta, placa, true, 45, 15000);
                    break;
                case 2:
                    v = new Buseta(conductor, ruta, placa, true, 19, 8000);
                    break;
                case 3:
                    v = new Microbus(conductor, ruta, placa, true, 25, 10000);
                    break;
                default:
                    System.out.println("Tipo de vehiculo invalido.");
                    return;
            }

            vehiculoService.registrarVehiculo(v);

        } catch (Exception e) {
            System.out.println("Error al registrar vehiculo: " + e.getMessage());
        }
    }

    private void listarVehiculos() {
        System.out.println("\n===== LISTA DE VEHICULOS =====");
        vehiculoService.listarVehiculos();
    }

    private void buscarVehiculo() {
        try {
            System.out.print("Placa del vehiculo: ");
            String placa = scanner.nextLine().trim();

            Vehiculo resultado = vehiculoService.buscarVehiculo(placa);

            if (resultado == null) {
                System.out.println("Vehiculo no encontrado.");
            } else {
                System.out.println("Vehiculo encontrado: " + resultado);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar vehiculo: " + e.getMessage());
        }
    }

    private void eliminarVehiculo() {
        try {
            System.out.print("Placa del vehiculo a eliminar: ");
            String placa = scanner.nextLine().trim();

            vehiculoService.eliminarVehiculo(placa);

        } catch (Exception e) {
            System.out.println("Error al eliminar vehiculo: " + e.getMessage());
        }
    }
    //================= RUTAS===================//

    private void menuRutas() {
        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("|        GESTION DE RUTAS        |");
            System.out.println("=================================");
            System.out.println("| 1. Registrar ruta             |");
            System.out.println("| 2. Listar rutas               |");
            System.out.println("| 3. Buscar ruta                |");
            System.out.println("| 4. Modificar ruta             |");
            System.out.println("| 5. Eliminar ruta              |");
            System.out.println("| 0. Volver                     |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarRuta();
                    break;
                case 2:
                    listarRutas();
                    break;
                case 3:
                    buscarRuta();
                    break;
                case 4:
                    modificarRuta();
                    break;
                case 5:
                    eliminarRuta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    private void registrarRuta() {
        try {
            System.out.println("\n===== REGISTRAR RUTA =====");

            System.out.print("Codigo: ");
            String codigo = scanner.nextLine().trim();

            System.out.print("Origen: ");
            String origen = scanner.nextLine().trim();

            System.out.print("Destino: ");
            String destino = scanner.nextLine().trim();

            System.out.print("Distancia: ");
            String distancia = scanner.nextLine().trim();

            System.out.print("Tiempo (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine().trim());

            Ruta ruta = new Ruta(codigo, origen, destino, distancia, tiempo);

            rutaService.registrarruta(ruta);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarRutas() {
        List<Ruta> rutas = rutaService.listarRutas();

        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }

        System.out.println("\n===== LISTA DE RUTAS =====");

        for (Ruta r : rutas) {
            System.out.println("--------------------------------");
            System.out.println("Codigo: " + r.getCodigo());
            System.out.println("Origen: " + r.getOrigen());
            System.out.println("Destino: " + r.getDestino());
            System.out.println("Distancia: " + r.getDistancia());
            System.out.println("Tiempo: " + r.getTiempo());
        }
    }

    private void buscarRuta() {
        System.out.print("Codigo de la ruta: ");
        String codigo = scanner.nextLine().trim();

        String resultado = rutaService.buscarRuta(codigo);

        if (resultado == null) {
            System.out.println("Ruta no encontrada.");
        }

    }

    private void modificarRuta() {
        try {
            System.out.println("\n===== MODIFICAR RUTA =====");

            System.out.print("Codigo de la ruta a modificar: ");
            String codigo = scanner.nextLine().trim();

            // Buscar si existe
            String resultado = rutaService.buscarRuta(codigo);

            if (resultado == null) {
                System.out.println("La ruta no existe.");
                return;
            }

            System.out.println("\nIngrese los nuevos datos:");

            System.out.print("Origen: ");
            String origen = scanner.nextLine().trim();

            System.out.print("Destino: ");
            String destino = scanner.nextLine().trim();

            System.out.print("Distancia: ");
            String distancia = scanner.nextLine().trim();

            System.out.print("Tiempo (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine().trim());

            // Crear nueva ruta con el mismo código
            Ruta rutaActualizada = new Ruta(codigo, origen, destino, distancia, tiempo);

            rutaService.actualizarRuta(rutaActualizada);

        } catch (Exception e) {
            System.out.println("Error al modificar ruta: " + e.getMessage());
        }
    }

    private void eliminarRuta() {
        System.out.print("Codigo de la ruta a eliminar: ");
        String codigo = scanner.nextLine().trim();

        rutaService.eliminarRuta(codigo);
    }
    // ================= APOYO =================

    private int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
// ================= TICKETS =================

    private void menuTickets() {
        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("|        GESTION DE TICKETS      |");
            System.out.println("=================================");
            System.out.println("| 1. Crear ticket                |");
            System.out.println("| 2. Listar tickets              |");
            System.out.println("| 3. Buscar ticket               |");
            System.out.println("| 4. Eliminar ticket             |");
            System.out.println("| 5. Tickets por fecha           |");
            System.out.println("| 6. Tickets por tipo pasajero   |");
            System.out.println("| 7. Tickets por tipo vehiculo   |");
            System.out.println("| 8. Resumen del dia             |");
            System.out.println("| 0. Volver                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    listarTickets();
                    break;
                case 3:
                    buscarTicket();
                    break;
                case 4:
                    eliminarTicket();
                    break;
                case 5:
                    ticketsPorFecha();
                    break;
                case 6:
                    ticketsPorTipoPasajero();
                    break;
                case 7:
                    ticketsPorTipoVehiculo();
                    break;
                case 8:
                    resumenDia();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    private void crearTicket() {
        try {
            System.out.println("\n===== CREAR TICKET =====");

            System.out.print("Codigo del ticket: ");
            String codigo = scanner.nextLine().trim();

            System.out.print("Documento del pasajero: ");
            String documento = scanner.nextLine().trim();

            Pasajero pasajero = personaService.buscarPasajero(documento);

            if (pasajero == null) {
                System.out.println("Pasajero no encontrado.");
                return;
            }

            System.out.print("Placa del vehiculo: ");
            String placa = scanner.nextLine().trim();

            Vehiculo vehiculo = vehiculoService.buscarVehiculo(placa);

            if (vehiculo == null) {
                System.out.println("Vehiculo no encontrado.");
                return;
            }

            if (!vehiculo.isDisponible()) {
                System.out.println("El vehiculo no está disponible.");
                return;
            }

            Ruta ruta = vehiculo.getRuta();

            if (ruta == null) {
                System.out.println("El vehiculo no tiene ruta asignada.");
                return;
            }

            System.out.print("Fecha de compra (yyyy-MM-dd): ");
            String fechaCompra = scanner.nextLine().trim();

            Ticket ticket = new Ticket(
                    codigo,
                    pasajero,
                    vehiculo,
                    vehiculo.getTarifaBase(),
                    fechaCompra
            );

            ticketService.venderTicket(ticket);

            vehiculo.setDisponible(false);
            vehiculoService.actualizarVehiculo(vehiculo);

            System.out.println("Ticket creado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al crear ticket: " + e.getMessage());
        }
    }

    private void listarTickets() {
        try {
            List<Ticket> lista = ticketService.listarTickets();

            if (lista.isEmpty()) {
                System.out.println("No hay tickets registrados.");
                return;
            }

            for (Ticket t : lista) {
                t.imprimirDetalle();
            }

        } catch (Exception e) {
            System.out.println("Error al listar tickets: " + e.getMessage());
        }
    }

    private void buscarTicket() {
        try {
            System.out.print("Codigo del ticket: ");
            String codigo = scanner.nextLine().trim();

            Ticket ticket = ticketService.buscarTicket(codigo);

            if (ticket == null) {
                System.out.println("Ticket no encontrado.");
                return;
            }

            ticket.imprimirDetalle();

        } catch (Exception e) {
            System.out.println("Error al buscar ticket: " + e.getMessage());
        }
    }

    private void eliminarTicket() {
        try {
            System.out.print("Codigo del ticket a eliminar: ");
            String codigo = scanner.nextLine().trim();

            ticketService.eliminarTicket(codigo);
            System.out.println("Ticket eliminado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar ticket: " + e.getMessage());
        }
    }

    private void ticketsPorFecha() {
        try {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();

            List<Ticket> lista = ticketService.listarTickets();
            boolean encontrado = false;

            for (Ticket t : lista) {
                if (t.getFechaCompra().equals(fecha)) {
                    t.imprimirDetalle();
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No hay tickets para esa fecha.");
            }

        } catch (Exception e) {
            System.out.println("Error al consultar tickets por fecha: " + e.getMessage());
        }
    }

    private void ticketsPorTipoPasajero() {
        try {
            System.out.println("Tipos disponibles:");
            System.out.println("1. PasajeroRegular");
            System.out.println("2. PasajeroEstudiante");
            System.out.println("3. PasajeroAdultoMayor");
            System.out.print("Seleccione una opcion: ");

            int opcion = leerEntero();
            String tipo = "";

            switch (opcion) {
                case 1:
                    tipo = "PasajeroRegular";
                    break;
                case 2:
                    tipo = "PasajeroEstudiante";
                    break;
                case 3:
                    tipo = "PasajeroAdultoMayor";
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    return;
            }

            List<Ticket> lista = ticketService.ticketsPorTipoPasajero(tipo);

            if (lista.isEmpty()) {
                System.out.println("No hay tickets para ese tipo de pasajero.");
                return;
            }

            for (Ticket t : lista) {
                t.imprimirDetalle();
            }

        } catch (Exception e) {
            System.out.println("Error al consultar tickets por tipo de pasajero: " + e.getMessage());
        }
    }

    private void ticketsPorTipoVehiculo() {
        try {
            System.out.println("Tipos disponibles:");
            System.out.println("1. Bus");
            System.out.println("2. Buseta");
            System.out.println("3. Microbus");
            System.out.print("Seleccione una opcion: ");

            int opcion = leerEntero();
            String tipoVehiculo = "";

            switch (opcion) {
                case 1:
                    tipoVehiculo = "Bus";
                    break;
                case 2:
                    tipoVehiculo = "Buseta";
                    break;
                case 3:
                    tipoVehiculo = "Microbus";
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    return;
            }

            List<Ticket> lista = ticketService.ticketsPorTipoVehiculo(tipoVehiculo);

            if (lista.isEmpty()) {
                System.out.println("No hay tickets para ese tipo de vehiculo.");
                return;
            }

            for (Ticket t : lista) {
                t.imprimirDetalle();
            }

        } catch (Exception e) {
            System.out.println("Error al consultar tickets por tipo de vehiculo: " + e.getMessage());
        }
    }

    private void resumenDia() {
        try {
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();

            ticketService.resumenDia(fecha);

        } catch (Exception e) {
            System.out.println("Error al generar resumen del dia: " + e.getMessage());
        }
    }
    // ================= RESERVAS =================

    private void menuReservas() {
        ReservaService reservaService = new ReservaService(vehiculoService, personaService);
        int opcion;

        do {
            System.out.println("\n=================================");
            System.out.println("|         GESTION RESERVAS       |");
            System.out.println("=================================");
            System.out.println("| 1. Crear nueva reserva         |");
            System.out.println("| 2. Cancelar reserva            |");
            System.out.println("| 3. Listar reservas activas     |");
            System.out.println("| 4. Historial de pasajero       |");
            System.out.println("| 5. Convertir reserva en ticket |");
            System.out.println("| 6. Verificar reservas vencidas |");
            System.out.println("| 0. Volver                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearReserva(reservaService);
                    break;
                case 2:
                    cancelarReserva(reservaService);
                    break;
                case 3:
                    listarReservasActivas(reservaService);
                    break;
                case 4:
                    historialPasajero(reservaService);
                    break;
                case 5:
                    convertirReserva(reservaService);
                    break;
                case 6:
                    verificarReservasVencidas(reservaService);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

// ================= OPCIONES =================
    private void crearReserva(ReservaService reservaService) {
        System.out.println("\n===== CREAR RESERVA =====");

        System.out.print("Codigo de la reserva: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Documento pasajero: ");
        String doc = scanner.nextLine().trim();

        System.out.print("Placa vehiculo: ");
        String placa = scanner.nextLine().trim();

        System.out.print("Fecha de creación (yyyy-MM-dd HH:mm): ");
        String fechaCreacion = scanner.nextLine().trim();

        System.out.print("Fecha viaje (yyyy-MM-dd): ");
        String fechaViaje = scanner.nextLine().trim();

        // Pasar el código manual al servicio
        reservaService.crearReserva(codigo, doc, placa, fechaCreacion, fechaViaje);
    }

    private void cancelarReserva(ReservaService reservaService) {
        System.out.print("Codigo de la reserva a cancelar: ");
        String codigo = scanner.nextLine().trim();
        reservaService.cancelarReserva(codigo);
    }

    private void listarReservasActivas(ReservaService reservaService) {
        System.out.println("\n===== RESERVAS ACTIVAS =====");
        List<Reserva> lista = reservaService.listarReservas();

        for (Reserva r : lista) {
            if (r.getEstado().equalsIgnoreCase("Activa")) {

                // Obtener pasajero
                Pasajero pasajero = null;
                try {
                    pasajero = personaService.buscarPasajero(r.getDocumentoPasajero());
                } catch (IllegalArgumentException e) {
                    System.out.println("Pasajero no encontrado: " + r.getDocumentoPasajero());
                }

                // Obtener vehículo
                Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorPlaca(r.getPlacaVehiculo());

                System.out.println("--------------------------------");
                System.out.println("Codigo reserva: " + r.getCodigo());

                if (pasajero != null) {
                    System.out.println("Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido());
                    System.out.println("Documento: " + pasajero.getDocumento());
                    System.out.println("Telefono: " + pasajero.getTelefono());
                } else {
                    System.out.println("Pasajero: No disponible");
                }

                System.out.println("Vehiculo: " + r.getPlacaVehiculo());

                if (vehiculo != null && vehiculo.getRuta() != null) {
                    System.out.println("Ruta: " + vehiculo.getRuta().getOrigen() + " -> " + vehiculo.getRuta().getDestino());
                    System.out.println("Distancia: " + vehiculo.getRuta().getDistancia());
                    System.out.println("Tiempo: " + vehiculo.getRuta().getTiempo() + " minutos");
                } else {
                    System.out.println("Ruta: No asignada");
                }

                System.out.println("Fecha creación: " + r.getFechaCreacion());
                System.out.println("Fecha viaje: " + r.getFechaViaje());
                System.out.println("Estado: " + r.getEstado());
            }
        }
    }

    private void historialPasajero(ReservaService reservaService) {
        System.out.print("Documento pasajero: ");
        String doc = scanner.nextLine().trim();

        System.out.println("\n===== HISTORIAL DE RESERVAS =====");
        List<Reserva> lista = reservaService.listarReservas();
        boolean encontrado = false;

        for (Reserva r : lista) {
            if (r.getDocumentoPasajero().equalsIgnoreCase(doc)) {
                encontrado = true;

                // Obtener el vehículo de la reserva
                Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorPlaca(r.getPlacaVehiculo());
                System.out.println("--------------------------------");
                System.out.println("Codigo reserva: " + r.getCodigo());
                System.out.println("Vehiculo: " + r.getPlacaVehiculo());

                if (vehiculo != null && vehiculo.getRuta() != null) {
                    System.out.println("Ruta: " + vehiculo.getRuta().getOrigen() + " -> " + vehiculo.getRuta().getDestino());
                    System.out.println("Distancia: " + vehiculo.getRuta().getDistancia());
                    System.out.println("Tiempo: " + vehiculo.getRuta().getTiempo() + " minutos");
                } else {
                    System.out.println("Ruta: No asignada");
                }

                System.out.println("Fecha creación: " + r.getFechaCreacion());
                System.out.println("Fecha viaje: " + r.getFechaViaje());
                System.out.println("Estado: " + r.getEstado());
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron reservas para este pasajero.");
        }
    }

    private void convertirReserva(ReservaService reservaService) {
        System.out.print("Codigo de la reserva a convertir: ");
        String codigo = scanner.nextLine().trim();
        reservaService.convertirReserva(codigo);
    }

    private void verificarReservasVencidas(ReservaService reservaService) {
        System.out.print("Ingrese la fecha actual (yyyy-MM-dd): ");
        String fechaActual = scanner.nextLine().trim();
        reservaService.verificarReservasVencidas(fechaActual);
        System.out.println("Verificación completada.");
    }
}
