/**
 * Nombre del programa: Ejercicio 01 - 23648
 * Descripción: Taylor Swift Era - Programa beta boletos 
 * Proyecto realizado para la clase de Programación orientada a objetos
 * Autor: Leonardo Dufrey Mejía Mejía
 * Fecha de creación: 11 de agosto de 2023
 * Fecha de última modificación: 12 de agosto de 2023
 * Fuentes de información: [crédito a toda fuente de información que haya aportado al desarrollo del programa]
 * Paúl Reyes. (2021, May 3). Uso básico de Listas en Java [Video]. YouTube. https://www.youtube.com/watch?v=yHFacwFar0A
 */

 public class Localidad {
    private String nombre;
    private int precio;
    private int capacidad;
    private int boletosVendidos;

    /**
     * @param nombre
     * @param precio
     * @param capacidad
     */
    public Localidad(String nombre, int precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletosVendidos = 0;
    }

    /**
     * @return
     */
    public int disponibilidad() {
        return capacidad - boletosVendidos;
    }

    /**
     * @param cantidad
     */
    public void venderBoletos(int cantidad) {
        boletosVendidos += cantidad;
    }

    /**
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
}
