package Nebrija.Javafx1erTrimestre;

import Nebrija.Javafx1erTrimestre.dao.Producto;
import Nebrija.Javafx1erTrimestre.javafxconexion.SQLconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VistaUsuarioController implements Initializable {

    @FXML
    private Button btnVolverLogin;

    @FXML
    private TableColumn<Producto,String> clmAlto;

    @FXML
    private TableColumn<Producto,String> clmAncho;

    @FXML
    private TableColumn<Producto,String> clmCategoria;

    @FXML
    private TableColumn<Producto,String> clmDescripcion;

    @FXML
    private TableColumn<Producto,String> clmEstado;

    @FXML
    private TableColumn<Producto,Number> clmId;

    @FXML
    private TableColumn<Producto,String> clmMarca;

    @FXML
    private TableColumn<Producto,String> clmNombre;

    @FXML
    private TableColumn<Producto,String> clmPrecio;

    @FXML
    private TableColumn<Producto,String> clmTalla;

    @FXML
    private TableView<Producto> tablaUsuario;

    private ObservableList<Producto> lista;

    private SQLconnection conexion;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaUsuario.setEditable(true);

        conexion = new SQLconnection();
        conexion.conectar();

        //Inicializamos la lista
        lista = FXCollections.observableArrayList();
        //Llenamos la lista
        Producto.llenarDatosProducto(conexion.getCx(), lista);
        //Enlazar lista con TableView
        tablaUsuario.setItems(lista);
        //Enlazar las columnas con los atributos
        clmId.setCellValueFactory(new PropertyValueFactory<Producto, Number>("id"));
        clmNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        clmTalla.setCellValueFactory(new PropertyValueFactory<Producto, String>("talla"));
        clmDescripcion.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        clmMarca.setCellValueFactory(new PropertyValueFactory<Producto, String>("marca"));
        clmCategoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
        clmEstado.setCellValueFactory(new PropertyValueFactory<Producto, String>("estado"));
        clmAncho.setCellValueFactory(new PropertyValueFactory<Producto, String>("medidaAncho"));
        clmAlto.setCellValueFactory(new PropertyValueFactory<Producto, String>("medidaAlto"));
        clmPrecio.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));

        //Paramos la conexion con la base de datos
        conexion.cerrarConexion();
    }

    @FXML
    void volverAlogin(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

}