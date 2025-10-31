/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductoTipoDAO;
import java.util.List;
import model.ProductoTipo;

/**
 *
 * @author sd
 */
public class ProductoTipoController {
    private ProductoTipoDAO productoTipoDAO;
    
    public ProductoTipoController(){
        this.productoTipoDAO = new ProductoTipoDAO();
    }

    // Método para agregar un tipo de producto
    public void agregarProductoTipo(ProductoTipo pt){
        // Validar que el nombre no exista
        if (productoTipoDAO.existeNombre(pt.getNombre())) {
            System.out.println("Error: Ya existe un tipo de producto con ese nombre.");
            return;
        }
        productoTipoDAO.agregarProductoTipo(pt);
        System.out.println("Tipo de producto agregado");
    }
    
    // Método para listar tipos de producto
    public List<ProductoTipo> listarProductoTipos(){
        return productoTipoDAO.listarProductoTipos();
    }
    
    // Método para buscar por ID
    public ProductoTipo buscarPorId(int id){
        return productoTipoDAO.buscarPorId(id);
    }
    
    // Método para buscar por nombre
    public ProductoTipo buscarPorNombre(String nombre){
        return productoTipoDAO.buscarPorNombre(nombre);
    }
    
    // Método para actualizar tipo de producto
    public void actualizarProductoTipo(ProductoTipo pt){
        productoTipoDAO.actualizarProductoTipo(pt);
    }
    
    // Método para eliminar tipo de producto
    public void eliminarProductoTipo(int id){
        productoTipoDAO.eliminarProductoTipo(id);
    }
    
    // Método para verificar si existe nombre
    public boolean existeNombre(String nombre) {
        return productoTipoDAO.existeNombre(nombre);
    }
    
    // Método para mostrar tipos disponibles
    public void mostrarTiposDisponibles() {
        List<ProductoTipo> tipos = listarProductoTipos();
        if (tipos.isEmpty()) {
            System.out.println("No hay tipos de producto registrados.");
        } else {
            System.out.println("\n--- TIPOS DE PRODUCTO DISPONIBLES ---");
            for (ProductoTipo tipo : tipos) {
                System.out.println("ID: " + tipo.getId() + " - " + tipo.getNombre() + 
                                 " - " + tipo.getDescripcion());
            }
        }
    }
}