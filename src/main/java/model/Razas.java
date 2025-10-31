/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USUARIO
 */
public class Razas {
    private int id;
    private String nombre;
    private String caracteristicas;
    private Especie especie;

    public Razas(int id, String nombre, String caracteristicas, Especie especie) {
        this.id = id;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.especie = especie;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCaracteristicas() { return caracteristicas; }
    public Especie getEspecie() { return especie; }
}

