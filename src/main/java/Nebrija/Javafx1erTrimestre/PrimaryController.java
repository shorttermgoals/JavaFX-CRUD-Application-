package Nebrija.Javafx1erTrimestre;

import Nebrija.Javafx1erTrimestre.dao.Producto;
import Nebrija.Javafx1erTrimestre.javafxconexion.SQLconnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private Button btnVolverLogin;

    @FXML
    private TableColumn<Producto, String> clmAlto;

    @FXML
    private TableColumn<Producto, String> clmAncho;

    @FXML
    private TableColumn<Producto, String> clmCategoria;

    @FXML
    private TableColumn<Producto, String> clmDescripcion;

    @FXML
    private TableColumn<Producto, String> clmEstado;

    @FXML
    private TableColumn<Producto, Number> clmId;

    @FXML
    private TableColumn<Producto, String> clmMarca;

    @FXML
    private TableColumn<Producto, String> clmNombre;

    @FXML
    private TableColumn<Producto, String> clmPrecio;

    @FXML
    private TableColumn<Producto, String> clmTalla;

    @FXML
    private TextArea idAlto;

    @FXML
    private TextArea idAncho;

    @FXML
    private TextArea idCategoria;

    @FXML
    private TextArea idDescripcion;

    @FXML
    private TextArea idEstado;

    @FXML
    private TextArea idMarca;

    @FXML
    private TextArea idNombre;

    @FXML
    private TextArea idNumero;

    @FXML
    private TextArea idPrecio;

    @FXML
    private TextArea idTalla;

    @FXML
    private TableView<Producto> tablaDatos;

    private ObservableList<Producto> lista;

    @FXML
    void actualizarDato(ActionEvent event) {
        Producto prenda = new Producto();
        prenda.actualizar(Integer.parseInt(idNumero.getText()),idNombre.getText(),idTalla.getText(),idDescripcion.getText(),idMarca.getText(),idCategoria.getText(),idEstado.getText(),idAncho.getText(),idAlto.getText(),idPrecio.getText());
    }

    @FXML
    void eliminarDato(ActionEvent event) {
        Producto prenda = new Producto();
        prenda.eliminar(Integer.parseInt(idNumero.getText()));
    }

    @FXML
    void grabarDato(ActionEvent event) {
        Producto prenda = new Producto();
        prenda.grabar(idNombre.getText(),idTalla.getText(),idDescripcion.getText(),idMarca.getText(),idCategoria.getText(),idEstado.getText(),idAncho.getText(),idAlto.getText(),idPrecio.getText());
    }

    private SQLconnection conexion;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaDatos.setEditable(true);

        conexion = new SQLconnection();
        conexion.conectar();

        //Inicializamos la lista
        lista = FXCollections.observableArrayList();
        //Llenamos la lista
        Producto.llenarDatosProducto(conexion.getCx(), lista);
        //Enlazar lista con TableView
        tablaDatos.setItems(lista);
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
    void gestionarEventos(){
        tablaDatos.onMouseClickedProperty().set(event -> {
            Producto selectProductos = tablaDatos.getSelectionModel().getSelectedItem();
            if (selectProductos != null) {
                idNumero.setText(String.valueOf(selectProductos.getId()));
                idNombre.setText(String.valueOf(selectProductos.getNombre()));
                idTalla.setText(String.valueOf(selectProductos.getTalla()));
                idDescripcion.setText(String.valueOf(selectProductos.getDescripcion()));
                idMarca.setText(String.valueOf(selectProductos.getMarca()));
                idCategoria.setText(String.valueOf(selectProductos.getCategoria()));
                idEstado.setText(String.valueOf(selectProductos.getEstado()));
                idAncho.setText(String.valueOf(selectProductos.getMedidaAncho()));
                idAlto.setText(String.valueOf(selectProductos.getMedidaAlto()));
                idPrecio.setText(String.valueOf(selectProductos.getPrecio()));
                boton1.setDisable(true);
                boton2.setDisable(false);
                boton3.setDisable(false);
            }
        });
    }

    @FXML
    public void limpiarComponentes(){
        idNumero.setText(null);
        idNombre.setText(null);
        idTalla.setText(null);
        idDescripcion.setText(null);
        idMarca.setText(null);
        idCategoria.setText(null);
        idEstado.setText(null);
        idAncho.setText(null);
        idAlto.setText(null);
        idPrecio.setText(null);
        boton1.setDisable(false);
        boton2.setDisable(true);
        boton3.setDisable(true);
    }

    @FXML
    void volverAlogin(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }
}
