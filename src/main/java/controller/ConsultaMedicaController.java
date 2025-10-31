/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ConsultaMedicaDAO;
import model.ConsultaMedica;
import java.util.List;

public class ConsultaMedicaController {

    private ConsultaMedicaDAO dao;

    public ConsultaMedicaController() {
        dao = new ConsultaMedicaDAO();
    }

    public void agregarConsulta(ConsultaMedica consulta) {
         dao.agregarConsulta(consulta);
    }

    public List<ConsultaMedica> listarConsultas() {
        return dao.listarConsultas();
    }

    public ConsultaMedica buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
