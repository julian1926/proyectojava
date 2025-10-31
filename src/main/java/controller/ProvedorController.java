/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProvedorDAO;
import java.util.List;
import model.Provedor;

/**
 *
 * @author sd
 */
public class ProvedorController {
    private ProvedorDAO provedorDAO;
    
    public ProvedorController(){
        this.provedorDAO = new ProvedorDAO();
    }

    // Método para agregar un proveedor
    public void agregarProvedor(Provedor p){
        // Llamada al DAO para agregar el proveedor
        provedorDAO.agregarProvedor(p);
        System.out.println("Proveedor agregado");
    }
    
    // Método para listar proveedores
    public List<Provedor> listarProvedores(){
        return provedorDAO.listarProvedores();
    }
    
    // Método para actualizar estado
    public void actualizarEstado(int id){
        provedorDAO.actualizarEstado(id);
    }
    
    // Método para buscar proveedor por id
    public Provedor buscarPorId(int id){
        return provedorDAO.buscarPorId(id);
    }
    
    // Método adicional para buscar por nombre (si lo necesitas)
    public List<Provedor> buscarPorNombre(String nombre){
        return provedorDAO.buscarPorNombre(nombre);
    }
}