module proyectofinal.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;


    opens ProyectoFinal.Hotel to javafx.fxml;
    opens ProyectoFinal.Hotel.utils to java.xml.bind;
    exports ProyectoFinal.Hotel;
    exports ProyectoFinal.Hotel.utils.popUpWindows;
    opens ProyectoFinal.Hotel.utils.popUpWindows to javafx.fxml;
    opens ProyectoFinal.Hotel.model.dao to java.xml.bind;
    opens ProyectoFinal.Hotel.model;
}