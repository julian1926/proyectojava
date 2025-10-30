/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MascotaDAO;
import java.util.List;
import model.Mascota;

/**
 *
 * @author USUARIO
 */
public class MascotaController {
    private MascotaDAO mascotadao;
    
    public MascotaController(){
        this.mascotadao = new MascotaDAO();
    }
    
    public void agregarMascota(Mascota m){
        mascotadao.agregarMascota(m);
        System.out.println("Mascota agregada");
    }
    
    public List<Mascota> listarMascotas(){
        return mascotadao.listarMascotas();
    }
    
    public Mascota buscarPorId(int id){
        return mascotadao.buscarPorId(id);
    }
    
    public void actualizarEstado(int id){
        mascotadao.actualizarEstado(id);
    }
}
