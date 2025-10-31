/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.InventarioDAO;
import java.util.List;
import model.Inventario;
import model.ProductoTipo;

/**
 *
 * @author sd
 */
public class InventarioController {
    private InventarioDAO inventarioDAO;
    
    public InventarioController(){
        this.inventarioDAO = new InventarioDAO();
    }

    // Método para agregar un producto al inventario
    public void agregarInventario(Inventario inv){
        inventarioDAO.agregarInventario(inv);
        System.out.println("Producto agregado al inventario");
    }
    
    // Método para listar inventario
    public List<Inventario> listarInventario(){
        return inventarioDAO.listarInventario();
    }
    
    // Método para actualizar estado
    public void actualizarEstado(int id){
        inventarioDAO.actualizarEstado(id);
    }
    
    // Método para buscar producto por id
    public Inventario buscarPorId(int id){
        return inventarioDAO.buscarPorId(id);
    }
    
    // Método para obtener alertas de stock bajo
    public List<Inventario> obtenerAlertasStockBajo(){
        return inventarioDAO.buscarStockBajo();
    }
    
    // Método para obtener productos próximos a vencer
    public List<Inventario> obtenerProximosVencer(){
        return inventarioDAO.buscarProximosVencer();
    }
    
    // Método para obtener productos vencidos
    public List<Inventario> obtenerProductosVencidos(){
        return inventarioDAO.buscarVencidos();
    }
    
    // Método para actualizar stock
    public void actualizarStock(int id, int nuevaCantidad){
        inventarioDAO.actualizarStock(id, nuevaCantidad);
    }
    
    // Método para buscar por tipo
    public List<Inventario> buscarPorTipo(int tipoId){
        return inventarioDAO.buscarPorTipo(tipoId);
    }
    
    public void mostrarTiposParaSeleccion() {
        ProductoTipoController tipoController = new ProductoTipoController();
        List<ProductoTipo> tipos = tipoController.listarProductoTipos();

        if (tipos.isEmpty()) {
            System.out.println("No hay tipos de producto registrados.");
            return;
        }

        System.out.println("\n--- SELECCIONE UN TIPO DE PRODUCTO ---");
        for (ProductoTipo tipo : tipos) {
            System.out.println("ID: " + tipo.getId() + " - " + tipo.getNombre() + 
                             " - " + tipo.getDescripcion());
        }
    }

    // Método para generar reporte completo de alertas
    public void generarReporteAlertas() {
        System.out.println("\n=== REPORTE DE ALERTAS DEL INVENTARIO ===");
        
        List<Inventario> stockBajo = obtenerAlertasStockBajo();
        if (!stockBajo.isEmpty()) {
            System.out.println("\n⚠️  PRODUCTOS CON STOCK BAJO:");
            for (Inventario inv : stockBajo) {
                System.out.println("• " + inv.getNombreProducto() + " - Stock: " + inv.getCantidadStock() + " (Mínimo: " + inv.getStockMinimo() + ")");
            }
        }
        
        List<Inventario> proximosVencer = obtenerProximosVencer();
        if (!proximosVencer.isEmpty()) {
            System.out.println("\n📅  PRODUCTOS PRÓXIMOS A VENCER (30 días):");
            for (Inventario inv : proximosVencer) {
                System.out.println("• " + inv.getNombreProducto() + " - Vence: " + inv.getFechaVencimiento() + " - Lote: " + inv.getLote());
            }
        }
        
        List<Inventario> vencidos = obtenerProductosVencidos();
        if (!vencidos.isEmpty()) {
            System.out.println("\n❌  PRODUCTOS VENCIDOS (NO USAR):");
            for (Inventario inv : vencidos) {
                System.out.println("• " + inv.getNombreProducto() + " - Venció: " + inv.getFechaVencimiento() + " - Lote: " + inv.getLote());
            }
        }
        
        if (stockBajo.isEmpty() && proximosVencer.isEmpty() && vencidos.isEmpty()) {
            System.out.println("✅ No hay alertas pendientes.");
        }
    }
}