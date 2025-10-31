/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author camper
 */
public class ConexionDB {
    public static final String URL = "jdbc:mysql://localhost:3306/happy_feet_veterinaria";
    public static final String USUARIO = "root";
    public static final String CONTRASENIA = "J4lian19";
    
    public static Connection getConexion (){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Conexion no encontrada"+e.getMessage());
        }
        return con;
    }
    
    
}
