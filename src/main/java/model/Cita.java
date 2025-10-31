/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Estado;
import java.time.LocalDateTime;

/**
 *
 * @author USUARIO
 */
public class Cita {
    //Valores...
    private int id;
    private int mascota_id;
    private int veterinario_id;
    private LocalDateTime fecha_hora;
    private String motivo;
    private Estado estado;
    private String observaciones;
    private LocalDateTime fecha_creacion;
    
    private String mascota_nombre;
    private String veterinario_nombre;

    
    //constructor vacio
    public Cita(){};
    
    //constructor completo
    public Cita(int id, int mascota_id, int veterinario_id, LocalDateTime fecha_hora, String motivo,
           Estado estado, String observaciones, LocalDateTime fecha_creacion){
        
        this.id = id;
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.estado = estado;
        this.observaciones = observaciones;
        this.fecha_creacion = fecha_creacion;
    }
    
    //constructor sin id
    public Cita( int mascota_id, int veterinario_id, LocalDateTime fecha_hora, String motivo,
           Estado estado, String observaciones, LocalDateTime fecha_creacion){
        
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.estado = estado;
        this.observaciones = observaciones;
        this.fecha_creacion = fecha_creacion;
    }
    
    //getters
    public int getId (){return id;}
    public int getMascota_id(){return mascota_id;}
    public int getVeterinarioId(){return veterinario_id;}
    public LocalDateTime getFecha_hora(){return fecha_hora;}
    public String getMotivo(){return motivo;}
    public Estado getEstado(){return estado;}
    public String getObservaciones(){return observaciones;}
    public LocalDateTime getFecha_creacion(){return fecha_creacion;}
    
    public String getMascota_nombre(){return mascota_nombre;}
    public String getVeterinario_nombre(){return veterinario_nombre;}
    
    public void setEstado(Estado estado){
        this.estado = estado;  
    }
    
    public void setMascota_nombre(String mascota_nombre) {
    this.mascota_nombre = mascota_nombre;
    }

    public void setVeterinario_nombre(String veterinario_nombre) {
      this.veterinario_nombre = veterinario_nombre;
    }

    
    
}
