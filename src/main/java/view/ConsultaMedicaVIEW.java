/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ConsultaMedicaController;
import controller.MascotaController;
import controller.VeterinarioController;
import controller.CitaController;
import java.time.LocalDateTime;
import model.ConsultaMedica;
import model.Mascota;
import model.Veterinario;
import model.Cita;
import java.util.List;
import java.util.Scanner;

public class ConsultaMedicaVIEW {

    private ConsultaMedicaController controller;
    private MascotaController mascotaController;
    private VeterinarioController veterinarioController;
    private CitaController citaController;
    private Scanner scanner;

    public ConsultaMedicaVIEW() {
        controller = new ConsultaMedicaController();
        mascotaController = new MascotaController();
        veterinarioController = new VeterinarioController();
        citaController = new CitaController();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE CONSULTAS MÉDICAS =====");
            System.out.println("1. Registrar nueva consulta");
            System.out.println("2. Listar todas las consultas");
            System.out.println("3. Buscar consulta por ID");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\n--- REGISTRAR NUEVA CONSULTA ---");

                    // Mostrar mascotas registradas
                    List<Mascota> mascotas = mascotaController.listarMascotas();
                    if (mascotas.isEmpty()) {
                        System.out.println("⚠️ No hay mascotas registradas. Registre una primero.");
                        break;
                    }
                    System.out.println("\n--- MASCOTAS REGISTRADAS ---");
                    for (Mascota m : mascotas) {
                        System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre() + " | Dueño: " + m.getNombre_dueño());
                    }

                    System.out.print("\nSeleccione el ID de la mascota: ");
                    int mascotaId = scanner.nextInt();

                    // Mostrar veterinarios registrados
                    List<Veterinario> veterinarios = veterinarioController.ListarVeterinarios();
                    if (veterinarios.isEmpty()) {
                        System.out.println("⚠️ No hay veterinarios registrados. Registre uno primero.");
                        break;
                    }
                    System.out.println("\n--- VETERINARIOS REGISTRADOS ---");
                    for (Veterinario v : veterinarios) {
                        System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombreCompleto()+ " | Especialidad: " + v.getEspecialidad());
                    }

                    System.out.print("\nSeleccione el ID del veterinario: ");
                    int veterinarioId = scanner.nextInt();

                    // Mostrar citas registradas
                    List<Cita> citas = citaController.listarCitas();
                    System.out.println("\n--- CITAS DISPONIBLES ---");
                    if (citas.isEmpty()) {
                        System.out.println("No hay citas registradas, puede dejar el campo en 0.");
                    } else {
                        for (Cita c : citas) {
                            System.out.println("ID: " + c.getId() + " | Mascota: " + c.getMascota_nombre()+ " | Fecha: " + c.getFecha_hora());
                        }
                    }

                    System.out.print("\nSeleccione el ID de la cita (0 si no aplica): ");
                    int citaId = scanner.nextInt();
                    scanner.nextLine();

                    // Campos de la consulta médica
                    System.out.print("Motivo de la consulta: ");
                    String motivo = scanner.nextLine();

                    System.out.print("Síntomas observados: ");
                    String sintomas = scanner.nextLine();

                    System.out.print("Diagnóstico: ");
                    String diagnostico = scanner.nextLine();

                    System.out.print("Recomendaciones: ");
                    String recomendaciones = scanner.nextLine();

                    System.out.print("Observaciones adicionales: ");
                    String observaciones = scanner.nextLine();

                    double peso = 0;
                    while (true) {
                        System.out.print("Peso registrado (kg): ");
                        if (scanner.hasNextDouble()) {
                            peso = scanner.nextDouble();
                            scanner.nextLine(); // limpiar el buffer
                            if (peso > 0 && peso < 200) break; // rango válido
                            else System.out.println("⚠️ El peso debe estar entre 0 y 200 kg.");
                        } else {
                            System.out.println("⚠️ Ingrese un número válido para el peso.");
                            scanner.nextLine(); // limpiar entrada inválida
                        }
                    }

                    double temperatura = 0;
                    while (true) {
                        System.out.print("Temperatura (°C): ");
                        if (scanner.hasNextDouble()) {
                            temperatura = scanner.nextDouble();
                            scanner.nextLine(); // limpiar el buffer
                            if (temperatura > 30 && temperatura < 45) break; // rango lógico
                            else System.out.println("⚠️ La temperatura debe estar entre 30°C y 45°C.");
                        } else {
                            System.out.println("⚠️ Ingrese un número válido para la temperatura.");
                            scanner.nextLine(); // limpiar entrada inválida
                        }
                    }


                    ConsultaMedica consulta = new ConsultaMedica();
                    consulta.setMascota_id(mascotaId);
                    consulta.setVeterinario_id(veterinarioId);
                    consulta.setCita_id(citaId == 0 ? null : citaId);
                    consulta.setMotivo(motivo);
                    consulta.setSintomas(sintomas);
                    consulta.setDiagnostico(diagnostico);
                    consulta.setRecomendaciones(recomendaciones);
                    consulta.setObservaciones(observaciones);
                    consulta.setPeso_registrado(peso);
                    consulta.setTemperatura(temperatura);
                    consulta.setFecha_hora(LocalDateTime.now());

                    controller.agregarConsulta(consulta);
                    System.out.println("✅ Consulta registrada correctamente.");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CONSULTAS MÉDICAS ---");
                    List<ConsultaMedica> lista = controller.listarConsultas();
                    if (lista.isEmpty()) {
                        System.out.println("No hay consultas registradas.");
                    } else {
                        for (ConsultaMedica c : lista) {
                            System.out.println("\nID: " + c.getId());
                            System.out.println("Mascota : " + c.getMascota_nombre());
                            System.out.println("Veterinario : " + c.getVeterinario_nombre());
                             System.out.println("Cita: " + (c.getCita_id() != 0 
                                ? "ID " + c.getCita_id() + " | Fecha: " + c.getCita_fecha_hora() + " | Estado: " + c.getCita_estado()
                                 : "Sin cita asociada"));
                            System.out.println("Motivo: " + c.getMotivo());
                            System.out.println("Síntomas: " + c.getSintomas());
                            System.out.println("Diagnóstico: " + c.getDiagnostico());
                            System.out.println("Recomendaciones: " + c.getRecomendaciones());
                            System.out.println("Observaciones: " + c.getObservaciones());
                            System.out.println("Peso: " + c.getPeso_registrado() + " kg");
                            System.out.println("Temperatura: " + c.getTemperatura() + " °C");
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("\nIngrese el ID de la consulta: ");
                    int id = scanner.nextInt();
                    ConsultaMedica consultaBuscada = controller.buscarPorId(id);
                    if (consultaBuscada != null) {
                        System.out.println("\n--- DETALLE DE LA CONSULTA ---");
                        System.out.println("ID: " + consultaBuscada.getId());
                        System.out.println("Mascota : " + consultaBuscada.getMascota_nombre());
                        System.out.println("Veterinario : " + consultaBuscada.getVeterinario_nombre());
                        System.out.println("Cita: " + (consultaBuscada.getCita_id() != 0 
                        ? "ID " + consultaBuscada.getCita_id() + " | Fecha: " + consultaBuscada.getCita_fecha_hora() + " | Estado: " + consultaBuscada.getCita_estado()
                        : "Sin cita asociada"));
                        System.out.println("Motivo: " + consultaBuscada.getMotivo());
                        System.out.println("Síntomas: " + consultaBuscada.getSintomas());
                        System.out.println("Diagnóstico: " + consultaBuscada.getDiagnostico());
                        System.out.println("Recomendaciones: " + consultaBuscada.getRecomendaciones());
                        System.out.println("Observaciones: " + consultaBuscada.getObservaciones());
                        System.out.println("Peso: " + consultaBuscada.getPeso_registrado() + " kg");
                        System.out.println("Temperatura: " + consultaBuscada.getTemperatura() + " °C");
                    } else {
                        System.out.println("❌ No se encontró una consulta con ese ID.");
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }

        } while (opcion != 0);
    }
}
