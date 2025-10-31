/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.CitaDAO;
import enums.Estado;
import java.util.List;
import model.Cita;

/**
 *
 * @author camper
 */
public class CitaController {
    private CitaDAO citadao;
    
    public CitaController() {
        this.citadao = new CitaDAO();
    }
    
    public void agregarCita(Cita c) {
        citadao.agregarCita(c);
        System.out.println("✅ Cita agregada correctamente");
    }
    
    public List<Cita> listarCitas() {
        return citadao.listarCitas();
    }
    
    public Cita buscarPorId(int id) {
        return citadao.buscarPorId(id);
    }
    
    public void actualizarEstado(int id, Estado nuevoEstado) {
        citadao.actualizarEstado(id, nuevoEstado);
    }
}
