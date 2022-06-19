package ProyectoFinal.Hotel.interfaces;

import ProyectoFinal.Hotel.model.Usuario;

import java.time.LocalDate;

public interface IReserva {
    Usuario getCliente();
    void setCliente(Usuario c);
    LocalDate getFechaEntrada();
    void setFechaEntrada(LocalDate fechaEntrada);
    LocalDate getFechaSalida();
    void setFechaSalida(LocalDate fechaSalida);
    String toString();
    int hashCode();
    boolean equals(Object obj);
}
