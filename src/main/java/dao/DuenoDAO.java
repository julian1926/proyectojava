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
import model.Dueno;
import util.ConexionDB;

/**
 *
 * @author camper
 */
public class DuenoDAO {
    
    // metodo de agregar
    public void agregarDueno(Dueno d){
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia)"
                + "VALUES (?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, d.getNombreCompleto());
            ps.setString(2, d.getDocumentoIdentidad());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            
            ps.executeUpdate();
            System.out.println("Registro exitoso!!!!!");
            
        } catch (SQLException e) {
            System.out.println("No se registro el dueño"+e.getMessage());
        }
        
    }
    // metodo para listar
   public List<Dueno> ListarDuenos (){
        List<Dueno>  lista = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Connection con = ConexionDB.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                Dueno d = new Dueno(
                rs.getInt("id"),
                rs.getString("nombre_completo"),
                rs.getString("documento_identidad"),
                rs.getString("direccion"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("contacto_emergencia"),
                rs.getTimestamp("fecha_registro").toLocalDateTime(),
                rs.getBoolean("activo")
                );
                lista.add(d);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar dueños"+ e.getMessage());
        }
        return lista;
    }
    
   //metodo para buscar por id
    public Dueno buscarPorId(int id) {
        Dueno dueno = null;
        String sql = "SELECT * FROM duenos WHERE id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dueno = new Dueno(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("contacto_emergencia"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime(),
                    rs.getBoolean("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar dueño por ID: " + e.getMessage());
        }

        return dueno;
    }


   
    // metodo para actualizar estado
    public void actualizarEstado(int id){
        String sql = "UPDATE duenos SET activo = NOT activo WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            
            int filasActualizadas = ps.executeUpdate();
            if(filasActualizadas > 0){
                System.out.println("Estado actualizado correctamente");
            }else{
                System.out.println("No se encontro el dueño");
            }
        } catch (SQLException e) {
//            System.out.println("Error al actualizar estado"+ e.getMessage());
        }
    }
}
