/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author camper
 */
public class Inventario {
    private int id;
    private String nombre_producto;
    private int producto_tipo_id;
    private String descripcion;
    private String fabricante;
    private Integer proveedor_id;
    private String lote;
    private int cantidad_stock;
    private int stock_minimo;
    private String unidad_medida;
    private LocalDate fecha_vencimiento;
    private double precio_compra;
    private double precio_venta;
    private boolean requiere_receta;
    private boolean activo;
    private LocalDateTime fecha_registro;
    
    // Constructor completo
    public Inventario(int id, String nombre_producto, int producto_tipo_id, String descripcion, String fabricante, Integer proveedor_id, String lote, int cantidad_stock, int stock_minimo, String unidad_medida, LocalDate fecha_vencimiento, double precio_compra, double precio_venta, boolean requiere_receta, boolean activo, LocalDateTime fecha_registro) {
        this.id = id;
        this.nombre_producto = nombre_producto;
        this.producto_tipo_id = producto_tipo_id;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedor_id = proveedor_id;
        this.lote = lote;
        this.cantidad_stock = cantidad_stock;
        this.stock_minimo = stock_minimo;
        this.unidad_medida = unidad_medida;
        this.fecha_vencimiento = fecha_vencimiento;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.requiere_receta = requiere_receta;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
    }
    
    // Constructor vacío
    public Inventario() {}
    
    // Constructor sin ID para nuevos registros
    public Inventario(String nombre_producto, int producto_tipo_id, String descripcion, String fabricante, Integer proveedor_id, String lote, int cantidad_stock, int stock_minimo, String unidad_medida, LocalDate fecha_vencimiento, double precio_compra, double precio_venta, boolean requiere_receta, boolean activo, LocalDateTime fecha_registro) {
        this.nombre_producto = nombre_producto;
        this.producto_tipo_id = producto_tipo_id;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.proveedor_id = proveedor_id;
        this.lote = lote;
        this.cantidad_stock = cantidad_stock;
        this.stock_minimo = stock_minimo;
        this.unidad_medida = unidad_medida;
        this.fecha_vencimiento = fecha_vencimiento;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.requiere_receta = requiere_receta;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
    }
    
    // Getters
    public int getId() { return id; }
    public String getNombreProducto() { return nombre_producto; }
    public int getProductoTipoId() { return producto_tipo_id; }
    public String getDescripcion() { return descripcion; }
    public String getFabricante() { return fabricante; }
    public Integer getProveedorId() { return proveedor_id; }
    public String getLote() { return lote; }
    public int getCantidadStock() { return cantidad_stock; }
    public int getStockMinimo() { return stock_minimo; }
    public String getUnidadMedida() { return unidad_medida; }
    public LocalDate getFechaVencimiento() { return fecha_vencimiento; }
    public double getPrecioCompra() { return precio_compra; }
    public double getPrecioVenta() { return precio_venta; }
    public boolean isRequiereReceta() { return requiere_receta; }
    public boolean isActivo() { return activo; }
    public LocalDateTime getFechaRegistro() { return fecha_registro; }
    
    // Método para verificar si está próximo a vencer (30 días)
    public boolean estaProximoVencer() {
        if (fecha_vencimiento == null) return false;
        return LocalDate.now().plusDays(30).isAfter(fecha_vencimiento) && 
               LocalDate.now().isBefore(fecha_vencimiento);
    }
    
    // Método para verificar si está vencido
    public boolean estaVencido() {
        if (fecha_vencimiento == null) return false;
        return LocalDate.now().isAfter(fecha_vencimiento);
    }
    
    // Método para verificar si tiene stock bajo
    public boolean tieneStockBajo() {
        return cantidad_stock <= stock_minimo;
    }
}