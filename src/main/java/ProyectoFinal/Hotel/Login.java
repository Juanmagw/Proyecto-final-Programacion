package ProyectoFinal.Hotel;

import ProyectoFinal.Hotel.model.Usuario;
import ProyectoFinal.Hotel.model.dao.UsuarioDAO;
import ProyectoFinal.Hotel.utils.Utils;
import ProyectoFinal.Hotel.utils.popUpWindows.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class Login {

    private UsuarioDAO misUsuarios=new UsuarioDAO();
    private Usuario u=new Usuario();
    @FXML private TextField nick=new TextField();
    @FXML private TextField contraseña=new TextField();

    @FXML private Button iniciar=new Button();
    @FXML private Button registrar=new Button();
    @FXML private Button goBack=new Button();

    @FXML public void iniciar() throws IOException {
        String Nick=nick.getText();
        String Contraseña=contraseña.getText();
        u=new Usuario();
        u.setNick(Nick);
        u.setContraseña(Contraseña);
        if(nick!=null && contraseña!=null && u!=null && misUsuarios.getByDni(u.getDni())!=null){
            App.setRoot("hostController");
        }else{
            Windows.error("Iniciar sesión","Error, los datos no son correctos o el usuario no existe. Pruebe a registrarse.","Iniciando sesión");
            App.setRoot("login");
        }
    }

    @FXML public void registrar() throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void switchToGoBack() throws IOException{
    }
}
