/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Sexo;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author camper
 */
public class Mascota {
    //datos...
    private int id;
    private int dueno_id;
    private String nombre;
    private int raza_id;
    private LocalDate fecha_nacimiento;
    private Sexo sexo;
    private double peso_actual;
    private String microchip;
    private String tatuaje;
    private String url_foto;
    private String alergias;
    private String condiciones_preexistentes;
    private LocalDateTime fecha_registro;
    private boolean activo;
    
    //datos para los joins
    private String nombre_dueno;
    private String nombre_raza;
    
    //constructor completo
    public Mascota(int id, int dueno_id, String nombre, int raza_id, LocalDate fecha_nacimiento, Sexo sexo, double peso_actual,
            String microchip, String tatuaje, String url_foto, String alergias, String condiciones_preexistentes, LocalDateTime fecha_registro, boolean activo){
        
        this.id = id;
        this.dueno_id = dueno_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.peso_actual = peso_actual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.url_foto = url_foto;
        this.alergias = alergias;
        this.condiciones_preexistentes = condiciones_preexistentes;
        this.fecha_registro = fecha_registro;
        this.activo = activo;  
    }
    
    //constructor sin ID
    public Mascota(int dueno_id, String nombre, int raza_id, LocalDate fecha_nacimiento, Sexo sexo, double peso_actual,
           String microchip, String tatuaje, String url_foto, String alergias, String condiciones_preexistentes, LocalDateTime fecha_registro, boolean activo){
     
        this.dueno_id = dueno_id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.peso_actual = peso_actual;
        this.microchip = microchip;
        this.tatuaje = tatuaje;
        this.url_foto = url_foto;
        this.alergias = alergias;
        this.condiciones_preexistentes = condiciones_preexistentes;
        this.fecha_registro = fecha_registro;
        this.activo = activo;  
    }
    
    //constructor vacio
    public Mascota(){ 
    }
    
    public int getId(){return id;}
    public int getDueno_id(){return dueno_id;}
    public String getNombre(){return nombre;}
    public int getRaza_id(){return raza_id;}
    public LocalDate getFecha_nacimiento(){return fecha_nacimiento;}
    public Sexo getSexo(){return sexo;}
    public double getPeso_actual(){return peso_actual;}
    public String getMicrochip(){return microchip;}
    public String getTatuaje(){return tatuaje;}
    public String getUrl_foto(){return url_foto;}
    public String getAlergias(){return alergias;}
    public String getCondiciones_preexistentes(){return condiciones_preexistentes;}
    public LocalDateTime getFecha_registro(){return fecha_registro;}
    public boolean getActivo(){return activo;}
    
    //getters para los join
    public String getNombre_dueño(){return nombre_dueno;}
    public String getNombre_raza(){return nombre_raza;}
    
    //setters para los join 
    public void setNombre_dueno(String nombre_dueno){
        this.nombre_dueno = nombre_dueno;
    }
    
    public void setNombre_raza(String nombre_raza){
        this.nombre_raza = nombre_raza;
    }
    
    //actualizar estado
    public void setIs_activo(boolean activo){
        this.activo = activo;  
    }
    
    
    
    
    
    
}
