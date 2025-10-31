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
public class Provedor {
    private int id;
    private String nombre_empresa;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private String sitio_web;
    private boolean activo;
    private LocalDateTime fecha_registro;
    
    // Constructor completo con ID
    public Provedor(int id, String nombre_empresa, String contacto, String telefono, String email, String direccion, String sitio_web, boolean activo, LocalDateTime fecha_registro) {
        this.id = id;
        this.nombre_empresa = nombre_empresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitio_web = sitio_web;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
    }
    
    // Constructor vacío
    public Provedor() {}
    
    // Constructor sin ID (para nuevos registros)
    public Provedor(String nombre_empresa, String contacto, String telefono, String email, String direccion, String sitio_web, boolean activo, LocalDateTime fecha_registro) {
        this.nombre_empresa = nombre_empresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sitio_web = sitio_web;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
    }
    
    // Getters
    public int getId() { return id; }
    public String getNombreEmpresa() { return nombre_empresa; }
    public String getContacto() { return contacto; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public String getSitioWeb() { return sitio_web; }
    public boolean isActivo() { return activo; }
    public LocalDateTime getFechaRegistro() { return fecha_registro; }
}