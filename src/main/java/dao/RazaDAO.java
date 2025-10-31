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
import model.Razas;
import util.ConexionDB;

public class RazaDAO {

    // Método para agregar una nueva raza
    public int agregarRaza(Razas r) {
        int idGenerado = -1;
        String sql = "INSERT INTO razas (nombre, caracteristicas, especie_id) VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, r.getNombre());
            ps.setString(2, r.getCaracteristicas());
            ps.setInt(3, r.getEspecie().getId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
                System.out.println("Raza agregada correctamente con ID: " + idGenerado);
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar la raza: " + e.getMessage());
        }

        return idGenerado;
    }
}
