package ProyectoFinal.Hotel.model.dao;

import ProyectoFinal.Hotel.interfaces.dao.IUsuarioDAO;
import ProyectoFinal.Hotel.model.Usuario;
import ProyectoFinal.Hotel.model.Reserva;
import ProyectoFinal.Hotel.utils.Connect;
import ProyectoFinal.Hotel.utils.Errores;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    private Collection<Usuario> misUsuarios =new HashSet<Usuario>();

    public Connection miCon;

    public UsuarioDAO(){
        this.miCon = Connect.getConnect();
    }

    /**
     * Metodo que permite insertar un cliente en la base de datos.
     * @param u
     * @return
     */
    public boolean insert(Usuario u) {
        boolean result=false;
        String sql="insert into usuario (dni,nombre,apellido,nick,contraseña,correo,edad) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=miCon.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getNick());
            ps.setString(5, u.getContraseña());
            ps.setString(6, u.getCorreo());
            ps.setInt(7, u.getEdad());
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Errores.logWarning(e+"");
        }
        return result;
    }

    /**
     * Metodo que muestra todos los clientes de la base de datos.
     * @return
     */
    public Collection<Usuario> getAll() {
        Collection<Usuario> result=new HashSet<Usuario>();
        String sql="Select * from usuario";
        try {
            Statement st=miCon.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()) {
                Usuario aux=new Usuario();
                aux.setDni(rs.getString("dni"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellido(rs.getString("apellido"));
                aux.setNick(rs.getString("nick"));
                aux.setContraseña(rs.getString("contraseña"));
                aux.setCorreo(rs.getString("correo"));
                aux.setEdad(rs.getInt("edad"));
                aux.setListaReservas((Collection<Reserva>)new ReservaDAO().getAll());
                result.add(aux);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Errores.logWarning(e+"");
        }
        return result;
    }

    /**
     * Metodo que muestra un solo cliente por su dni.
     * @param dni
     * @return
     */
    public Usuario getByDni(String dni) {
        Usuario aux=null;
        String sql="Select * from usuario where dni=?";
        try {
            PreparedStatement ps=miCon.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs=ps.executeQuery();
            aux=new Usuario();
            rs.next();
            aux.setDni(rs.getString(1));
            aux.setNombre(rs.getString(2));
            aux.setApellido(rs.getString(3));
            aux.setNick(rs.getString(4));
            aux.setContraseña(rs.getString(5));
            aux.setCorreo(rs.getString(6));
            aux.setEdad(rs.getInt(7));
            aux.setListaReservas(new ReservaDAO().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Errores.logWarning(e+"");
            aux=null;
        }
        return aux;
    }

    /**
     * Metodo que edita un cliente de la base de datos.
     * @param u
     * @return
     */
    public boolean update(Usuario u) {
        boolean result=false;
        String sql="Update usuario set dni=?, nombre=?, apellido=?, nick=?, contraseña=?, correo=?, edad=? where dni=?";
        try {
            PreparedStatement ps=miCon.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getNick());
            ps.setString(5, u.getContraseña());
            ps.setString(6, u.getCorreo());
            ps.setInt(7, u.getEdad());
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Errores.logWarning(e+"");
            result=false;
        }
        return result;
    }

    /**
     * Metodo que borra un cliente de la base de datos por su dni.
     * @param u
     * @return
     */
    public boolean delete(Usuario u) {
        boolean result=false;
        String sql="Delete from usuario where dni=?";
        try {
            PreparedStatement ps=miCon.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.executeUpdate();
            result=true;
        }catch(SQLException e) {
            Errores.logWarning(e+"");
            result=false;
        }
        return result;
    }

    public Usuario getUser(String nick, String contraseña) throws SQLException {
        String sql="Select * from usuario where nick=? and contraseña=?";
        List<Object> list=new ArrayList<>();
        list.add(nick);
        list.add(contraseña);
        PreparedStatement ps=miCon.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Usuario u=new Usuario(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("nick"),rs.getString("contraseña"),rs.getString("correo"),rs.getInt("edad"));
            Collection<Reserva> listaReservas= (Collection<Reserva>) new ReservaDAO().getByDni(u.getDni());
            listaReservas.forEach(l->l.setCliente(u));
            u.setListaReservas(listaReservas);
            return u;
        }
        return null;
    }

    public Collection<Reserva> getReservas(Usuario u){
        Collection<Reserva> result=new HashSet<Reserva>();
        String sql="Select fechaEntrada,fechaSalida from reserva where cliente=?";
        try {
            Statement st= miCon.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Reserva r=new Reserva();
                u.setDni(u.getDni());
                r.setCliente(u);
                result.add(r);
            }
        } catch (SQLException e) {
            Errores.logWarning(e+"");
        }
        return result;
    }
}
