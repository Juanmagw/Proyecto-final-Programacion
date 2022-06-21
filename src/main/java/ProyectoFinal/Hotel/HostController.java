package ProyectoFinal.Hotel;

import ProyectoFinal.Hotel.model.Reserva;
import ProyectoFinal.Hotel.model.Usuario;
import ProyectoFinal.Hotel.model.dao.ReservaDAO;
import ProyectoFinal.Hotel.model.dao.UsuarioDAO;
import ProyectoFinal.Hotel.utils.Errores;
import ProyectoFinal.Hotel.utils.popUpWindows.Windows;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class HostController implements Initializable {
    ObservableList<Reserva> listaReservas;
    ReservaDAO misReservas;
    Usuario u;
    Reserva r;

    /**
     * Columnas de la tabla de los clientes.
     */
    @FXML
    private TableView<Reserva> tablaReserva=new TableView<>();
    @FXML private TableColumn<Reserva, String> dniCliente=new TableColumn<>("dniCliente");
    @FXML private TableColumn<Reserva, LocalDate> fechaEntrada=new TableColumn<>("fechaEntrada");
    @FXML private TableColumn<Reserva, LocalDate> fechaSalida=new TableColumn<>("fechaSalida");

    @FXML private TextField buscador;

    /**
     *Lineas de texto para introducir por teclado.
     */
    @FXML private TextField dniClienteEdit;
    @FXML private DatePicker fechaEntradaEdit;
    @FXML private DatePicker fechaSalidaEdit;

    /**
     * Botones para ejecutar acciones.
     */
    @FXML private Button add=new Button();
    @FXML private Button update=new Button();
    @FXML private Button delete=new Button();
    @FXML private Button goBack=new Button();

    /**
     * Inserta un cliente.
     */
    @FXML
    private void insert() {
        misReservas=new ReservaDAO();
        String DniCliente=dniClienteEdit.getText();
        LocalDate FechaEntrada=fechaEntradaEdit.getValue();
        LocalDate FechaSalida=fechaSalidaEdit.getValue();
        r=new Reserva(DniCliente,FechaEntrada,FechaSalida);
        if(r!=null){
            misReservas.insert(r);
            Windows.alert("Añadir reserva","La reserva con DNI: "+r.getDniCliente()+" ha sido añadido con éxito","Añadiendo reserva");
            try {
                App.setRoot("hostController");
            } catch (IOException ex) {
                Errores.logWarning(ex+"");
            }
        }else{
            Windows.error("Añadir reserva","La reserva con DNI: "+r.getDniCliente()+" no existe","Añadiendo reserva");
        }
    }

    /**
     * Lista observable en la que aparecen todos los clientes.
     */
    public void initialize(URL location, ResourceBundle resources) {

        dniCliente.setCellValueFactory(misReservas ->{
            SimpleStringProperty s  = new SimpleStringProperty();
            s.setValue(misReservas.getValue().getDniCliente());
            return s;
        });
        fechaEntrada.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("fechaEntrada"));
        fechaSalida.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("fechaSalida"));

        listaReservas= FXCollections.observableArrayList();
        misReservas=new ReservaDAO();
        listaReservas.addAll(misReservas.getAll());
        tablaReserva.setItems(listaReservas);


        misReservas=new ReservaDAO();
        misReservas.getAll();
        for(Reserva r: misReservas.getAll()){
            r.setCliente(new UsuarioDAO().getByDni(u.getNick()));
        }
    }

    /**
     *Edita un cliente.
     * @throws IOException
     */
    @FXML
    private void update() throws IOException{
        misReservas=new ReservaDAO();
        String Dni=dniClienteEdit.getText();
        LocalDate FechaEntrada=fechaEntradaEdit.getValue();
        LocalDate FechaSalida=fechaSalidaEdit.getValue();
        r.setDniCliente(Dni);
        r.setFechaEntrada(FechaEntrada);
        r.setFechaSalida(FechaSalida);
        if(new ReservaDAO().update(r)){
            Windows.alert("Actualizar reserva","La reserva se ha actualizado con éxito","Actualizando reserva");
            initialize(null,null);
            Errores.logInfo("La reserva se ha actualizado con éxito");
        }else {
            Windows.alert("Actualizar reserva", "La reserva no se ha podido actualizar", "Actualizando reserva");
            Windows.error("Actualizar reserva","La reserva no se ha actualizar","Actualizando reserva");
        }
    }

    /**
     * Elimina un cliente por su dni.
     * @throws IOException
     */
    @FXML
    private void delete() throws IOException{
        misReservas=new ReservaDAO();
        String Dni=dniClienteEdit.getText();
        r=new Reserva();
        r.setDniCliente(Dni);
        if(r!=null){
            misReservas.delete(r);
            Windows.alert("Eliminar reserva","La reserva con DNI: "+r.getDniCliente()+" se ha eliminado con éxito","Eliminando reserva");
            try {
                App.setRoot("hostController");
            } catch (IOException ex) {
                Errores.logWarning(ex+"");
            }
        }else{
            Windows.error("Eliminar reserva","La reserva no se ha eliminado","Eliminando reserva");
        }
    }

    private void cargaDatos(){
        tablaReserva.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reserva>() {
            @Override
            public void changed(ObservableValue<? extends Reserva> observable, Reserva oldValue, Reserva newValue) {
                dniClienteEdit.setText(newValue.getDniCliente());
                fechaEntradaEdit.setValue(newValue.getFechaEntrada());
                fechaSalidaEdit.setValue(newValue.getFechaSalida());
                if(newValue!=null){
                    update.setDisable(false);
                    delete.setDisable(false);
                }
            }
        });
    }

    /**
     * Boton que hace que vuelvas a la ventana anterior.
     * @throws IOException
     */
    @FXML
    private void switchToGoBack() throws IOException{
        App.setRoot("login");
    }
}
