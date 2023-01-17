package Nebrija.Javafx1erTrimestre;
import Nebrija.Javafx1erTrimestre.dao.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Button btnIrALogin;

    @FXML
    private Button btnRegistro;

    @FXML
    private TextArea idMailR;

    @FXML
    private TextArea idPermisoR;

    @FXML
    private TextArea idPwdR;


    @FXML
    void aLogin(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    void grabarUsuarioRegistro(ActionEvent event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.grabarUsuario(idMailR.getText(),idPwdR.getText(), Integer.parseInt(idPermisoR.getText()));
        App.setRoot("Login");
    }

}
