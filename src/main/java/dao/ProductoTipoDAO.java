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
import java.util.ArrayList;
import java.util.List;
import model.ProductoTipo;
import util.ConexionDB;

/**
 *
 * @author camper
 */
public class ProductoTipoDAO {
    
    // Método para agregar tipo de producto
    public void agregarProductoTipo(ProductoTipo pt) {
        String sql = "INSERT INTO producto_tipos (nombre, descripcion) VALUES (?, ?)";
        try (Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pt.getNombre());
            ps.setString(2, pt.getDescripcion());
            
            ps.executeUpdate();
            System.out.println("Tipo de producto agregado exitosamente!!!!!");
            
        } catch (SQLException e) {
            System.out.println("No se registro el tipo de producto: " + e.getMessage());
        }
    }
    
    // Método para listar todos los tipos de producto
    public List<ProductoTipo> listarProductoTipos() {
        List<ProductoTipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto_tipos ORDER BY nombre";
        try (Connection con = ConexionDB.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ProductoTipo pt = new ProductoTipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(pt);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar tipos de producto: " + e.getMessage());
        }
        return lista;
    }
    
    // Método para buscar por ID
    public ProductoTipo buscarPorId(int id) {
        ProductoTipo productoTipo = null;
        String sql = "SELECT * FROM producto_tipos WHERE id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                productoTipo = new ProductoTipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar tipo de producto por ID: " + e.getMessage());
        }

        return productoTipo;
    }
    
    // Método para buscar por nombre
    public ProductoTipo buscarPorNombre(String nombre) {
        ProductoTipo productoTipo = null;
        String sql = "SELECT * FROM producto_tipos WHERE nombre = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                productoTipo = new ProductoTipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar tipo de producto por nombre: " + e.getMessage());
        }

        return productoTipo;
    }
    
    // Método para actualizar tipo de producto
    public void actualizarProductoTipo(ProductoTipo pt) {
        String sql = "UPDATE producto_tipos SET nombre = ?, descripcion = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pt.getNombre());
            ps.setString(2, pt.getDescripcion());
            ps.setInt(3, pt.getId());
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Tipo de producto actualizado correctamente");
            } else {
                System.out.println("No se encontro el tipo de producto");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de producto: " + e.getMessage());
        }
    }
    
    // Método para eliminar tipo de producto (solo si no tiene productos asociados)
    public void eliminarProductoTipo(int id) {
        String sql = "DELETE FROM producto_tipos WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Tipo de producto eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el tipo de producto (puede tener productos asociados)");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de producto: " + e.getMessage());
        }
    }
    
    // Método para verificar si existe el nombre (para evitar duplicados)
    public boolean existeNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM producto_tipos WHERE nombre = ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al verificar nombre: " + e.getMessage());
        }
        return false;
    }
}