/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.CitaController;
import controller.MascotaController;
import controller.VeterinarioController;
import enums.Estado;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Cita;
import model.Mascota;
import model.Veterinario;

public class CitaVIEW {

    private CitaController controller;
    private Scanner sc;

    public CitaVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new CitaController();

        do {
            System.out.println("-------- Gestión de Citas --------");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar Estado");
            System.out.println("4. Buscar Por ID");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    // -------------------- Selección de Mascota --------------------
                    MascotaController mascotaController = new MascotaController();
                    List<Mascota> mascotas = mascotaController.listarMascotas();
                    if (mascotas.isEmpty()) {
                        System.out.println("⚠ No hay mascotas registradas. Registre una primero.");
                        break;
                    }

                    System.out.println("Selecciona la mascota para la cita:");
                    for (Mascota m : mascotas) {
                        System.out.println(m.getId() + ". " + m.getNombre() + " - Dueño: " + m.getNombre_dueño());
                    }
                    int idMascota = sc.nextInt();
                    sc.nextLine();

                    // -------------------- Selección de Veterinario --------------------
                    VeterinarioController veterinarioController = new VeterinarioController();
                    List<Veterinario> veterinarios = veterinarioController.ListarVeterinarios();
                    if (veterinarios.isEmpty()) {
                        System.out.println("⚠ No hay veterinarios registrados.");
                        break;
                    }

                    System.out.println("Selecciona el veterinario encargado:");
                    for (Veterinario v : veterinarios) {
                        System.out.println(v.getId() + ". " + v.getNombreCompleto());
                    }
                    int idVeterinario = sc.nextInt();
                    sc.nextLine();

                    // -------------------- Ingreso de datos de la cita --------------------
                    System.out.println("Motivo de la cita: ");
                    String motivo = sc.nextLine();

                    System.out.println("Observaciones (opcional): ");
                    String observaciones = sc.nextLine();

                    System.out.println("Ingrese la fecha y hora (formato: 2025-10-31T15:30): ");
                    String fechaStr = sc.nextLine();
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaStr);

                    LocalDateTime fechaCreacion = LocalDateTime.now();
                    Estado estado = Estado.PROGRAMADA; // Estado inicial por defecto

                    // -------------------- Crear la cita --------------------
                    Cita cita = new Cita(0, idMascota, idVeterinario, fechaHora, motivo, estado, observaciones, fechaCreacion);

                    controller.agregarCita(cita);
                    System.out.println(" Cita agregada correctamente.");
                    break;

                case 2:
                    List<Cita> lista = controller.listarCitas();
                    if (lista.isEmpty()) {
                        System.out.println(" No hay citas registradas.");
                    } else {
                        System.out.println("-------- LISTA DE CITAS --------");
                        for (Cita c : lista) {
                            System.out.println("");
                            System.out.println("ID: " + c.getId());
                            System.out.println("Mascota: " + c.getMascota_nombre());
                            System.out.println("Veterinario: " + c.getVeterinario_nombre());
                            System.out.println("Fecha y hora: " + c.getFecha_hora());
                            System.out.println("Motivo: " + c.getMotivo());
                            System.out.println("Estado: " + c.getEstado());
                            System.out.println("Observaciones: " + c.getObservaciones());
                            System.out.println("--------------------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el ID de la cita para modificar el estado: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Ingrese el nuevo estado (PROGRAMADA / COMPLETADA/ CONFIRMADA/ INICIADA / CANCELADA): ");
                    String estadoInput = sc.nextLine().trim().toUpperCase();

                    try {
                        // Convierte el texto ingresado a enum
                        Estado nuevoEstado = Estado.valueOf(estadoInput);

                        // Llama al controller para actualizar
                        controller.actualizarEstado(id, nuevoEstado);

                        System.out.println("✅ Estado actualizado correctamente!");
                    } catch (IllegalArgumentException e) {
                        // Si el texto no coincide con PROGRAMADA / FINALIZADA / CANCELADA
                        System.out.println("❌ Estado inválido. Debe ser PROGRAMADA, FINALIZADA o CANCELADA.");
                    }
                    break;


                case 4:
                    System.out.println("Ingrese el ID de la cita a buscar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();

                    Cita citaEncontrada = controller.buscarPorId(idBuscar);
                    if (citaEncontrada != null) {
                        System.out.println("-------- INFORMACIÓN DE LA CITA --------");
                        System.out.println("ID: " + citaEncontrada.getId());
                        System.out.println("Mascota: " + citaEncontrada.getMascota_nombre());
                        System.out.println("Veterinario: " + citaEncontrada.getVeterinario_nombre());
                        System.out.println("Fecha y hora: " + citaEncontrada.getFecha_hora());
                        System.out.println("Motivo: " + citaEncontrada.getMotivo());
                        System.out.println("Estado: " + citaEncontrada.getEstado());
                        System.out.println("Observaciones: " + citaEncontrada.getObservaciones());
                        System.out.println("-----------------------------------------");
                    } else {
                        System.out.println(" No se encontró la cita con ese ID.");
                    }
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    return;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (true);
    }
}
