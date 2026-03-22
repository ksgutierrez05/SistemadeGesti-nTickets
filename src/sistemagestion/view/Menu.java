/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.view;

import java.util.List;
import java.util.Scanner;
import sistemagestion.model.Conductor;
import sistemagestion.model.Pasajero;
import sistemagestion.model.PasajeroEstudiante;
import sistemagestion.model.PasajeroRegular;
import sistemagestion.service.PersonaService;


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

