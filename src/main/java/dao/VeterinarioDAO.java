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
import model.Veterinario;
import util.ConexionDB;

/**
 *
 * @author sd
 */
public class VeterinarioDAO {
      // metodo de agregar
    public void agregarVeterinario(Veterinario v){
        String sql = "INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion)"
                + "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, v.getNombreCompleto());
            ps.setString(2, v.getDocumentoIdentidad());
            ps.setString(3, v.getLicenciaProfesional());
            ps.setString(4, v.getEspecialidad());
            ps.setString(5, v.getTelefono());
            ps.setString(6, v.getEmail());
            ps.setDate(7, v.getFechaContratacion());
            
            ps.executeUpdate();
            System.out.println("Registro exitoso!!!!!");
            
        } catch (SQLException e) {
            System.out.println("No se registro el Veterinario "+e.getMessage());
        }
        
    }
    
        // metodo para listar
    public List<Veterinario> ListarVeterinarios (){
        List<Veterinario>  listav = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios";
        try (Connection con = ConexionDB.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                Veterinario v = new Veterinario(
                rs.getInt("id"),
                rs.getString("nombre_completo"),
                rs.getString("documento_identidad"),
                rs.getString("licencia_profesional"),
                rs.getString("especialidad"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getDate("fecha_contratacion"),
                rs.getBoolean("activo")
                );
                listav.add(v);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar Veterinarios "+ e.getMessage());
        }
        return listav;
    }
    
       //metodo para buscar por id
    public Veterinario buscarPorId(int id) {
        Veterinario v = null;
        String sql = "SELECT * FROM veterinarios WHERE id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("licencia_profesional"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getDate("fecha_contratacion"),
                    rs.getBoolean("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar dueño por ID: " + e.getMessage());
        }

        return v;
    }
    
        // metodo para actualizar estado
    public void actualizarEstado(int id){
        String sql = "UPDATE veterinarios SET activo = NOT activo WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            
            int filasActualizadas = ps.executeUpdate();
            if(filasActualizadas > 0){
                System.out.println("Estado actualizado correctamente");
            }else{
                System.out.println("No se encontro el Veterinario");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado"+ e.getMessage());
        }
    }
}
