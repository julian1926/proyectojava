/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.DuenoController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Dueno;

/**
 *
 * @author camper
 */
public class DuenoVIEW {
    private DuenoController controller;
    private Scanner sc;
    
    public DuenoVIEW (){
        int opcion = 0;
        sc = new Scanner(System.in);
        controller = new DuenoController();
        do {            
            System.out.println("---------Gestion de dueños--------");
            System.out.println("1.Agregar");
            System.out.println("2.Listar");
            System.out.println("3.Actualizar estado");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1 :
                    System.out.println("nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Documento de identidad: ");
                    String DNI = sc.nextLine();
                    System.out.println("Direccion: ");
                    String direccion = sc.nextLine();
                    System.out.println("Telefono: ");
                    String telefono = sc.nextLine();
                    System.out.println("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Contato de emergencia: ");
                    String ConEmergencia = sc.nextLine();
                    
                    LocalDateTime fechaRegistro = LocalDateTime.now();  // Fecha actual
                    boolean estado = true;
                    Dueno d = new Dueno(nombre, DNI, direccion, telefono, email, ConEmergencia, fechaRegistro, estado);
                    
                    controller.agregarDueno(d);
                break;
                case 2 :
                    List<Dueno> lista = controller.ListarDuenos();
                    if(lista.isEmpty()){
                        System.out.println("No hay dueños registrados");
                    }else{
                        System.out.println("---------Lista de dueños--------");
                        for (Dueno dueno : lista) {
                            System.out.println("Nombre: "+dueno.getNombreCompleto());
                            System.out.println("Telefono: "+dueno.getTelefono());
                            System.out.println("Dni: "+dueno.getDocumentoIdentidad());
                            System.out.println("Direccion "+dueno.getDireccion());
                            System.out.println("Email: "+dueno.getEmail());
                            System.out.println("Contacto de emergencia: "+dueno.getContactoEmergencia());
                            System.out.println("Fecha de registro: "+dueno.getFechaRegistro());
                            System.out.println("Estado: "+ dueno.getEstado());
                        }
                    }
                break;
                case 3:
                   // Solicitar al usuario el ID del dueño cuyo estado se desea actualizar
                   System.out.println("Dame el ID del dueño cuyo estado deseas actualizar: ");
                   int id = sc.nextInt();
                   sc.nextLine();  // Limpiar el buffer

                   // Llamar al controlador para actualizar el estado del dueño
                   controller.actualizarEstado(id);

                   System.out.println("Estado actualizado.");
                   break;
                
                    
                    
                default:
                    throw new AssertionError();
            }
        } while (true);
    }
}
