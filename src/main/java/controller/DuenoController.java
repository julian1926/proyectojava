/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DuenoDAO;
import java.util.List;
import model.Dueno;

/**
 *
 * @author sd
 */
public class DuenoController {
    private DuenoDAO duenodao;
    
    public DuenoController(){
        this.duenodao = new DuenoDAO();
    }

    // Método para agregar un dueño
    public void agregarDueno(Dueno d){
        // Llamada al DAO para agregar el dueño
        duenodao.agregarDueno(d);
        System.out.println("dueño agregado");
    }
    
    // motodo para listar dueño
    public List<Dueno> ListarDuenos(){
        return duenodao.ListarDuenos();
    }
    
    //metodo para actualizar estado
    public void actualizarEstado(int id){
        duenodao.actualizarEstado(id);
    }
    
    //metodo para buscar dueño por id
    public Dueno buscarPorId(int id){
        return duenodao.buscarPorId(id);
    }
}