/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.MascotaController;
import controller.EspecieCONTROLLER;
import dao.RazaDAO;
import enums.Sexo;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Especie;
import model.Mascota;
import model.Razas;

/**
 *
 * @author USUARIO
 */
public class MascotaVIEW {
    private MascotaController controller;
    private Scanner sc;

    public MascotaVIEW() {
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

                    // -------------------- Selección de especie --------------------
                    EspecieCONTROLLER especieController = new EspecieCONTROLLER();
                    List<Especie> especies = especieController.listarEspecie();
                    System.out.println("Selecciona la especie de la mascota:");
                    for (Especie e : especies) {
                        System.out.println(e.getId() + ". " + e.getNombre() + " - " + e.getDescripcion());
                    }
                    int idEspecie = sc.nextInt();
                    sc.nextLine();
                    Especie especieSeleccionada = null;
                    for (Especie e : especies) {
                        if (e.getId() == idEspecie) {
                            especieSeleccionada = e;
                            break;
                        }
                    }

                    // -------------------- Creación de nueva raza --------------------
                    System.out.println("Ingrese el nombre de la nueva raza: ");
                    String nombreRaza = sc.nextLine();
                    System.out.println("Ingrese las características de la raza: ");
                    String caracteristicas = sc.nextLine();
                    Razas raza = new Razas(0, nombreRaza, caracteristicas, especieSeleccionada);
                    RazaDAO razaDAO = new RazaDAO();
                    int idRaza = razaDAO.agregarRaza(raza);

                    // -------------------- Fecha de nacimiento --------------------
                    String fecha = "";
                    Date fecha_nacimiento = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    while (true) {
                        System.out.println("Fecha de nacimiento (formato: yyyy-MM-dd): ");
                        fecha = sc.nextLine();
                        try {
                            fecha_nacimiento = new Date(sdf.parse(fecha).getTime());
                            break;
                        } catch (ParseException e) {
                            System.out.println("Fecha inválida. Por favor, ingrese una fecha válida.");
                        }
                    }

                    System.out.println("Sexo de la mascota (MACHO/HEMBRA): ");
                    Sexo sexo = Sexo.valueOf(sc.nextLine().toUpperCase());

                    System.out.println("Ingrese el peso actual: ");
                    double peso = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Ingrese el microchip: ");
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

                    Mascota m = new Mascota(id_dueno, nombre, idRaza, fecha_nacimiento, sexo, peso,
                            microchip, tatuaje, url_foto, alergias, condiciones, fecha_registro, activo);

                    controller.agregarMascota(m);
                    System.out.println("Mascota agregada correctamente con nueva raza.");
                    break;

                case 2:
                    List<Mascota> lista = controller.listarMascotas();
                    if (lista.isEmpty()) {
                        System.out.println("La lista de mascotas está vacía...");
                    } else {
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
                    if (mascota != null) {
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
                    } else {
                        System.out.println("No hay ninguna mascota con ese ID.");
                    }
                    break;

                case 5:
                    System.out.println("Volviendo al menu principal....");
                    return;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (true);
    }
}
