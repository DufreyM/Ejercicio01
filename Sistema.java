/**
 * Nombre del programa: Ejercicio 01 - 23648
 * Descripción: Taylor Swift Era - Programa beta boletos 
 * Proyecto realizado para la clase de Programación orientada a objetos
 * Autor: Leonardo Dufrey Mejía Mejía
 * Fecha de creación: 11 de agosto de 2023
 * Fecha de última modificación: 12 de agosto de 2023
 * Fuentes de información: [crédito a toda fuente de información que haya aportado al desarrollo del programa]
 * Paúl Reyes. (2021, May 3). Uso básico de Listas en Java [Video]. YouTube. https://www.youtube.com/watch?v=yHFacwFar0A
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random; 

public class Sistema {
    private List<Localidad> localidades; //Crear lista de las localidades. 
    private Comprador compradorActivo;
    private Scanner scanner;
    /**
     * 
     */
    public Sistema() {
        localidades = new ArrayList<>();
        localidades.add(new Localidad("Localidad 1", 100, 20)); //Localidad más económica
        localidades.add(new Localidad("Localidad 5", 500, 20)); //Localiadad media
        localidades.add(new Localidad("Localidad 10", 1000, 20)); //Localidad más lujosa
        compradorActivo = null;
        scanner = new Scanner(System.in);
    }

    /**
     * @param nombre
     * @param email
     * @param cantidadBoletos
     * @param presupuestoMaximo
     */
    public void nuevoComprador(String nombre, String email, int cantidadBoletos, int presupuestoMaximo) { //Crea el comprador 
        compradorActivo = new Comprador(nombre, email, cantidadBoletos, presupuestoMaximo);
        System.out.println("Nuevo comprador registrado: " + nombre); 
    }

    /**
     * 
     */
    public void nuevaSolicitudBoletos() {
        if (compradorActivo == null) {
            System.out.println("Error: No hay comprador registrado."); //Verifica si existe algún comprador para poder comprar boletos
            return;
        }

        Random random = new Random();
        int numeroTicket = random.nextInt(15000) + 1; //Random que genera el ticket para poder comprar los boletos
        int a = random.nextInt(15000) + 1;
        int b = random.nextInt(15000) + 1;

        boolean ticketApto = numeroTicket >= Math.min(a, b) && numeroTicket <= Math.max(a, b); //Devolver true o false según la condición. 

        if (ticketApto) {
            System.out.println("Su ticket es apto para comprar boletos.");
            int localidadIndex = random.nextInt(localidades.size()); // Se le asigna una localidad al azar dentro de las tres existentes. 
            Localidad localidadSeleccionada = localidades.get(localidadIndex); 
            compradorActivo.setLocalidadAsignada(localidadSeleccionada);
            System.out.println("Se le ha asignado la localidad: " + localidadSeleccionada.getNombre());
        } else {
            System.out.println("Su ticket no es apto para comprar boletos."); //Notifica al usuario que su ticket no es válido.
            System.out.println("Intentalo de nuevo :'(");
        }
    }

    /**
     * 
     */
    public void consultarDisponibilidadTotal() { //Para colsultar la disponibilidad de todos las localidades. 
        System.out.println("Disponibilidad total de boletos:");
        for (Localidad localidad : localidades) {
            System.out.println(localidad.getNombre() + ": " + localidad.disponibilidad() + " boletos disponibles");
        }
    }

    /**
     * @param localidadNombre
     */
    public void consultarDisponibilidadIndividual(String localidadNombre) { //Consultar la disponibilidad de una localidad especificada. 
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equalsIgnoreCase(localidadNombre)) {
                System.out.println("Disponibilidad de boletos en " + localidad.getNombre() + ": " + localidad.disponibilidad() + " boletos disponibles");
                return;
            }
        }
        System.out.println("Localidad no encontrada."); //Por si el usuario ingresa una localidad o valores que no existen. 
    }

    /**
     * 
     */
    public void reporteCaja() {
        int totalRecaudado = 0;
        System.out.println("Reporte de caja:"); //Reporte de caja según los boletos vendidos. 
        for (Localidad localidad : localidades) {
            int boletosVendidos = (localidad.getCapacidad() - localidad.disponibilidad()) * localidad.getPrecio();
            System.out.println(localidad.getNombre() + ": $" + boletosVendidos);
            totalRecaudado += boletosVendidos;
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
    }

    /**
     * 
     */
    public void ejecutar() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); //Por si se queda un espacio flotando :)

            switch (opcion) { //Switch con los posibles casos según el menú. 
                case 1:
                    System.out.print("Ingrese el nombre del comprador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el correo electrónico del comprador: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de boletos que desea comprar: ");
                    int cantidadBoletos = scanner.nextInt();
                    System.out.print("Ingrese su presupuesto máximo: ");
                    int presupuestoMaximo = scanner.nextInt();
                    nuevoComprador(nombre, email, cantidadBoletos, presupuestoMaximo);
                    break;
                case 2:
                    nuevaSolicitudBoletos();
                    if (compradorActivo == null) {
                        System.out.println("Error: No hay comprador registrado.");
                        break;
                    }
                    compradorActivo.realizarCompra();
                    break;
                case 3:
                    consultarDisponibilidadTotal();
                    break;
                case 4:
                    System.out.println("Ingrese el nombre de la localidad para consultar disponibilidad: ");
                    System.out.println("Las localidades son:");
                    System.out.println("Localidad 1"); 
                    System.out.println("Localidad 5");
                    System.out.println("Localidad 10"); 
                    String localidadConsulta = scanner.nextLine();
                    consultarDisponibilidadIndividual(localidadConsulta);
                    break;
                case 5:
                    reporteCaja();
                    break;
                case 6: //Culmina el programa
                    continuar = false;
                    break;
                
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 6."); //Verifica si el usuario ingresa un caso válido
                    break;
            }
        }

        System.out.println("Gracias por usar el sistema. ¡Hasta luego!"); //Despide al usuario
    }

    /**
     * 
     */
    private void mostrarMenu() { //Imprime el menú
        System.out.println("\nMenú:");
        System.out.println("1. Nuevo comprador");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad individual");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
}