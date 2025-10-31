package dao;

import enums.Sexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Mascota;
import util.ConexionDB;

public class MascotaDAO {

    // Método para registrar mascotas
    public void agregarMascota(Mascota m) {
        String sql = "INSERT INTO mascotas(dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto,"
                + " alergias, condiciones_preexistentes) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getDueno_id());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRaza_id());
            ps.setDate(4, m.getFecha_nacimiento());
            ps.setString(5, m.getSexo().name()); // Enum en mayúsculas
            ps.setDouble(6, m.getPeso_actual());
            ps.setString(7, m.getMicrochip());
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrl_foto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondiciones_preexistentes());

            ps.executeUpdate();
            System.out.println("Registro exitoso.");

        } catch (SQLException e) {
            System.out.println("Error al registrar la mascota: " + e.getMessage());
        }
    }

    // Método para listar mascotas
    public List<Mascota> listarMascotas() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT m.*, d.nombre_completo AS nombre_dueno, r.nombre AS nombre_raza "
                   + "FROM mascotas m "
                   + "LEFT JOIN duenos d ON m.dueno_id = d.id "
                   + "LEFT JOIN razas r ON m.raza_id = r.id";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sexo sexo = null;
                try {
                    sexo = Sexo.valueOf(rs.getString("sexo").toUpperCase());
                } catch (IllegalArgumentException | NullPointerException ex) {
                    sexo = null;
                }

                Mascota m = new Mascota(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("nombre"),
                    rs.getInt("raza_id"),
                    rs.getDate("fecha_nacimiento"),
                    sexo,  // Aquí usamos la variable ya creada
                    rs.getDouble("peso_actual"),
                    rs.getString("microchip"),
                    rs.getString("tatuaje"),
                    rs.getString("url_foto"),
                    rs.getString("alergias"),
                    rs.getString("condiciones_preexistentes"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime(),
                    rs.getBoolean("activo")
                );

                m.setNombre_dueno(rs.getString("nombre_dueno"));
                m.setNombre_raza(rs.getString("nombre_raza"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar las mascotas: " + e.getMessage());
        }

        return lista;
    }

    // Método para actualizar estado
    public void actualizarEstado(int id) {
        String sql = "UPDATE mascotas SET activo = NOT activo WHERE id=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, id);
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado actualizado con éxito.");
            } else {
                System.out.println("No se encontró la mascota.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado: " + e.getMessage());
        }
    }

    // Método para buscar por ID
    public Mascota buscarPorId(int id) {
        Mascota mascota = null;

        String sql = "SELECT m.*, d.nombre_completo AS nombre_dueno, r.nombre AS nombre_raza "
                   + "FROM mascotas m "
                   + " JOIN duenos d ON m.dueno_id = d.id "
                   + " JOIN razas r ON m.raza_id = r.id "
                   + "WHERE m.id = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Sexo sexo = null;
                try {
                    sexo = Sexo.valueOf(rs.getString("sexo").toUpperCase());
                } catch (IllegalArgumentException | NullPointerException ex) {
                    sexo = null;
                }

                mascota = new Mascota(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("nombre"),
                    rs.getInt("raza_id"),
                    rs.getDate("fecha_nacimiento"),
                    sexo,
                    rs.getDouble("peso_actual"),
                    rs.getString("microchip"),
                    rs.getString("tatuaje"),
                    rs.getString("url_foto"),
                    rs.getString("alergias"),
                    rs.getString("condiciones_preexistentes"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime(),
                    rs.getBoolean("activo")
                );

                mascota.setNombre_dueno(rs.getString("nombre_dueno"));
                mascota.setNombre_raza(rs.getString("nombre_raza"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la mascota por ID: " + e.getMessage());
        }

        return mascota;
    }
}
