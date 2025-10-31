
package dao;

import enums.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cita;
import util.ConexionDB;

/**
 *
 * @author camper
 */
public class CitaDAO {

    // MÉTODO PARA AGREGAR CITA
    public void agregarCita(Cita c) {
        String sql = "INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, observaciones, fecha_creacion, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getMascota_id());
            ps.setInt(2, c.getVeterinarioId());
            ps.setTimestamp(3, Timestamp.valueOf(c.getFecha_hora()));
            ps.setString(4, c.getMotivo());
            ps.setString(5, c.getObservaciones());
            ps.setTimestamp(6, Timestamp.valueOf(c.getFecha_creacion()));
            ps.setString(7, c.getEstado().name());

            ps.executeUpdate();
            System.out.println(" Cita registrada correctamente!");

        } catch (SQLException e) {
            System.out.println(" Error al registrar la cita: " + e.getMessage());
        }
    }

    // MÉTODO PARA LISTAR TODAS LAS CITAS (con nombre de mascota y veterinario)
    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT c.*, m.nombre AS mascota_nombre, v.nombre_completo AS veterinario_nombre "
                   + "FROM citas c "
                   + "JOIN mascotas m ON c.mascota_id = m.id "
                   + "JOIN veterinarios v ON c.veterinario_id = v.id";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cita c = new Cita(
                    rs.getInt("id"),
                    rs.getInt("mascota_id"),
                    rs.getInt("veterinario_id"),
                    rs.getTimestamp("fecha_hora").toLocalDateTime(),
                    rs.getString("motivo"),
                    Estado.valueOf(rs.getString("estado")),
                    rs.getString("observaciones"),
                    rs.getTimestamp("fecha_creacion").toLocalDateTime()
                );

                // nombres de relaciones
               c.setMascota_nombre(rs.getString("mascota_nombre"));
               c.setVeterinario_nombre(rs.getString("veterinario_nombre"));


                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar citas: " + e.getMessage());
        }
        return lista;
    }

    // MÉTODO PARA BUSCAR CITA POR ID
    public Cita buscarPorId(int id) {
        Cita cita = null;
        String sql = "SELECT c.*, m.nombre AS mascota_nombre, v.nombre_completo AS veterinario_nombre "
                   + "FROM citas c "
                   + "JOIN mascotas m ON c.mascota_id = m.id "
                   + "JOIN veterinarios v ON c.veterinario_id = v.id "
                   + "WHERE c.id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cita = new Cita(
                    rs.getInt("id"),
                    rs.getInt("mascota_id"),
                    rs.getInt("veterinario_id"),
                    rs.getTimestamp("fecha_hora").toLocalDateTime(),
                    rs.getString("motivo"),
                    Estado.valueOf(rs.getString("estado")),
                    rs.getString("observaciones"),
                    rs.getTimestamp("fecha_creacion").toLocalDateTime()
                );

                cita.setMascota_nombre(rs.getString("mascota_nombre"));
                cita.setVeterinario_nombre(rs.getString("veterinario_nombre"));

            }

        } catch (SQLException e) {
            System.out.println(" Error al buscar cita por ID: " + e.getMessage());
        }
        return cita;
    }

    // MÉTODO PARA ACTUALIZAR ESTADO DE UNA CITA
    public void actualizarEstado(int id, Estado nuevoEstado) {
        String sql = "UPDATE citas SET estado = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado.name());
            ps.setInt(2, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println(" Estado de la cita actualizado correctamente!");
            } else {
                System.out.println("️ No se encontró la cita con el ID especificado.");
            }

        } catch (SQLException e) {
            System.out.println(" Error al actualizar estado de cita: " + e.getMessage());
        }
    }
}
