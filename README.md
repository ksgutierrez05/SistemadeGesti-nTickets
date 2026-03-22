# Sistema de Gestión de Tickets — TransCesar S.A.S

Sistema desarrollado en Java como solución al problema de gestión de cupos en la empresa de transporte TransCesar S.A.S., incorporando un módulo de reservas para mejorar la experiencia del cliente y optimizar la ocupación de vehículos

## Arquitectura del sistema
El proyecto sigue una arquitectura en capas:
- Model → Entidades (Reserva, Ticket, Pasajero, Vehículo)
- DAO → Persistencia en archivos .txt
- Service → Lógica de negocio
- View → Interfaz en consola

## Conceptos POO aplicados
- Encapsulamiento → Uso de atributos privados con métodos getters y setters para el acceso controlado a los datos.
- Abstracción → Separación de responsabilidades mediante capas (Model, DAO, Service, View).
- Modularidad → Organización del sistema en paquetes para facilitar mantenimiento y escalabilidad.
- Responsabilidad única → Cada clase cumple una función específica dentro del sistema.

## Nueva funcionalidad: Módulo de Reservas

Se implementa la clase Reserva como una nueva entidad del sistema, permitiendo a los pasajeros apartar cupos de manera anticipada:
-Atributos de la clase Reserva
- Código único
- Documento del pasajero
- Placa del vehículo
-Fecha de creación
- Fecha del viaje
- Estado

## Reglas de negocio

* Un vehículo no puede superar su capacidad máxima considerando:

  * Tickets vendidos
  * Reservas activas

* Una reserva expira después de **24 horas** si no se convierte en ticket.

* No se permiten reservas duplicadas para:

  * Mismo pasajero
  * Mismo vehículo
  * Misma fecha

* Al convertir una reserva en ticket se aplican:

  * Descuentos por tipo de pasajero
  * Tarifas especiales en días festivos


  ### Estructura del proyecto

```plaintext
SistemaGestionTickets/
├── src/
│   └── sistemagestion/
│       ├── dao/
│       │   └── (Clases DAO para persistencia en archivos)
│       │
│       ├── model/
│       │   ├── Bus.java
│       │   ├── Buseta.java
│       │   ├── Calculable.java
│       │   ├── Conductor.java
│       │   ├── Imprimible.java
│       │   ├── Microbus.java
│       │   ├── Pasajero.java
│       │   ├── PasajeroAdultoMayor.java
│       │   ├── PasajeroEstudiante.java
│       │   ├── PasajeroRegular.java
│       │   ├── Persona.java
│       │   ├── Reserva.java
│       │   ├── Ruta.java
│       │   ├── Ticket.java
│       │   └── Vehiculo.java
│       │
│       ├── service/
│       │   ├── PersonaService.java
│       │   ├── ReservaService.java
│       │   ├── RutaService.java
│       │   ├── TicketService.java
│       │   └── VehiculoService.java
│       │
│       ├── view/
│       │   └── Main.java
│
├── datos/
│   ├── reservas.txt
│   ├── tickets.txt
│   ├── pasajeros.txt
│   ├── vehiculos.txt
│   └── festivos.txt
│
└── SistemaTransCesar.java
```

---

## Autores
- LIder: katherine sofia gutierrez barliza
- Desarrolador 1:maria cristina martinez hinojosa
- Desarrollador 2: bayron enrique lobo lopez
 Universidad Popular del Cesar  
Programación de Computadores III

