package ProyectoFinal.Hotel.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


public class Persona{

	protected String dni;
	protected String nombre;
    protected String apellido;
    
    public Persona() {
    	
    }
	public Persona(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		return "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
}
