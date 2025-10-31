/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.InventarioController;
import controller.ProductoTipoController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Inventario;
import model.ProductoTipo;
import model.Provedor;

public class InventarioVIEW {
    private InventarioController controller;
    private ProductoTipoController tipoController;
    private Scanner sc;
    
    public InventarioVIEW() {
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new InventarioController();
        tipoController = new ProductoTipoController();
        
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
            System.out.println("10. Buscar productos por tipo");
            System.out.println("11. Volver al menú principal");
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
                    buscarPorTipo();
                    break;

                case 11:
                    System.out.println("Volviendo al menú principal...");
                    return; 
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }
    
    private void agregarProducto() {
     System.out.println("\n--- AGREGAR NUEVO PRODUCTO AL INVENTARIO ---");

     // Mostrar tipos de producto disponibles
     System.out.println("\n--- TIPOS DE PRODUCTO DISPONIBLES ---");
     List<ProductoTipo> tipos = tipoController.listarProductoTipos();
     if (tipos.isEmpty()) {
         System.out.println("No hay tipos de producto registrados. Debe crear tipos primero.");
         return;
     }

     for (ProductoTipo tipo : tipos) {
         System.out.println("ID: " + tipo.getId() + " - " + tipo.getNombre() + 
                          " - " + tipo.getDescripcion());
     }

     System.out.print("\nSeleccione el ID del tipo de producto: ");
     int tipoId = sc.nextInt();
     sc.nextLine();

     // Validar que el tipo existe
     ProductoTipo tipoSeleccionado = tipoController.buscarPorId(tipoId);
     if (tipoSeleccionado == null) {
         System.out.println("Error: El ID de tipo de producto no existe.");
         return;
     }

     System.out.println("Tipo seleccionado: " + tipoSeleccionado.getNombre());

     System.out.print("Nombre del producto: ");
     String nombre = sc.nextLine();

     System.out.print("Descripción: ");
     String descripcion = sc.nextLine();

     System.out.print("Fabricante: ");
     String fabricante = sc.nextLine();

     // CORRECCIÓN: Mostrar proveedores disponibles
     System.out.println("\n--- PROVEEDORES REGISTRADOS ---");
     // Necesitas importar el controller de proveedores
     controller.ProvedorController provedorController = new controller.ProvedorController();
     List<model.Provedor> proveedores = provedorController.listarProvedores();

     if (proveedores.isEmpty()) {
         System.out.println("No hay proveedores registrados. Puede usar 0 para ninguno.");
     } else {
         for (model.Provedor p : proveedores) {
             System.out.println("ID: " + p.getId() + " | Empresa: " + p.getNombreEmpresa());
         }
     }

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
                // Obtener el nombre del tipo de producto
                String nombreTipo = obtenerNombreTipo(inv.getProductoTipoId());
                
                System.out.println("ID: " + inv.getId());
                System.out.println("Producto: " + inv.getNombreProducto());
                System.out.println("Tipo: " + nombreTipo);
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
    
    private void buscarPorTipo() {
        System.out.println("\n--- BUSCAR PRODUCTOS POR TIPO ---");
        
        // Mostrar tipos disponibles
        System.out.println("Tipos de producto disponibles:");
        List<ProductoTipo> tipos = tipoController.listarProductoTipos();
        for (ProductoTipo tipo : tipos) {
            System.out.println("ID: " + tipo.getId() + " - " + tipo.getNombre());
        }
        
        System.out.print("Seleccione el ID del tipo: ");
        int tipoId = sc.nextInt();
        sc.nextLine();
        
        List<Inventario> productos = controller.buscarPorTipo(tipoId);
        if (productos.isEmpty()) {
            System.out.println("No hay productos de este tipo en el inventario.");
        } else {
            ProductoTipo tipo = tipoController.buscarPorId(tipoId);
            System.out.println("\n--- PRODUCTOS DEL TIPO: " + tipo.getNombre() + " ---");
            for (Inventario inv : productos) {
                System.out.println("ID: " + inv.getId() + " - " + inv.getNombreProducto() + 
                                 " - Stock: " + inv.getCantidadStock() + " " + inv.getUnidadMedida() +
                                 " - Precio: $" + inv.getPrecioVenta());
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
            String nombreTipo = obtenerNombreTipo(inv.getProductoTipoId());
            
            System.out.println("\n=== INFORMACIÓN DEL PRODUCTO ===");
            System.out.println("ID: " + inv.getId());
            System.out.println("Nombre: " + inv.getNombreProducto());
            System.out.println("Tipo: " + nombreTipo);
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
                System.out.println("Estado: " + (inv.estaVencido() ? "VENCIDO" : 
                                              inv.estaProximoVencer() ? "PRÓXIMO A VENCER" : "VIGENTE"));
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
                String nombreTipo = obtenerNombreTipo(inv.getProductoTipoId());
                System.out.println("• " + inv.getNombreProducto() + " [" + nombreTipo + "] - Stock: " + 
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
                String nombreTipo = obtenerNombreTipo(inv.getProductoTipoId());
                System.out.println("• " + inv.getNombreProducto() + " [" + nombreTipo + "] - Vence: " + 
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
                String nombreTipo = obtenerNombreTipo(inv.getProductoTipoId());
                System.out.println("• " + inv.getNombreProducto() + " [" + nombreTipo + "] - Venció: " + 
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
    
    // Método auxiliar para obtener el nombre del tipo
    private String obtenerNombreTipo(int tipoId) {
        ProductoTipo tipo = tipoController.buscarPorId(tipoId);
        return tipo != null ? tipo.getNombre() : "Tipo desconocido (ID: " + tipoId + ")";
    }
}