package ProyectoFinal.Hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * controller.Aplicacion de JavaFX en la que se inician las ventanas de diseño grafico.
 */
public class App extends Application {

    private static Scene scene;
    
    public static void main(String[] args) {
    	//new PrimaryController().run();
        launch();
    }

    /**
     * Crea la escena principal.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primaryController"), 1000, 450);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enlaza una escena con otra.
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Crea el enlace entre una clase y un archivo .fxml.
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
}