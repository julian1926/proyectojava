package view;

import controller.ProvedorController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Provedor;

public class ProvedorVIEW {
    private ProvedorController controller;
    private Scanner sc;
    
    public ProvedorVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new ProvedorController();
        
        do {            
            System.out.println("\n--------- Gestión de Proveedores --------");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar estado");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Buscar por nombre");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    System.out.println("Nombre de la empresa: ");
                    String nombreEmpresa = sc.nextLine();
                    System.out.println("Persona de contacto: ");
                    String contacto = sc.nextLine();
                    System.out.println("Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Dirección: ");
                    String direccion = sc.nextLine();
                    System.out.println("Sitio web: ");
                    String sitioWeb = sc.nextLine();

                    LocalDateTime fechaRegistro = LocalDateTime.now();
                    boolean activo = true;
                    Provedor p = new Provedor(nombreEmpresa, contacto, telefono, email, direccion, sitioWeb, activo, fechaRegistro);
                    controller.agregarProvedor(p);
                    break;
                    
                case 2:
                    List<Provedor> lista = controller.listarProvedores();
                    if (lista.isEmpty()) {
                        System.out.println("No hay proveedores registrados.");
                    } else {
                        System.out.println("--------- Lista de Proveedores --------");
                        for (Provedor provedor : lista) {
                            System.out.println("ID: " + provedor.getId());
                            System.out.println("Empresa: " + provedor.getNombreEmpresa());
                            System.out.println("Contacto: " + provedor.getContacto());
                            System.out.println("Teléfono: " + provedor.getTelefono());
                            System.out.println("Email: " + provedor.getEmail());
                            System.out.println("Dirección: " + provedor.getDireccion());
                            System.out.println("Sitio web: " + provedor.getSitioWeb());
                            System.out.println("Fecha de registro: " + provedor.getFechaRegistro());
                            System.out.println("Activo: " + provedor.isActivo());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Ingrese el ID del proveedor cuyo estado desea actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    controller.actualizarEstado(id);
                    System.out.println("Estado actualizado correctamente.");
                    break;
                    
                case 4:
                    System.out.println("Ingrese el ID del proveedor a buscar: ");
                    int provedor_id = sc.nextInt();
                    sc.nextLine();
                    Provedor provedor = controller.buscarPorId(provedor_id);
                    if (provedor != null) {
                        System.out.println("\n=== Información del Proveedor ===");
                        System.out.println("ID: " + provedor.getId());
                        System.out.println("Empresa: " + provedor.getNombreEmpresa());
                        System.out.println("Contacto: " + provedor.getContacto());
                        System.out.println("Teléfono: " + provedor.getTelefono());
                        System.out.println("Email: " + provedor.getEmail());
                        System.out.println("Dirección: " + provedor.getDireccion());
                        System.out.println("Sitio web: " + provedor.getSitioWeb());
                        System.out.println("Activo: " + (provedor.isActivo() ? "Sí" : "No"));
                    } else {
                        System.out.println("No se encontró ningún proveedor con ese ID.");
                    }
                    break;
                    
                case 5:
                    System.out.println("Ingrese el nombre o parte del nombre de la empresa a buscar: ");
                    String nombreBusqueda = sc.nextLine();
                    List<Provedor> proveedoresEncontrados = controller.buscarPorNombre(nombreBusqueda);
                    if (proveedoresEncontrados.isEmpty()) {
                        System.out.println("No se encontraron proveedores con ese nombre.");
                    } else {
                        System.out.println("\n=== Proveedores Encontrados ===");
                        for (Provedor prov : proveedoresEncontrados) {
                            System.out.println("ID: " + prov.getId());
                            System.out.println("Empresa: " + prov.getNombreEmpresa());
                            System.out.println("Contacto: " + prov.getContacto());
                            System.out.println("Teléfono: " + prov.getTelefono());
                            System.out.println("Activo: " + (prov.isActivo() ? "Sí" : "No"));
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Volviendo al menú principal...");
                    return; 
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }
}