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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Provedor;
import util.ConexionDB;

/**
 *
 * @author camper
 */
public class ProvedorDAO {
    
    // Método para agregar
    public void agregarProvedor(Provedor p) {
        String sql = "INSERT INTO provedores (nombre_empresa, contacto, telefono, email, direccion, sitio_web)"
                + "VALUES (?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getSitioWeb());
            
            ps.executeUpdate();
            System.out.println("Proveedor registrado exitosamente!!!!!");
            
        } catch (SQLException e) {
            System.out.println("No se registro el proveedor: " + e.getMessage());
        }
    }
    
    // Método para listar
    public List<Provedor> listarProvedores() {
        List<Provedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM provedores";
        try (Connection con = ConexionDB.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Provedor p = new Provedor(
                    rs.getInt("id"),
                    rs.getString("nombre_empresa"),
                    rs.getString("contacto"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("sitio_web"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
                lista.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }
    
    // Método para buscar por ID
    public Provedor buscarPorId(int id) {
        Provedor provedor = null;
        String sql = "SELECT * FROM provedores WHERE id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                provedor = new Provedor(
                    rs.getInt("id"),
                    rs.getString("nombre_empresa"),
                    rs.getString("contacto"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("sitio_web"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar proveedor por ID: " + e.getMessage());
        }

        return provedor;
    }
    
    // Método para actualizar estado
    public void actualizarEstado(int id) {
        String sql = "UPDATE provedores SET activo = NOT activo WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado del proveedor actualizado correctamente");
            } else {
                System.out.println("No se encontro el proveedor");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del proveedor: " + e.getMessage());
        }
    }
    
    // Método adicional: buscar por nombre (opcional, basado en el índice de la tabla)
    public List<Provedor> buscarPorNombre(String nombre) {
        List<Provedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM provedores WHERE nombre_empresa LIKE ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Provedor p = new Provedor(
                    rs.getInt("id"),
                    rs.getString("nombre_empresa"),
                    rs.getString("contacto"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("sitio_web"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
                lista.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar proveedores por nombre: " + e.getMessage());
        }
        return lista;
    }
}