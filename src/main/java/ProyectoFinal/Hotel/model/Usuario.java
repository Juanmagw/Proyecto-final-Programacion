package ProyectoFinal.Hotel.model;

import ProyectoFinal.Hotel.interfaces.IUsuario;

import java.util.Collection;
import java.util.Objects;


public class Usuario extends Persona implements IUsuario {

	private String nick;
	private String contraseña;
	private String correo;
	private int edad;
	private Collection<Reserva> listaReservas;

	 public Usuario(){

	 }
	public Usuario(String dni, String nombre, String apellido, String nick, String contraseña, String correo, int edad) {
		super(dni,nombre,apellido);
		this.nick = nick;
		this.contraseña = contraseña;
		this.dni = dni;
		this.correo = correo;
		this.edad = edad;
	}

	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Collection<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(Collection<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	@Override
	public String toString() {
		return "Usuario: " + super.toString() + ", correo = " + correo
				+ ", edad = " + edad + ", lista de reservas = " + listaReservas;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni);
	}
}
