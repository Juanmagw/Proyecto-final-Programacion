package ProyectoFinal.Hotel;

import java.io.IOException;

import ProyectoFinal.Hotel.utils.Errores;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML private Button botonLogin=new Button();
    @FXML private Button botonRegistro=new Button();
    @FXML private Button botonSalir=new Button();

	@FXML
    private void switchToLogin() throws IOException {
        try {
            App.setRoot("login");
        } catch (IOException ex) {
            Errores.logWarning(ex+"");
        }
	}
	@FXML
    private void switchToRegister() throws IOException {
        try {
            App.setRoot("register");
        } catch (IOException ex) {
            Errores.logWarning(ex+"");
        }
	}
    @FXML
    private void switchToExit() {
        System.exit(0);
    }
}
