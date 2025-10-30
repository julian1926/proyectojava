package view;

import controller.VeterinarioController;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import model.Veterinario;

public class VeterinarioVIEW {
    private VeterinarioController controller;
    private Scanner sc;

    public VeterinarioVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new VeterinarioController();

        do {
            System.out.println("\n--------- Gestión de veterinarios --------");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar estado");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Documento de identidad: ");
                    String DNI = sc.nextLine();
                    System.out.println("Licencia profesional: ");
                    String licencia = sc.nextLine();
                    System.out.println("Especialidad: ");
                    String especialidad = sc.nextLine();
                    System.out.println("Telefono: ");
                    String telefono = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();

                    // Validación de la fecha de contratación
                    String fecha = "";
                    Date fechaV = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false); // Para evitar fechas imposibles

                    while (true) {
                        System.out.println("Fecha de contratación (formato: yyyy-MM-dd): ");
                        fecha = sc.nextLine();

                        try {
                            // Intentamos parsear la fecha
                            fechaV = new Date(sdf.parse(fecha).getTime());
                            break; // Si la fecha es válida, salimos del bucle
                        } catch (ParseException e) {
                            System.out.println("Fecha inválida. Por favor, ingrese una fecha válida en formato yyyy-MM-dd.");
                        }
                    }

                    boolean estado = true;
                    Veterinario v = new Veterinario(nombre, DNI, licencia, especialidad, telefono, email, fechaV, estado);
                    controller.agregarVeterinario(v);
                    break;

                case 2:
                    List<Veterinario> listav = controller.ListarVeterinarios();
                    if (listav.isEmpty()) {
                        System.out.println("No hay Veterinarios registrados.");
                    } else {
                        System.out.println("--------- Lista de Veterinarios --------");
                        for (Veterinario vet : listav) {
                            System.out.println("ID: " + vet.getId());
                            System.out.println("Nombre: " + vet.getNombreCompleto());
                            System.out.println("DNI: " + vet.getDocumentoIdentidad());
                            System.out.println("Licencia: " + vet.getLicenciaProfesional());
                            System.out.println("Especialidad: " + vet.getEspecialidad());
                            System.out.println("Telefono: " + vet.getTelefono());
                            System.out.println("Email: " + vet.getEmail());
                            System.out.println("Fecha de contratacion: " + vet.getFechaContratacion());
                            System.out.println("Estado: " + vet.isActivo());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el ID del veterinario cuyo estado desea actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    controller.actualizarEstado(id);
                    System.out.println("Estado actualizado correctamente.");
                    break;
                case 4:
                    System.out.println("Ingrese el ID del veterinario a buscar: ");
                    int veterinario_id = sc.nextInt();
                    sc.nextLine();
                    Veterinario ve = controller.buscaPorId(veterinario_id);
                    if (ve != null) {
                        System.out.println("\n=== Información del Veterinario ===");
                        System.out.println("ID: " + ve.getId());
                        System.out.println("Nombre: " + ve.getNombreCompleto());
                        System.out.println("Documento: " + ve.getDocumentoIdentidad());
                        System.out.println("Teléfono: " + ve.getTelefono());
                        System.out.println("Email: " + ve.getEmail());
                        System.out.println("Activo: " + (ve.isActivo() ? "Sí" : "No"));
                    } else {
                        System.out.println(" No se encontró ningún veterinario con ese ID.");
                    }
                    break;
                            case 5:
                    System.out.println("Volviendo al menú principal...");
                    return; 
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }
}

