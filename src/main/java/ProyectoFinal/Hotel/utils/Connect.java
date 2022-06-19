package ProyectoFinal.Hotel.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Connect{

	private static Connection con;
	private String file="conexion.xml";
	private static Connect _newInstance;
	
	private Connect() {
		//Cargamos los datos de la conexion del XML.
		DatosConexion dc=load();
		//Establecemos la conexion.
		try {
			load();
			con=DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(),dc.getUsername(),dc.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			con=null;
		}
	}
	public static Connection getConnect() {
		if(_newInstance==null) {
			_newInstance=new Connect();
		}
		return con;
	}
	
	public DatosConexion load() {
		DatosConexion con=new DatosConexion();
		JAXBContext contexto;
		try {
			contexto=JAXBContext.newInstance(DatosConexion.class);
			Unmarshaller um=contexto.createUnmarshaller();
			DatosConexion newR=(DatosConexion) um.unmarshal(new File(file));
			con=newR;
			
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
