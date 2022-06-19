package ProyectoFinal.Hotel.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

public class Admin extends Persona {

	private Double salario;
	private boolean activo;
	
	public Admin() {
		
	}
	public Admin(String dni, String nombre, String apellido, Double salario, boolean activo) {
		super(dni, nombre, apellido);
		this.salario = salario;
		this.activo = activo;
	}
	
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Admin: " + super.toString() + "salario=" + salario + ", activo=" + activo;
	}
}
