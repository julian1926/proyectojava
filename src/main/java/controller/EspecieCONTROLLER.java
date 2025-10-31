/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EspecieDAO;
import java.util.List;
import model.Especie;

/**
 *
 * @author USUARIO
 */
public class EspecieCONTROLLER {
     private EspecieDAO especiedao;
    
    public EspecieCONTROLLER(){
        this.especiedao = new EspecieDAO();
    }
    
    public List<Especie> listarEspecie(){
        return especiedao.listarEspecies();
    }
}
