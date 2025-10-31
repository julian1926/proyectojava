/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RazaDAO;
import model.Razas;

/**
 *
 * @author USUARIO
 */
public class RazaController {
     private RazaDAO razadao;
    
    public RazaController(){
        this.razadao = new RazaDAO();
    }
    
    public void agregarRaza(Razas r){
        razadao.agregarRaza(r);
    }
}
