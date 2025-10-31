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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.ConsultaMedica;
import util.ConexionDB;

public class ConsultaMedicaDAO {

    // 🔹 Agregar una nueva consulta médica
    public void agregarConsulta(ConsultaMedica consulta) {
        String sql = """
            INSERT INTO consultas_medicas (
                mascota_id, veterinario_id, cita_id, fecha_hora, motivo,
                sintomas, diagnostico, recomendaciones, observaciones,
                peso_registrado, temperatura
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, consulta.getMascota_id());
            ps.setInt(2, consulta.getVeterinario_id());
            ps.setInt(3, consulta.getCita_id());
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getFecha_hora()));
            ps.setString(5, consulta.getMotivo());
            ps.setString(6, consulta.getSintomas());
            ps.setString(7, consulta.getDiagnostico());
            ps.setString(8, consulta.getRecomendaciones());
            ps.setString(9, consulta.getObservaciones());
            ps.setDouble(10, consulta.getPeso_registrado());
            ps.setDouble(11, consulta.getTemperatura());

            ps.executeUpdate();
            System.out.println("✅ Consulta médica registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar la consulta médica: " + e.getMessage());
        }
    }

    // 🔹 Listar todas las consultas médicas con nombres de mascota y veterinario
    public List<ConsultaMedica> listarConsultas() {
    List<ConsultaMedica> lista = new ArrayList<>();

    String sql = """
        SELECT 
            cm.id,
            cm.mascota_id,
            cm.veterinario_id,
            cm.cita_id,
            cm.fecha_hora,
            cm.motivo,
            cm.sintomas,
            cm.diagnostico,
            cm.recomendaciones,
            cm.observaciones,
            cm.peso_registrado,
            cm.temperatura,
            m.nombre AS mascota_nombre,
            v.nombre_completo AS veterinario_nombre,
            c.fecha_hora AS cita_fecha_hora,
            c.estado AS cita_estado
        FROM consultas_medicas cm
        INNER JOIN mascotas m ON cm.mascota_id = m.id
        INNER JOIN veterinarios v ON cm.veterinario_id = v.id
        LEFT JOIN citas c ON cm.cita_id = c.id
        ORDER BY cm.id ASC;
    """;

    try (Connection conn = ConexionDB.getConexion();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            ConsultaMedica consulta = new ConsultaMedica();
            consulta.setId(rs.getInt("id"));
            consulta.setMascota_id(rs.getInt("mascota_id"));
            consulta.setVeterinario_id(rs.getInt("veterinario_id"));
            consulta.setCita_id(rs.getInt("cita_id"));
            
            Timestamp tsConsulta = rs.getTimestamp("fecha_hora");
            if (tsConsulta != null) {
                consulta.setFecha_hora(tsConsulta.toLocalDateTime());
            }

            consulta.setMotivo(rs.getString("motivo"));
            consulta.setSintomas(rs.getString("sintomas"));
            consulta.setDiagnostico(rs.getString("diagnostico"));
            consulta.setRecomendaciones(rs.getString("recomendaciones"));
            consulta.setObservaciones(rs.getString("observaciones"));
            consulta.setPeso_registrado(rs.getDouble("peso_registrado"));
            consulta.setTemperatura(rs.getDouble("temperatura"));

            consulta.setMascota_nombre(rs.getString("mascota_nombre"));
            consulta.setVeterinario_nombre(rs.getString("veterinario_nombre"));

            Timestamp tsCita = rs.getTimestamp("cita_fecha_hora");
            if (tsCita != null) {
                consulta.setCita_fecha_hora(tsCita.toLocalDateTime());
            }

            consulta.setCita_estado(rs.getString("cita_estado"));
            lista.add(consulta);
        }

    } catch (SQLException e) {
        System.out.println("? Error al listar consultas médicas: " + e.getMessage());
    }

    return lista;
}


    // 🔹 Buscar consulta médica por ID (con nombres y cita relacionada)
    public ConsultaMedica buscarPorId(int id) {
        ConsultaMedica c = null;

        String sql = """
            SELECT cm.*, 
                   m.nombre AS mascota_nombre,
                   v.nombre_completo AS veterinario_nombre,
                   c2.fecha_hora AS cita_fecha,
                   c2.estado AS cita_estado
            FROM consultas_medicas cm
            JOIN mascotas m ON cm.mascota_id = m.id
            JOIN veterinarios v ON cm.veterinario_id = v.id
            LEFT JOIN citas c2 ON cm.cita_id = c2.id
            WHERE cm.id = ?
        """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new ConsultaMedica(
                    rs.getInt("id"),
                    rs.getInt("mascota_id"),
                    rs.getInt("veterinario_id"),
                    rs.getInt("cita_id"),
                    rs.getTimestamp("fecha_hora").toLocalDateTime(),
                    rs.getString("motivo"),
                    rs.getString("sintomas"),
                    rs.getString("diagnostico"),
                    rs.getString("recomendaciones"),
                    rs.getString("observaciones"),
                    rs.getDouble("peso_registrado"),
                    rs.getDouble("temperatura"),
                    rs.getString("mascota_nombre"),
                    rs.getString("veterinario_nombre"),
                    rs.getTimestamp("cita_fecha") != null ? rs.getTimestamp("cita_fecha").toLocalDateTime() : null,
                    rs.getString("cita_estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar la consulta médica: " + e.getMessage());
        }
        return c;
    }
}
