/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Inventario;
import util.ConexionDB;

/**
 *
 * @author camper
 */
public class InventarioDAO {
    
    // Método para agregar producto al inventario
    public void agregarInventario(Inventario inv) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, proveedor_id, lote, cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento, precio_compra, precio_venta, requiere_receta) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, inv.getNombreProducto());
            ps.setInt(2, inv.getProductoTipoId());
            ps.setString(3, inv.getDescripcion());
            ps.setString(4, inv.getFabricante());
            if (inv.getProveedorId() != null) {
                ps.setInt(5, inv.getProveedorId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setString(6, inv.getLote());
            ps.setInt(7, inv.getCantidadStock());
            ps.setInt(8, inv.getStockMinimo());
            ps.setString(9, inv.getUnidadMedida());
            if (inv.getFechaVencimiento() != null) {
                ps.setDate(10, java.sql.Date.valueOf(inv.getFechaVencimiento()));
            } else {
                ps.setNull(10, java.sql.Types.DATE);
            }
            ps.setDouble(11, inv.getPrecioCompra());
            ps.setDouble(12, inv.getPrecioVenta());
            ps.setBoolean(13, inv.isRequiereReceta());
            
            ps.executeUpdate();
            System.out.println("Producto agregado al inventario exitosamente!!!!!");
            
        } catch (SQLException e) {
            System.out.println("No se registro el producto en el inventario: " + e.getMessage());
        }
    }
    
    // Método para listar inventario
    public List<Inventario> listarInventario() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE activo = true";
        try (Connection con = ConexionDB.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Inventario inv = crearInventarioDesdeResultSet(rs);
                lista.add(inv);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar inventario: " + e.getMessage());
        }
        return lista;
    }
    
    // Método para buscar por ID
    public Inventario buscarPorId(int id) {
        Inventario inventario = null;
        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inventario = crearInventarioDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto por ID: " + e.getMessage());
        }

        return inventario;
    }
    
    // Método para actualizar estado
    public void actualizarEstado(int id) {
        String sql = "UPDATE inventario SET activo = NOT activo WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado del producto actualizado correctamente");
            } else {
                System.out.println("No se encontro el producto");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del producto: " + e.getMessage());
        }
    }
    
    // ALERTA: Productos con stock bajo
    public List<Inventario> buscarStockBajo() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE cantidad_stock <= stock_minimo AND activo = true";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventario inv = crearInventarioDesdeResultSet(rs);
                lista.add(inv);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar productos con stock bajo: " + e.getMessage());
        }
        return lista;
    }
    
    // ALERTA: Productos próximos a vencer (30 días)
    public List<Inventario> buscarProximosVencer() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) AND activo = true";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventario inv = crearInventarioDesdeResultSet(rs);
                lista.add(inv);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar productos próximos a vencer: " + e.getMessage());
        }
        return lista;
    }
    
    // ALERTA: Productos vencidos
    public List<Inventario> buscarVencidos() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento < CURDATE() AND activo = true";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventario inv = crearInventarioDesdeResultSet(rs);
                lista.add(inv);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar productos vencidos: " + e.getMessage());
        }
        return lista;
    }
    
    // Método para actualizar stock
    public void actualizarStock(int id, int nuevaCantidad) {
        String sql = "UPDATE inventario SET cantidad_stock = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, id);
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Stock actualizado correctamente");
            } else {
                System.out.println("No se encontro el producto");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }
    
    // Método para buscar por tipo de producto
    public List<Inventario> buscarPorTipo(int tipoId) {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE producto_tipo_id = ? AND activo = true";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, tipoId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Inventario inv = crearInventarioDesdeResultSet(rs);
                lista.add(inv);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar productos por tipo: " + e.getMessage());
        }
        return lista;
    }
    
    // Método auxiliar para crear objeto Inventario desde ResultSet
    private Inventario crearInventarioDesdeResultSet(ResultSet rs) throws SQLException {
        return new Inventario(
            rs.getInt("id"),
            rs.getString("nombre_producto"),
            rs.getInt("producto_tipo_id"),
            rs.getString("descripcion"),
            rs.getString("fabricante"),
            rs.getObject("proveedor_id") != null ? rs.getInt("proveedor_id") : null,
            rs.getString("lote"),
            rs.getInt("cantidad_stock"),
            rs.getInt("stock_minimo"),
            rs.getString("unidad_medida"),
            rs.getDate("fecha_vencimiento") != null ? rs.getDate("fecha_vencimiento").toLocalDate() : null,
            rs.getDouble("precio_compra"),
            rs.getDouble("precio_venta"),
            rs.getBoolean("requiere_receta"),
            rs.getBoolean("activo"),
            rs.getTimestamp("fecha_registro").toLocalDateTime()
        );
    }
}