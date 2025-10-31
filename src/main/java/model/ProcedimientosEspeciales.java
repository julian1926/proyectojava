/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProcedimientosEspeciales {
    private int id;
    private int mascota_id;
    private int veterinario_id;
    private String tipo_procedimiento;
    private String nombre_procedimiento;
    private LocalDateTime fecha_hora;
    private int duracion_estimada_minutos;
    private String informacion_preoperatoria;
    private String detalle_procedimiento;
    private String complicaciones;
    private String seguimiento_postoperatorio;
    private LocalDate proximo_control;
    private String estado;
    private double costo_procedimiento;

    // Campos adicionales (relaciones)
    private String mascota_nombre;
    private String veterinario_nombre;

    // ----- Constructores -----
    public ProcedimientosEspeciales() {}

    public ProcedimientosEspeciales(int id, int mascota_id, int veterinario_id, String tipo_procedimiento,
                                 String nombre_procedimiento, LocalDateTime fecha_hora, int duracion_estimada_minutos,
                                 String informacion_preoperatoria, String detalle_procedimiento, String complicaciones,
                                 String seguimiento_postoperatorio, LocalDate proximo_control, String estado,
                                 double costo_procedimiento, String mascota_nombre, String veterinario_nombre) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.tipo_procedimiento = tipo_procedimiento;
        this.nombre_procedimiento = nombre_procedimiento;
        this.fecha_hora = fecha_hora;
        this.duracion_estimada_minutos = duracion_estimada_minutos;
        this.informacion_preoperatoria = informacion_preoperatoria;
        this.detalle_procedimiento = detalle_procedimiento;
        this.complicaciones = complicaciones;
        this.seguimiento_postoperatorio = seguimiento_postoperatorio;
        this.proximo_control = proximo_control;
        this.estado = estado;
        this.costo_procedimiento = costo_procedimiento;
        this.mascota_nombre = mascota_nombre;
        this.veterinario_nombre = veterinario_nombre;
    }

    // ----- Getters y Setters -----
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMascota_id() { return mascota_id; }
    public void setMascota_id(int mascota_id) { this.mascota_id = mascota_id; }

    public int getVeterinario_id() { return veterinario_id; }
    public void setVeterinario_id(int veterinario_id) { this.veterinario_id = veterinario_id; }

    public String getTipo_procedimiento() { return tipo_procedimiento; }
    public void setTipo_procedimiento(String tipo_procedimiento) { this.tipo_procedimiento = tipo_procedimiento; }

    public String getNombre_procedimiento() { return nombre_procedimiento; }
    public void setNombre_procedimiento(String nombre_procedimiento) { this.nombre_procedimiento = nombre_procedimiento; }

    public LocalDateTime getFecha_hora() { return fecha_hora; }
    public void setFecha_hora(LocalDateTime fecha_hora) { this.fecha_hora = fecha_hora; }

    public int getDuracion_estimada_minutos() { return duracion_estimada_minutos; }
    public void setDuracion_estimada_minutos(int duracion_estimada_minutos) { this.duracion_estimada_minutos = duracion_estimada_minutos; }

    public String getInformacion_preoperatoria() { return informacion_preoperatoria; }
    public void setInformacion_preoperatoria(String informacion_preoperatoria) { this.informacion_preoperatoria = informacion_preoperatoria; }

    public String getDetalle_procedimiento() { return detalle_procedimiento; }
    public void setDetalle_procedimiento(String detalle_procedimiento) { this.detalle_procedimiento = detalle_procedimiento; }

    public String getComplicaciones() { return complicaciones; }
    public void setComplicaciones(String complicaciones) { this.complicaciones = complicaciones; }

    public String getSeguimiento_postoperatorio() { return seguimiento_postoperatorio; }
    public void setSeguimiento_postoperatorio(String seguimiento_postoperatorio) { this.seguimiento_postoperatorio = seguimiento_postoperatorio; }

    public LocalDate getProximo_control() { return proximo_control; }
    public void setProximo_control(LocalDate proximo_control) { this.proximo_control = proximo_control; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getCosto_procedimiento() { return costo_procedimiento; }
    public void setCosto_procedimiento(double costo_procedimiento) { this.costo_procedimiento = costo_procedimiento; }

    public String getMascota_nombre() { return mascota_nombre; }
    public void setMascota_nombre(String mascota_nombre) { this.mascota_nombre = mascota_nombre; }

    public String getVeterinario_nombre() { return veterinario_nombre; }
    public void setVeterinario_nombre(String veterinario_nombre) { this.veterinario_nombre = veterinario_nombre; }

    // ----- toString -----
    @Override
    public String toString() {
        return "\n-------- PROCEDIMIENTO ESPECIAL --------\n"
                + "ID: " + id + "\n"
                + "Mascota: " + mascota_nombre + "\n"
                + "Veterinario: " + veterinario_nombre + "\n"
                + "Tipo de procedimiento: " + tipo_procedimiento + "\n"
                + "Nombre: " + nombre_procedimiento + "\n"
                + "Fecha y hora: " + fecha_hora + "\n"
                + "Duración estimada: " + duracion_estimada_minutos + " min\n"
                + "Estado: " + estado + "\n"
                + "Costo: $" + costo_procedimiento + "\n"
                + "Información preoperatoria: " + informacion_preoperatoria + "\n"
                + "Detalle: " + detalle_procedimiento + "\n"
                + "Complicaciones: " + complicaciones + "\n"
                + "Seguimiento postoperatorio: " + seguimiento_postoperatorio + "\n"
                + "Próximo control: " + proximo_control + "\n"
                + "---------------------------------------\n";
    }
}
