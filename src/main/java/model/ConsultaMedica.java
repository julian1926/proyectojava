/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 * Modelo para representar una consulta médica
 * 
 * @author camper
 */
public class ConsultaMedica {
    private int id;
    private int mascota_id;
    private int veterinario_id;
    private int cita_id;
    private LocalDateTime fecha_hora;
    private String motivo;
    private String sintomas;
    private String diagnostico;
    private String recomendaciones;
    private String observaciones;
    private double peso_registrado;
    private double temperatura;

    // Campos adicionales (relaciones)
    private String mascota_nombre;
    private String veterinario_nombre;
    private LocalDateTime cita_fecha_hora;
    private String cita_estado;

    // ----- Constructores -----
    public ConsultaMedica() {
    }

    public ConsultaMedica(int id, int mascota_id, int veterinario_id, int cita_id, LocalDateTime fecha_hora,
                          String motivo, String sintomas, String diagnostico, String recomendaciones,
                          String observaciones, double peso_registrado, double temperatura) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.cita_id = cita_id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
        this.observaciones = observaciones;
        this.peso_registrado = peso_registrado;
        this.temperatura = temperatura;
    }
    
    
    public ConsultaMedica(int id, int mascota_id, int veterinario_id, int cita_id,
                      LocalDateTime fecha_hora, String motivo, String sintomas,
                      String diagnostico, String recomendaciones, String observaciones,
                      double peso_registrado, double temperatura,
                      String mascota_nombre, String veterinario_nombre,
                      LocalDateTime cita_fecha_hora, String cita_estado) {
    this.id = id;
    this.mascota_id = mascota_id;
    this.veterinario_id = veterinario_id;
    this.cita_id = cita_id;
    this.fecha_hora = fecha_hora;
    this.motivo = motivo;
    this.sintomas = sintomas;
    this.diagnostico = diagnostico;
    this.recomendaciones = recomendaciones;
    this.observaciones = observaciones;
    this.peso_registrado = peso_registrado;
    this.temperatura = temperatura;
    this.mascota_nombre = mascota_nombre;
    this.veterinario_nombre = veterinario_nombre;
    this.cita_fecha_hora = cita_fecha_hora;
    this.cita_estado = cita_estado;
}


    // ----- Getters y Setters -----
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(int mascota_id) {
        this.mascota_id = mascota_id;
    }

    public int getVeterinario_id() {
        return veterinario_id;
    }

    public void setVeterinario_id(int veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public int getCita_id() {
        return cita_id;
    }

    public void setCita_id(int cita_id) {
        this.cita_id = cita_id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPeso_registrado() {
        return peso_registrado;
    }

    public void setPeso_registrado(double peso_registrado) {
        this.peso_registrado = peso_registrado;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getMascota_nombre() {
        return mascota_nombre;
    }

    public void setMascota_nombre(String mascota_nombre) {
        this.mascota_nombre = mascota_nombre;
    }

    public String getVeterinario_nombre() {
        return veterinario_nombre;
    }

    public void setVeterinario_nombre(String veterinario_nombre) {
        this.veterinario_nombre = veterinario_nombre;
    }

    public LocalDateTime getCita_fecha_hora() {
        return cita_fecha_hora;
    }

    public void setCita_fecha_hora(LocalDateTime cita_fecha_hora) {
        this.cita_fecha_hora = cita_fecha_hora;
    }

    public String getCita_estado() {
        return cita_estado;
    }

    public void setCita_estado(String cita_estado) {
        this.cita_estado = cita_estado;
    }

    // ----- toString -----
    @Override
    public String toString() {
        return "-------- CONSULTA MÉDICA --------\n"
                + "ID: " + id + "\n"
                + "Mascota ID: " + mascota_id + "\n"
                + "Mascota: " + mascota_nombre + "\n"
                + "Veterinario ID: " + veterinario_id + "\n"
                + "Veterinario: " + veterinario_nombre + "\n"
                + "Cita ID: " + cita_id + "\n"
                + "Cita Fecha: " + cita_fecha_hora + "\n"
                + "Cita Estado: " + cita_estado + "\n"
                + "Fecha y hora: " + fecha_hora + "\n"
                + "Motivo: " + motivo + "\n"
                + "Síntomas: " + sintomas + "\n"
                + "Diagnóstico: " + diagnostico + "\n"
                + "Recomendaciones: " + recomendaciones + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "Peso registrado: " + peso_registrado + "\n"
                + "Temperatura: " + temperatura + "\n"
                + "---------------------------------\n";
    }
}
