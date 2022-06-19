package ProyectoFinal.Hotel.utils.popUpWindows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Windows {

	/**
	 * Método que crea una ventana que salta a modo de alerta
	 * @param titulo Nombre de la ventana
	 * @param mensaje Mensaje que se muestra en la ventana
	 * @param subTitulo Titulo dentro de la ventana
	 */
	public static void alert(String titulo, String mensaje, String subTitulo) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle(titulo);
		a.setHeaderText(subTitulo);
		a.setContentText(mensaje);
		a.show();
		Stage s = (Stage) a.getDialogPane().getScene().getWindow();
		s.toFront();
	}

	public static boolean confirm(String titulo, String mensaje, String subTitulo){
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setTitle(titulo);
		a.setHeaderText(subTitulo);
		a.setContentText(mensaje);
		a.show();
		Stage s = (Stage) a.getDialogPane().getScene().getWindow();
		s.toFront();
		Optional<ButtonType> result=a.showAndWait();
		if(result.get()==ButtonType.OK){
			return true;
		}else{
			return false;
		}
	}

	public static void error(String titulo, String mensaje, String subTitulo) {
		Alert a = new Alert(Alert.AlertType.ERROR);
		a.setTitle(titulo);
		a.setHeaderText(subTitulo);
		a.setContentText(mensaje);
		a.show();
		Stage s = (Stage) a.getDialogPane().getScene().getWindow();
		s.toFront();
	}
}
