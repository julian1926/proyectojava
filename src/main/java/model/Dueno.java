/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;


/**
 *
 * @author camper
 */
public class Dueno {
    private int id;
    private String nombre_completo;
    private String documento_identidad;
    private String direccion;
    private String telefono;
    private String email;
    private String contacto_emergencia;
    private LocalDateTime fecha_registro;
    private boolean estado;
    
    public Dueno(int id, String nombre_completo,String documento_identidad, String direccion,String telefono,String email, String contacto_emergencia,LocalDateTime fecha_registro, boolean estado){
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.documento_identidad = documento_identidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.contacto_emergencia = contacto_emergencia;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        
        
    }
    
    public Dueno(){}
    
    public Dueno(String nombre_completo,String documento_identidad, String direccion,String telefono,String email, String contacto_emergencia,LocalDateTime fecha_registro, boolean estado){
        this.nombre_completo = nombre_completo;
        this.documento_identidad = documento_identidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.contacto_emergencia = contacto_emergencia;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        
    }
    
    public int getId (){return id;}
    public String getNombreCompleto(){return nombre_completo;}
    public String getDocumentoIdentidad(){return documento_identidad;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public String getContactoEmergencia(){return contacto_emergencia;}
    public LocalDateTime getFechaRegistro(){return fecha_registro;}
    public boolean getEstado (){return estado;}
    
    

}


