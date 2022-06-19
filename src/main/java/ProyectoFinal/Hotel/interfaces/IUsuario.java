package ProyectoFinal.Hotel.interfaces;

import ProyectoFinal.Hotel.model.Reserva;

import java.util.Collection;

public interface IUsuario {
    String getCorreo();
    void setCorreo(String correo);
    int getEdad();
    void setEdad(int edad);
    Collection<Reserva> getListaReservas();
    void setListaReservas(Collection<Reserva> listaReservas);
    String toString();
    int hashCode();
    boolean equals(Object obj);
}
