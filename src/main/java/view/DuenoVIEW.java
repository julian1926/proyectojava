/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DuenoController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Dueno;

public class DuenoVIEW {
    private DuenoController controller;
    private Scanner sc;
    
    public DuenoVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new DuenoController();
        
        do {            
            System.out.println("\n--------- Gestión de Dueños --------");
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
                    System.out.println("Dirección: ");
                    String direccion = sc.nextLine();
                    System.out.println("Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Contacto de emergencia: ");
                    String ConEmergencia = sc.nextLine();

                    LocalDateTime fechaRegistro = LocalDateTime.now();
                    boolean estado = true;
                    Dueno d = new Dueno(nombre, DNI, direccion, telefono, email, ConEmergencia, fechaRegistro, estado);
                    controller.agregarDueno(d);
                    break;
                    
                case 2:
                    List<Dueno> lista = controller.ListarDuenos();
                    if (lista.isEmpty()) {
                        System.out.println("No hay dueños registrados.");
                    } else {
                        System.out.println("--------- Lista de Dueños --------");
                        for (Dueno dueno : lista) {
                            System.out.println("ID: " + dueno.getId());
                            System.out.println("Nombre: " + dueno.getNombreCompleto());
                            System.out.println("Teléfono: " + dueno.getTelefono());
                            System.out.println("DNI: " + dueno.getDocumentoIdentidad());
                            System.out.println("Dirección: " + dueno.getDireccion());
                            System.out.println("Email: " + dueno.getEmail());
                            System.out.println("Contacto de emergencia: " + dueno.getContactoEmergencia());
                            System.out.println("Fecha de registro: " + dueno.getFechaRegistro());
                            System.out.println("Estado: " + dueno.getEstado());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Ingrese el ID del dueño cuyo estado desea actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    controller.actualizarEstado(id);
                    System.out.println("Estado actualizado correctamente.");
                    break;
                    
                case 4:
                    System.out.println("Ingrese el ID del dueño a buscar: ");
                    int dueno_id = sc.nextInt();
                    sc.nextLine();
                    Dueno dueno = controller.buscarPorId(dueno_id);
                    if (dueno != null) {
                        System.out.println("\n=== Información del Dueño ===");
                        System.out.println("ID: " + dueno.getId());
                        System.out.println("Nombre: " + dueno.getNombreCompleto());
                        System.out.println("Documento: " + dueno.getDocumentoIdentidad());
                        System.out.println("Teléfono: " + dueno.getTelefono());
                        System.out.println("Email: " + dueno.getEmail());
                        System.out.println("Activo: " + (dueno.getEstado() ? "Sí" : "No"));
                    } else {
                        System.out.println(" No se encontró ningún dueño con ese ID.");
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
