/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author sd
 */
public class Veterinario {
    private int id;
    private String nombre_completo;
    private String documento_identidad;
    private String licencia_profesional;
    private String especialidad;
    private String telefono;
    private String email;
    private Date fecha_contratacion;
    private boolean activo;
    
    public Veterinario(int id, String nombre_completo, String documento_identidad, String licencia_profesional, String especialidad, String telefono, String email, Date fecha_contratacion, boolean activo){
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.documento_identidad = documento_identidad;
        this.licencia_profesional = licencia_profesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fecha_contratacion = fecha_contratacion;
        this.activo = activo;
    }
    
    // constructor sin id    
    public Veterinario(String nombre_completo, String documento_identidad, String licencia_profesional, String especialidad, String telefono, String email, Date fecha_contratacion, boolean activo){
        this.nombre_completo = nombre_completo;
        this.documento_identidad = documento_identidad;
        this.licencia_profesional = licencia_profesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.fecha_contratacion = fecha_contratacion;
        this.activo = activo;
    }
    
    // contructor vacio
    public Veterinario(){}
    
    // getters
    public int getId(){return id;}
    public String getNombreCompleto(){return nombre_completo;}
    public String getDocumentoIdentidad(){return documento_identidad;}
    public String getLicenciaProfesional(){return licencia_profesional;}
    public String getEspecialidad(){return especialidad;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public Date getFechaContratacion(){return fecha_contratacion;}
    public boolean isActivo(){return activo;}
        
}
