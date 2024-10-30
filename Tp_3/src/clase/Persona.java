package clase;

public class Persona {
	public String nombre;
	public int dni;
	public int oferta;
	//capaz hacen falta mas datos
	public Persona(String nombre, int dni, int oferta) {
		this.nombre = nombre;
		this.dni = dni;
		this.oferta = oferta;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getDni() {
		return dni;
	}
	
	public int getOferta() {
		return oferta;
	}

    @Override
	public String toString() {
		return "Persona{" + "nombre=" + nombre + ", dni=" + dni + ", oferta=" + oferta + '}';
	}
    
	public boolean equals(Persona p) {
		return this.dni == p.dni;
	}
    
}
