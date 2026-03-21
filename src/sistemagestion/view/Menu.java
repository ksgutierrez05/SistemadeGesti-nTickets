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
import sistemagestion.model.Ruta;
import sistemagestion.model.Vehiculo;
import sistemagestion.service.PersonaService;
import sistemagestion.service.VehiculoService;

/**
 *
 * @author Lenovo
 */
public class Menu {

    private Scanner scanner;
    private PersonaService personaService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.personaService = new PersonaService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("|      SISTEMA TRANSCESAR       |");
            System.out.println("=================================");
            System.out.println("| 1. Gestion de Pasajeros       |");
            System.out.println("| 2. Gestion de Conductores     |");
            System.out.println("| 0. Salir                      |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuPasajeros();
                    break;
                case 2:
                    menuConductores();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ───── MENÚ PASAJEROS ─────
    private void menuPasajeros() {
        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("|    GESTION DE PASAJEROS      |");
            System.out.println("=================================");
            System.out.println("| 1. Registrar pasajero        |");
            System.out.println("| 2. Listar pasajeros          |");
            System.out.println("| 3. Buscar pasajero           |");
            System.out.println("| 4. Eliminar pasajero         |");
            System.out.println("| 0. Volver                    |");
            System.out.println("=================================");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

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
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ───── MÉTODOS PASAJERO ─────
    private void registrarPasajero() {
        System.out.println("\n=================================");
        System.out.println("|       " + "Registrar Pasajero" + "      |");
        System.out.println("=================================");

        System.out.print("Tipo documento: ");
        String tipoDoc = scanner.nextLine();

        System.out.print("Documento: ");
        String doc = leerDocumento();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();

        System.out.print("Fecha nacimiento (yyyy-MM-dd): ");
        String fechaNac = scanner.nextLine();
        if (!fechaNac.contains("-") || fechaNac.length() != 10) {
            System.out.println("Fecha invalida. Use formato yyyy-MM-dd");
            return;
        }

        System.out.print("Tipo (1-Regular, 2-Estudiante): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha de registro (yyyy-MM-dd): ");
        String fechaCompra = scanner.nextLine();

        try {
            Pasajero pasajero;

            if (tipo == 2) {
                pasajero = new sistemagestion.model.PasajeroEstudiante(
                        tipoDoc, doc, nombre, apellido, telefono, fechaNac);
            } else {
                pasajero = new sistemagestion.model.PasajeroRegular(
                        tipoDoc, doc, nombre, apellido, telefono, fechaNac);
            }

            personaService.registrarPasajero(pasajero, fechaCompra);

            System.out.println("Pasajero registrado");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarPasajeros() {
        System.out.println("\nLista de Pasajeros");

        List<Pasajero> lista = personaService.listarPasajeros();

        if (lista.isEmpty()) {
            System.out.println("No hay pasajeros registrados");
            return;
        }

        for (Pasajero p : lista) {
            p.imprimirDetalle();
        }
    }

    private void buscarPasajero() {
        System.out.println("\nBuscar Pasajero");
        System.out.print("Documento: ");
        String doc = scanner.nextLine().trim();

        try {
            Pasajero p = personaService.buscarPasajero(doc);
            p.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println("Pasajero no encontrado");
        }
    }

    private void eliminarPasajero() {
        System.out.println("\nEliminar Pasajero");
        System.out.print("Documento: ");
        String doc = scanner.nextLine().trim();

        try {
            personaService.eliminarPasajero(doc);
            System.out.println("Pasajero eliminado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String leerDocumento() {
        String doc = scanner.nextLine().trim();
        return doc;
    }

// ================= CONDUCTORES =================
    private void menuConductores() {
        int opcion;
        do {
            System.out.println("\nGESTION DE CONDUCTORES");
            System.out.println("1. Registrar conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Buscar conductor");
            System.out.println("4. Eliminar conductor");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

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
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    private void registrarConductor() {
        System.out.println("\n===== REGISTRAR CONDUCTOR =====");

        System.out.print("Tipo documento: ");
        String tipoDoc = scanner.nextLine().trim();

        System.out.print("Documento: ");
        String doc = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine().trim();

        System.out.print("Número de licencia: ");
        String licencia = scanner.nextLine().trim();

        System.out.print("Categoría (B1, B2, C1, C2): ");
        String categoria = scanner.nextLine().trim();

        System.out.print("Vencimiento licencia (yyyy-MM-dd): ");
        String vencimiento = scanner.nextLine().trim();

        try {
            Conductor conductor = new Conductor(
                    tipoDoc, doc, nombre, apellido, telefono,
                    licencia, categoria, vencimiento
            );

            personaService.registrarConductor(conductor);

            System.out.println("✅ Conductor registrado correctamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void listarConductores() {
        System.out.println("\nLista de Conductores");

        List<Conductor> lista = personaService.listarConductores();

        if (lista.isEmpty()) {
            System.out.println("No hay conductores registrados");
            return;
        }

        for (Conductor c : lista) {
            c.imprimirDetalle();
        }
    }

    private void buscarConductor() {
        System.out.println("\nBuscar Conductor");
        System.out.print("Documento: ");
        String doc = scanner.nextLine().trim();

        try {
            Conductor c = personaService.buscarConductor(doc);
            c.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println("Conductor no encontrado");
        }
    }

    private void eliminarConductor() {
        System.out.println("\nEliminar Conductor");
        System.out.print("Documento: ");
        String doc = scanner.nextLine().trim();

        try {
            personaService.eliminarConductor(doc);
            System.out.println("Conductor eliminado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // ================= VEHICULOS =================

private VehiculoService vehiculoService = new VehiculoService();

private void menuVehiculos() {

    int opcion;

    do {
        System.out.println("\n==== GESTION VEHICULOS ====");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");

        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1: registrarVehiculo(); break;
            case 2: listarVehiculos(); break;
            case 3: buscarVehiculo(); break;
            case 4: eliminarVehiculo(); break;
        }

    } while (opcion != 0);
}
private void registrarVehiculo() {

    System.out.println("\nRegistrar Vehiculo");

    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    System.out.print("Codigo ruta: ");
    String codigo = scanner.nextLine();

    Ruta ruta = new Ruta(codigo, "Medellin", "Bogota", 400);

    System.out.print("Tipo (1.Bus 2.Buseta 3.Microbus): ");
    int tipo = scanner.nextInt();

    Vehiculo v;

    switch (tipo) {
        case 1:
            v = new Bus(ruta, placa, true, 45, 15000);
            break;
        case 2:
            v = new Buseta(ruta, placa, true, 19, 8000);
            break;
        default:
            v = new Microbus(ruta, placa, true, 25, 10000);
            break;
    }

    vehiculoService.registrarVehiculo(v);
}
private void listarVehiculos() {

    List<Vehiculo> lista = vehiculoService.listarVehiculos();

    if (lista.isEmpty()) {
        System.out.println("No hay vehiculos");
        return;
    }

    for (Vehiculo v : lista) {
        v.imprimirDetalle();
    }
}
private void buscarVehiculo() {

    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    try {
        Vehiculo v = vehiculoService.buscarVehiculo(placa);
        v.imprimirDetalle();
    } catch (Exception e) {
        System.out.println("Vehiculo no encontrado");
    }
}
private void eliminarVehiculo() {

    System.out.print("Placa: ");
    String placa = scanner.nextLine();

    vehiculoService.eliminarVehiculo(placa);
}
}
