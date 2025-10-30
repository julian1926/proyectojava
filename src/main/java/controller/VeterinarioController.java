/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.VeterinarioDAO;
import java.util.List;
import model.Veterinario;

/**
 *
 * @author sd
 */
public class VeterinarioController {
    private VeterinarioDAO veterinariodao;
    
    public VeterinarioController(){
        this.veterinariodao = new VeterinarioDAO();
    }
    
        // Método para agregar un dueño
    public void agregarVeterinario(Veterinario v){
        // Llamada al DAO para agregar el dueño
        veterinariodao.agregarVeterinario(v);
        System.out.println("veterinario agregado");
    }
    
        // motodo para listar dueño
    public List<Veterinario> ListarVeterinarios(){
        return veterinariodao.ListarVeterinarios();
    }
    
        //metodo para actualizar estado
    public void actualizarEstado(int id){
        veterinariodao.actualizarEstado(id);
    }
    
        //metodo para buscar dueño por id
    public Veterinario buscaPorId(int id){
        return veterinariodao.buscarPorId(id);
    }
}
