/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import controller.MascotaController;
import enums.Sexo;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Mascota;

/**
 *
 * @author USUARIO
 */
public class MascotaVIEW {
    private MascotaController controller;
    private Scanner sc;
    
    public MascotaVIEW(){
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new MascotaController();
        
        do {            
            System.out.println("-------- Gestion De Mascotas --------");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar Estado");
            System.out.println("4. Buscar Por ID");
            System.out.println("5. Volver Al Menu Principal");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del dueño: ");
                    int id_dueno = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese el nombre de la mascota: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el ID de la raza: ");
                    int id_raza = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese la fecha de nacimiento de la mascota yy-mm-dd: ");
                    Date fecha_nacimiento = Date.valueOf(sc.nextLine());
                    System.out.println("Sexo de la mascota(MACHO/HEMBRA): ");
                    Sexo sexo = Sexo.valueOf(sc.nextLine().toUpperCase());
                    System.out.println("Ingrese el peso actual: ");
                    double peso = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Ingrese el microchip (o 'Ninguno' si no tiene): ");
                    String microchip = sc.nextLine();
                    System.out.println("Ingrese el tatuaje (o 'Ninguno' si no tiene): ");
                    String tatuaje = sc.nextLine();
                    System.out.println("Ingrese la URL de la foto: ");
                    String url_foto = sc.nextLine();
                    System.out.println("Ingrese alergias: ");
                    String alergias = sc.nextLine();
                    System.out.println("Ingrese condiciones preexistentes: ");
                    String condiciones = sc.nextLine();
                    
                    LocalDateTime fecha_registro = LocalDateTime.now();
                    boolean activo = true;
                    
                    Mascota m = new Mascota(id_dueno, nombre, id_raza, fecha_nacimiento, sexo, peso,
                            microchip, tatuaje, url_foto, alergias, condiciones, fecha_registro, activo);
                    
                    controller.agregarMascota(m);
                    break;
                    
                case 2:
                    List<Mascota> lista = controller.listarMascotas();
                    if(lista.isEmpty()){
                        System.out.println("La lista de mascotas esta vacia...");
                    }else{
                        System.out.println("-------- LISTA DE MASCOTAS --------");
                        for (Mascota mascota : lista) {
                            System.out.println("");
                            System.out.println("ID: " + mascota.getId());
                            System.out.println("Nombre: " + mascota.getNombre());
                            System.out.println("Dueño: " + mascota.getNombre_dueño());
                            System.out.println("Raza: " + mascota.getNombre_raza());
                            System.out.println("Fecha de nacimiento: " + mascota.getFecha_nacimiento());
                            System.out.println("Sexo: " + mascota.getSexo());
                            System.out.println("Peso actual: " + mascota.getPeso_actual());
                            System.out.println("Microchip: " + mascota.getMicrochip());
                            System.out.println("Tatuaje: " + mascota.getTatuaje());
                            System.out.println("URL Foto: " + mascota.getUrl_foto());
                            System.out.println("Alergias: " + mascota.getAlergias());
                            System.out.println("Condiciones preexistentes: " + mascota.getCondiciones_preexistentes());
                            System.out.println("Fecha de registro: " + mascota.getFecha_registro());
                            System.out.println("Activo: " + (mascota.getActivo() ? "Sí" : "No"));
                            System.out.println("--------------------------------------"); 
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Ingrese el ID de la mascota para modificar el estado: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    controller.actualizarEstado(id);
                    System.out.println("Estado actualizado!!!");
                    break;
                    
                case 4:
                    System.out.println("Ingrese el ID de la mascota a buscar: ");
                    int id_mascota = sc.nextInt();
                    sc.nextLine();
                    Mascota mascota = controller.buscarPorId(id_mascota);
                    if(mascota != null){
                        System.out.println("----- INFORMACION DE LA MASCOTA -----");
                        System.out.println("");
                        System.out.println("Nombre: " + mascota.getNombre());
                        System.out.println("Dueño: " + mascota.getNombre_dueño());
                        System.out.println("Raza: " + mascota.getNombre_raza());
                        System.out.println("Fecha de nacimiento: " + mascota.getFecha_nacimiento());
                        System.out.println("Sexo: " + mascota.getSexo());
                        System.out.println("Peso actual: " + mascota.getPeso_actual());
                        System.out.println("Microchip: " + mascota.getMicrochip());
                        System.out.println("Tatuaje: " + mascota.getTatuaje());
                        System.out.println("URL Foto: " + mascota.getUrl_foto());
                        System.out.println("Alergias: " + mascota.getAlergias());
                        System.out.println("Condiciones preexistentes: " + mascota.getCondiciones_preexistentes());
                        System.out.println("Fecha de registro: " + mascota.getFecha_registro());
                        System.out.println("Activo: " + (mascota.getActivo() ? "Sí" : "No"));
                        System.out.println("--------------------------------------");
                    }else{
                        System.out.println("No hay ninguna mascota con ese ID.");
                    }
                    
                    break;
                
                case 5:
                    System.out.println("Volviendo al menu principal....");
                    return;
                    
                default:
                    System.out.println("Opcion imvalida. Intente nuevamente.");
            }
        } while (true);
    }
}
