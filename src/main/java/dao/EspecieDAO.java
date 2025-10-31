/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Especie;
import util.ConexionDB;

/**
 *
 * @author USUARIO
 */
public class EspecieDAO {
     // Método para listar especies
    public List<Especie> listarEspecies() {
        List<Especie> lista = new ArrayList<>();
        String sql = "SELECT * FROM especies";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Especie e = new Especie(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("Error al listar las especies: " + ex.getMessage());
        }

        return lista;
    }
}
