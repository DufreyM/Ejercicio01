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

public class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletos;
    private int presupuestoMaximo;
    private int boletosComprados;
    private Localidad localidadAsignada;

    /**
     * @param nombre
     * @param email
     * @param cantidadBoletos
     * @param presupuestoMaximo
     */
    public Comprador(String nombre, String email, int cantidadBoletos, int presupuestoMaximo) { //Valores iniciales 
        this.nombre = nombre;
        this.email = email;
        this.cantidadBoletos = cantidadBoletos;
        this.presupuestoMaximo = presupuestoMaximo;
        this.boletosComprados = 0;
        this.localidadAsignada = null;
    }

    /**
     * 
     */
    public void realizarCompra() {
        if (localidadAsignada == null) { //Verifica si ya se tiene la localidad al azar. 
            System.out.println("Error: No se ha asignado una localidad.");
            return;
        }

        int boletosDisponibles = localidadAsignada.disponibilidad();
        int boletosComprar = Math.min(boletosDisponibles, cantidadBoletos);

        if (boletosComprar <= 0) { //Verificar si hay boletos suficientes. 
            System.out.println("No hay boletos disponibles para comprar.");
            return;
        }

        int costoTotal = boletosComprar * localidadAsignada.getPrecio(); //Para calcular cuanto se gastaria en el total de boletos y validar después. 

        if (costoTotal > presupuestoMaximo) { //Validación de presupuesto 
            System.out.println("No tiene suficiente presupuesto para comprar estos boletos.");
            return;
        }

        localidadAsignada.venderBoletos(boletosComprar); //Para poder calcular caja después 
        boletosComprados += boletosComprar;
        System.out.println("Compra exitosa. Ha comprado " + boletosComprar + " boletos en la localidad " + localidadAsignada.getNombre());
    }

    /**
     * @return
     */
    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    /**
     * @return
     */
    public int getBoletosComprados() {
        return boletosComprados;
    }

    /**
     * @param localidad
     */
    public void setLocalidadAsignada(Localidad localidad) {
        localidadAsignada = localidad;
    }
}