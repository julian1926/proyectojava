/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import model.Mascota;
import util.ConexionDB;


/**
 *
 * @author camper
 */
public class MascotaDAO {
    public void agregarMascota(Mascota m){
        String sql = "INSERT INTO mascotas(dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, micro_chip, tatuaje, ur_foto,"
                + "alergias, condiciones_preexistentes VALUES (?,?,?,?,?,?,?,?,?,?,? )";
        
        try(Connection con = ConexionDB.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, m.getDueno_id());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRaza_id());
           
            if (m.getFecha_nacimiento() != null) {
                ps.setDate(5, java.sql.Date.valueOf(m.getFecha_nacimiento()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            
            
        } catch (SQLException e) {
            System.out.println("error al agregar estudiante" +e.getMessage());
        }
       
    }
}

