/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.InventarioController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Inventario;

public class InventarioVIEW {
    private InventarioController controller;
    private Scanner sc;
    
    public InventarioVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new InventarioController();
        
        do {            
            System.out.println("\n--------- GESTIÓN DE INVENTARIO CLÍNICA ---------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar inventario");
            System.out.println("3. Actualizar estado producto");
            System.out.println("4. Buscar producto por ID");
            System.out.println("5. Ver alertas de stock bajo");
            System.out.println("6. Ver productos próximos a vencer");
            System.out.println("7. Ver productos vencidos");
            System.out.println("8. Reporte completo de alertas");
            System.out.println("9. Actualizar stock");
            System.out.println("10. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                    
                case 2:
                    listarInventario();
                    break;
                    
                case 3:
                    actualizarEstado();
                    break;
                    
                case 4:
                    buscarPorId();
                    break;
                    
                case 5:
                    verStockBajo();
                    break;
                    
                case 6:
                    verProximosVencer();
                    break;
                    
                case 7:
                    verVencidos();
                    break;
                    
                case 8:
                    controller.generarReporteAlertas();
                    break;
                    
                case 9:
                    actualizarStock();
                    break;

                case 10:
                    System.out.println("Volviendo al menú principal...");
                    return; 
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }
    
    private void agregarProducto() {
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO AL INVENTARIO ---");
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        
        System.out.print("Tipo de producto (ID): ");
        int tipoId = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Fabricante: ");
        String fabricante = sc.nextLine();
        
        System.out.print("ID del proveedor (0 si no tiene): ");
        int proveedorId = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Número de lote: ");
        String lote = sc.nextLine();
        
        System.out.print("Cantidad en stock: ");
        int stock = sc.nextInt();
        
        System.out.print("Stock mínimo para alerta: ");
        int stockMinimo = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Unidad de medida: ");
        String unidad = sc.nextLine();
        
        System.out.print("Fecha de vencimiento (YYYY-MM-DD, enter para omitir): ");
        String fechaVencStr = sc.nextLine();
        LocalDate fechaVencimiento = fechaVencStr.isEmpty() ? null : LocalDate.parse(fechaVencStr);
        
        System.out.print("Precio de compra: ");
        double precioCompra = sc.nextDouble();
        
        System.out.print("Precio de venta: ");
        double precioVenta = sc.nextDouble();
        
        System.out.print("¿Requiere receta? (true/false): ");
        boolean requiereReceta = sc.nextBoolean();
        sc.nextLine();

        LocalDateTime fechaRegistro = LocalDateTime.now();
        boolean activo = true;
        
        Inventario inv = new Inventario(nombre, tipoId, descripcion, fabricante, 
                                      proveedorId == 0 ? null : proveedorId, lote, 
                                      stock, stockMinimo, unidad, fechaVencimiento, 
                                      precioCompra, precioVenta, requiereReceta, 
                                      activo, fechaRegistro);
        controller.agregarInventario(inv);
    }
    
    private void listarInventario() {
        List<Inventario> lista = controller.listarInventario();
        if (lista.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("\n--- INVENTARIO COMPLETO ---");
            for (Inventario inv : lista) {
                System.out.println("ID: " + inv.getId());
                System.out.println("Producto: " + inv.getNombreProducto());
                System.out.println("Stock: " + inv.getCantidadStock() + " " + inv.getUnidadMedida() + 
                                 " (Mín: " + inv.getStockMinimo() + ")");
                System.out.println("Precio: $" + inv.getPrecioVenta());
                if (inv.getFechaVencimiento() != null) {
                    System.out.println("Vence: " + inv.getFechaVencimiento());
                    if (inv.estaVencido()) {
                        System.out.println("⚠️  VENCIDO - NO USAR");
                    } else if (inv.estaProximoVencer()) {
                        System.out.println("⚠️  Próximo a vencer");
                    }
                }
                if (inv.tieneStockBajo()) {
                    System.out.println("⚠️  STOCK BAJO");
                }
                System.out.println("-----------------------------------");
            }
        }
    }
    
    private void actualizarEstado() {
        System.out.print("Ingrese el ID del producto cuyo estado desea actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        controller.actualizarEstado(id);
        System.out.println("Estado actualizado correctamente.");
    }
    
    private void buscarPorId() {
        System.out.print("Ingrese el ID del producto a buscar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Inventario inv = controller.buscarPorId(id);
        if (inv != null) {
            System.out.println("\n=== INFORMACIÓN DEL PRODUCTO ===");
            System.out.println("ID: " + inv.getId());
            System.out.println("Nombre: " + inv.getNombreProducto());
            System.out.println("Descripción: " + inv.getDescripcion());
            System.out.println("Fabricante: " + inv.getFabricante());
            System.out.println("Lote: " + inv.getLote());
            System.out.println("Stock: " + inv.getCantidadStock() + " " + inv.getUnidadMedida());
            System.out.println("Stock mínimo: " + inv.getStockMinimo());
            System.out.println("Precio compra: $" + inv.getPrecioCompra());
            System.out.println("Precio venta: $" + inv.getPrecioVenta());
            System.out.println("Requiere receta: " + (inv.isRequiereReceta() ? "Sí" : "No"));
            System.out.println("Activo: " + (inv.isActivo() ? "Sí" : "No"));
            if (inv.getFechaVencimiento() != null) {
                System.out.println("Fecha vencimiento: " + inv.getFechaVencimiento());
            }
        } else {
            System.out.println("No se encontró ningún producto con ese ID.");
        }
    }
    
    private void verStockBajo() {
        List<Inventario> stockBajo = controller.obtenerAlertasStockBajo();
        if (stockBajo.isEmpty()) {
            System.out.println("No hay productos con stock bajo.");
        } else {
            System.out.println("\n⚠️  PRODUCTOS CON STOCK BAJO:");
            for (Inventario inv : stockBajo) {
                System.out.println("• " + inv.getNombreProducto() + " - Stock: " + 
                                 inv.getCantidadStock() + " (Mínimo: " + inv.getStockMinimo() + ")");
            }
        }
    }
    
    private void verProximosVencer() {
        List<Inventario> proximos = controller.obtenerProximosVencer();
        if (proximos.isEmpty()) {
            System.out.println("No hay productos próximos a vencer.");
        } else {
            System.out.println("\n📅  PRODUCTOS PRÓXIMOS A VENCER (30 días):");
            for (Inventario inv : proximos) {
                System.out.println("• " + inv.getNombreProducto() + " - Vence: " + 
                                 inv.getFechaVencimiento() + " - Lote: " + inv.getLote());
            }
        }
    }
    
    private void verVencidos() {
        List<Inventario> vencidos = controller.obtenerProductosVencidos();
        if (vencidos.isEmpty()) {
            System.out.println("No hay productos vencidos.");
        } else {
            System.out.println("\n❌  PRODUCTOS VENCIDOS (NO USAR):");
            for (Inventario inv : vencidos) {
                System.out.println("• " + inv.getNombreProducto() + " - Venció: " + 
                                 inv.getFechaVencimiento() + " - Lote: " + inv.getLote());
            }
        }
    }
    
    private void actualizarStock() {
        System.out.print("Ingrese el ID del producto: ");
        int id = sc.nextInt();
        System.out.print("Ingrese la nueva cantidad en stock: ");
        int nuevaCantidad = sc.nextInt();
        sc.nextLine();
        controller.actualizarStock(id, nuevaCantidad);
    }
}