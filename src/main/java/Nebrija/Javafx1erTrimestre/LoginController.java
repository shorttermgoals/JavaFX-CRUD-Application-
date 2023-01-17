package Nebrija.Javafx1erTrimestre;

import Nebrija.Javafx1erTrimestre.dao.Usuario;
import Nebrija.Javafx1erTrimestre.javafxconexion.SQLconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class LoginController {


    @FXML
    private Button btnIrARegistro;

    @FXML
    private Button btnLogin;

    @FXML
    private TextArea idMailL;

    @FXML
    private TextArea idPwdL;

    @FXML
    void aRegistro(ActionEvent event) throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    void compararDatos(ActionEvent event) throws IOException {
        boolean resultadoLogin = false;
        boolean resultadoPermiso = false;
        Usuario usuario = new Usuario();
        resultadoLogin =  usuario.comparacionDatos(idMailL.getText(),idPwdL.getText());
        resultadoPermiso = usuario.comprobarPermiso(idMailL.getText(),idPwdL.getText());

        if(resultadoLogin == true){
            if(resultadoPermiso == true){
                App.setRoot("IntroducirDatos");
            }else{
                App.setRoot("VistaUsuario");
            }
        }


    }



}
