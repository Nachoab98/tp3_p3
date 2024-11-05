
package model;

public class Reserva {
    private int horaInicio;
    private int horaFin;
    private int precioOfrecido; // Asegúrate de que sea Integer
    private String nombre;
    private static int contadorIDs = 0;
    private int ID;

    public Reserva(int horaInicio, int horaFin, int precioOfrecido, String nombre) {
        if (precioOfrecido < 0) {
            throw new RuntimeException("Precio ofrecido invalido");
        }
        if (!(horaInicio >= 0 && horaFin <= 24 && horaInicio < horaFin)) {
            throw new RuntimeException("Horarios invalidos");
        }
       if (nombre == null || nombre.isEmpty()) {
            nombre = "Anonimo";
        }
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precioOfrecido = precioOfrecido;
        this.nombre = nombre;
        this.ID = contadorIDs++;
    }

    public double precioXHora() {
        return (this.precioOfrecido / (this.horaFin - this.horaInicio));
    }

    public static boolean superponen(Reserva a, Reserva b) {
        return a.getHoraInicio() < b.getHoraFin() && b.getHoraInicio() < a.getHoraFin();
    }

    // GETTERS
    public int getHoraInicio() {
        return this.horaInicio;
    }

    public int getHoraFin() {
        return this.horaFin;
    }

    public int getPrecioOfrecido() {
        return this.precioOfrecido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getID() {
        return this.ID;
    }
}
