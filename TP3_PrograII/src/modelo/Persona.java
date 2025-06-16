package modelo;
import interfaces.IPersona;

public class Persona implements IPersona  {
	
	private int dni;
	private String nombre;
	
	public Persona(int dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
	
	@Override
	public int getDni() {
		return dni;
	}
	
	@Override
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + "]";
	}

	
	


	
	

}
