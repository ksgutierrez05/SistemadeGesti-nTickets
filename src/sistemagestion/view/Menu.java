/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.view;

import java.util.List;
import java.util.Scanner;
import sistemagestion.model.Bus;
import sistemagestion.model.Buseta;
import sistemagestion.model.Conductor;
import sistemagestion.model.Microbus;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroEstudiante;
import sistemagestion.model.PasajeroRegular;
import sistemagestion.model.Ruta;
import sistemagestion.model.Vehiculo;
import sistemagestion.service.PersonaService;
import sistemagestion.service.RutaService;
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

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.personaService = new PersonaService();
        this.vehiculoService = new VehiculoService();
        this.rutaService = new RutaService();

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
                    //menuVehiculos();
                    break;
                case 4:
                    //menuRutas();
                    break;
                case 5:
                    //menuTickets();
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

            if (rutas.isEmpty()) {
                System.out.println("No hay rutas registradas.");
                return;
            }

            List<Ruta> disponibles = vehiculoService.obtenerRutasDisponibles((RutaService) rutas);

            if (disponibles.isEmpty()) {
                System.out.println("Todas las rutas ya están asignadas.");
                return;
            }

            Ruta ruta = disponibles.get(0);

            System.out.print("Tipo (1.Bus 2.Buseta 3.Microbus): ");
            int tipo = leerEntero();

            Vehiculo v;

            switch (tipo) {
                case 1:
                    v = new Bus(null, ruta, placa, true, 45, 15000);
                    break;
                case 2:
                    v = new Buseta(null, ruta, placa, true, 19, 8000);
                    break;
                case 3:
                    v = new Microbus(null, ruta, placa, true, 25, 10000);
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

            String resultado = vehiculoService.buscarVehiculo(placa);

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
}
