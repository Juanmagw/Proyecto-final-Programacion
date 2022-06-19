package ProyectoFinal.Hotel.model.dao;

import ProyectoFinal.Hotel.interfaces.dao.IReservaDAO;
import ProyectoFinal.Hotel.model.Reserva;
import ProyectoFinal.Hotel.utils.Connect;
import ProyectoFinal.Hotel.utils.Errores;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;



public class ReservaDAO implements IReservaDAO {


	public Connection miCon=Connect.getConnect();

	public Collection<Reserva> misReservas;

	public boolean insert(Reserva r) {
		boolean result = false;
		String sql = "Insert into reserva (dni,fechaEntrada,fechaSalida) values(?,?,?)";
		try {
			PreparedStatement ps = miCon.prepareStatement(sql);
			ps.setString(3, r.getDniCliente());
			ps.setDate(4, Date.valueOf(r.getFechaEntrada()));
			ps.setDate(5, Date.valueOf(r.getFechaSalida()));
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Errores.logWarning(e+"");
		}
		return result;
	}

	public Collection<Reserva> getAll() {
		Collection<Reserva> result = new HashSet<Reserva>();
		String sql = "Select * from reserva";
		try {
			Statement st = miCon.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Reserva aux = new Reserva();
				aux.setDniCliente(rs.getString("dni"));
				aux.setFechaEntrada(rs.getDate(3).toLocalDate());
				aux.setFechaSalida(rs.getDate(4).toLocalDate());
				result.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Errores.logWarning(e+"");
		}
		return result;
	}

	public Reserva getByDni(String dni) {
		Reserva aux=null;
		String sql = "Select * from reserva where dni=?";
		try {
			PreparedStatement ps = miCon.prepareStatement(sql);
			ps.setString(2,dni);
			ResultSet rs = ps.executeQuery();
			aux=new Reserva();
			rs.next();
			aux.setDniCliente(rs.getString(2));
			aux.setFechaEntrada(rs.getDate(3).toLocalDate());
			aux.setFechaSalida(rs.getDate(4).toLocalDate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Errores.logWarning(e+"");
			aux=null;
		}
		return aux;
	}

	public boolean update(Reserva r) {
		boolean result = false;
		String sql = "Update reserva set dni=?, fechaEntrada=?, fechaSalida=? where dni=?";
		try {
			PreparedStatement ps = miCon.prepareStatement(sql);
			ps.setString(2, r.getDniCliente());
			ps.setDate(3, Date.valueOf(r.getFechaEntrada()));
			ps.setDate(4, Date.valueOf(r.getFechaSalida()));
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Errores.logWarning(e+"");
			result=false;
		}
		return result;
	}

	public boolean delete(Reserva r) {
		boolean result = false;
		String sql = "Delete from reserva where dni=?";
		try {
			PreparedStatement ps=miCon.prepareStatement(sql);
			ps.setString(2, r.getDniCliente());
			ps.executeUpdate();
			result=true;
		}catch(SQLException e) {
			Errores.logWarning(e+"");
			result=false;
		}
		return result;
	}

	public static void save (ReservaDAO rdao) throws JAXBException {
		JAXBContext contexto;
		String reservasXML = "Reservas.xml";
		try {
			contexto = JAXBContext.newInstance(ReservaDAO.class);
			Marshaller m = contexto.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(rdao, new File(reservasXML));
		} catch (JAXBException e) {
			Errores.logWarning(e + "");
		}
	}
	public static void load (ReservaDAO rdao) {
		JAXBContext contexto;
		Collection<Reserva> misReservas=null;
		String reservasXML="Reservas.xml";
		try {
			contexto = JAXBContext.newInstance(ReservaDAO.class);
			Unmarshaller um = contexto.createUnmarshaller();

			ReservaDAO newrDAO = (ReservaDAO) um.unmarshal( new File(reservasXML) );
			misReservas=newrDAO.getAll();
		} catch (JAXBException e) {
			Errores.logWarning(e+"");
		}
	}

	@Override
	public String toString() {
		return "ReservaDAO{" +
				"misReservas=" + misReservas +
				'}';
	}
}
