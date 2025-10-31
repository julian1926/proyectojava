/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ProductoTipoController;
import java.util.List;
import java.util.Scanner;
import model.ProductoTipo;

public class ProductoTipoVIEW {
    private ProductoTipoController controller;
    private Scanner sc;
    
    public ProductoTipoVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new ProductoTipoController();
        
        do {            
            System.out.println("\n--------- GESTIÓN DE TIPOS DE PRODUCTO ---------");
            System.out.println("1. Agregar tipo de producto");
            System.out.println("2. Listar tipos de producto");
            System.out.println("3. Buscar tipo por ID");
            System.out.println("4. Buscar tipo por nombre");
            System.out.println("5. Actualizar tipo de producto");
            System.out.println("6. Eliminar tipo de producto");
            System.out.println("7. Ver tipos disponibles");
            System.out.println("8. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    agregarProductoTipo();
                    break;
                    
                case 2:
                    listarProductoTipos();
                    break;
                    
                case 3:
                    buscarPorId();
                    break;
                    
                case 4:
                    buscarPorNombre();
                    break;
                    
                case 5:
                    actualizarProductoTipo();
                    break;
                    
                case 6:
                    eliminarProductoTipo();
                    break;
                    
                case 7:
                    controller.mostrarTiposDisponibles();
                    break;

                case 8:
                    System.out.println("Volviendo al menú principal...");
                    return; 
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }
    
    private void agregarProductoTipo() {
        System.out.println("\n--- AGREGAR NUEVO TIPO DE PRODUCTO ---");
        System.out.print("Nombre del tipo: ");
        String nombre = sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        ProductoTipo pt = new ProductoTipo(nombre, descripcion);
        controller.agregarProductoTipo(pt);
    }
    
    private void listarProductoTipos() {
        List<ProductoTipo> lista = controller.listarProductoTipos();
        if (lista.isEmpty()) {
            System.out.println("No hay tipos de producto registrados.");
        } else {
            System.out.println("\n--- LISTA DE TIPOS DE PRODUCTO ---");
            for (ProductoTipo pt : lista) {
                System.out.println("ID: " + pt.getId());
                System.out.println("Nombre: " + pt.getNombre());
                System.out.println("Descripción: " + pt.getDescripcion());
                System.out.println("-----------------------------------");
            }
        }
    }
    
    private void buscarPorId() {
        System.out.print("Ingrese el ID del tipo de producto a buscar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ProductoTipo pt = controller.buscarPorId(id);
        if (pt != null) {
            System.out.println("\n=== INFORMACIÓN DEL TIPO DE PRODUCTO ===");
            System.out.println("ID: " + pt.getId());
            System.out.println("Nombre: " + pt.getNombre());
            System.out.println("Descripción: " + pt.getDescripcion());
        } else {
            System.out.println("No se encontró ningún tipo de producto con ese ID.");
        }
    }
    
    private void buscarPorNombre() {
        System.out.print("Ingrese el nombre del tipo de producto a buscar: ");
        String nombre = sc.nextLine();
        ProductoTipo pt = controller.buscarPorNombre(nombre);
        if (pt != null) {
            System.out.println("\n=== INFORMACIÓN DEL TIPO DE PRODUCTO ===");
            System.out.println("ID: " + pt.getId());
            System.out.println("Nombre: " + pt.getNombre());
            System.out.println("Descripción: " + pt.getDescripcion());
        } else {
            System.out.println("No se encontró ningún tipo de producto con ese nombre.");
        }
    }
    
    private void actualizarProductoTipo() {
        System.out.print("Ingrese el ID del tipo de producto a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        ProductoTipo ptExistente = controller.buscarPorId(id);
        if (ptExistente == null) {
            System.out.println("No se encontró el tipo de producto.");
            return;
        }
        
        System.out.println("Tipo actual: " + ptExistente.getNombre() + " - " + ptExistente.getDescripcion());
        System.out.print("Nuevo nombre (enter para mantener actual): ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Nueva descripción (enter para mantener actual): ");
        String nuevaDescripcion = sc.nextLine();
        
        // Mantener valores actuales si no se ingresan nuevos
        if (!nuevoNombre.isEmpty()) {
            ptExistente.setNombre(nuevoNombre);
        }
        if (!nuevaDescripcion.isEmpty()) {
            ptExistente.setDescripcion(nuevaDescripcion);
        }
        
        controller.actualizarProductoTipo(ptExistente);
        System.out.println("Tipo de producto actualizado correctamente.");
    }
    
    private void eliminarProductoTipo() {
        System.out.print("Ingrese el ID del tipo de producto a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        ProductoTipo pt = controller.buscarPorId(id);
        if (pt != null) {
            System.out.println("¿Está seguro de eliminar: " + pt.getNombre() + "? (s/n)");
            String confirmacion = sc.nextLine();
            if (confirmacion.equalsIgnoreCase("s")) {
                controller.eliminarProductoTipo(id);
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("No se encontró el tipo de producto.");
        }
    }
}