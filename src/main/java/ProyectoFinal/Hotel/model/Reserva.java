package ProyectoFinal.Hotel.model;

import ProyectoFinal.Hotel.interfaces.IReserva;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class Reserva implements IReserva {

    private Usuario c;
    private String dniCliente;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    /**
     * Constructores.
     */
    public Reserva(){

    }
    public Reserva(Usuario c, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.c = c;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }
    public Reserva(String dniCliente, LocalDate fechaEntrada, LocalDate fechaSalida){
        this.dniCliente = dniCliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    //Getters y Setters.
    @Override
    public Usuario getCliente() {
        return c;
    }
    @Override
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    @Override
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    @Override
    public void setCliente(Usuario c) {
        this.c = c;
    }
    @Override
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    @Override
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    //Crean un código único que no puede duplicarse.
	@Override
	public int hashCode() {
		return Objects.hash(dniCliente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return dniCliente == other.dniCliente;
	}
	
	/**
	 * Salida por pantalla de los atributos de la clase.
	 */
	@Override
	public String toString() {
		return "Reserva [dniCliente=" + dniCliente + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + "]";
	}
}
