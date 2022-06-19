package ProyectoFinal.Hotel;

import ProyectoFinal.Hotel.model.Usuario;
import ProyectoFinal.Hotel.model.dao.UsuarioDAO;
import ProyectoFinal.Hotel.utils.Utils;
import ProyectoFinal.Hotel.utils.popUpWindows.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Register {

    private UsuarioDAO misUsuarios=new UsuarioDAO();
    private Usuario u= new Usuario();

    @FXML private TextField dni=new TextField();
    @FXML private TextField nombre=new TextField();
    @FXML private TextField apellido=new TextField();
    @FXML private TextField nick=new TextField();
    @FXML private TextField contraseña=new TextField();
    @FXML private TextField correo=new TextField();
    @FXML private TextField edad=new TextField();

    @FXML private Button registrarse=new Button();
    @FXML private Button goBack=new Button();

    @FXML private void register(){
        String Dni=dni.getText();
        String Nombre=nombre.getText();
        String Apellido=apellido.getText();
        String Nick=nick.getText();
        String Contraseña= contraseña.getText();
        String Correo=correo.getText();
        int Edad=Integer.parseInt(edad.getText());
        u=new Usuario(Dni,Nombre,Apellido,Nick,Contraseña,Correo,Edad);
        if(u!=null){
            misUsuarios.insert(u);
            Windows.alert("Registrarse","El usuario ha sido registrado con éxito","Guardando datos de nuevo usuario");
            try {
                App.setRoot("register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Windows.error("Registrarse","El usuario no ha sido registrado con éxito, observe si los datos ingresados son correctos","Guardando datos de nuevo usuario");
        }
    }

    @FXML
    private void switchToGoBack() throws IOException {
        App.setRoot("primaryController");
    }
}
