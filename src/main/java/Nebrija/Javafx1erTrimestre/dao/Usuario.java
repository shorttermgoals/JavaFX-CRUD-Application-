package Nebrija.Javafx1erTrimestre.dao;

import Nebrija.Javafx1erTrimestre.App;
import Nebrija.Javafx1erTrimestre.javafxconexion.SQLconnection;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.sql.*;

public class Usuario implements Serializable {

    private SQLconnection conexion = new SQLconnection();

    private static final long serialVersionUID = 1L;
    private String mail,pwd;
    private int permiso;

    public Usuario() {
        this.mail = mail;
        this.pwd = pwd;
        this.permiso = permiso;
    }

    public Usuario(String mail,String pwd){
        super();
        this.mail = mail;
        this.pwd = pwd;
        this.permiso = permiso;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPermiso() {
        return permiso;
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }

    public void grabarUsuario(String mail, String pwd,int permiso){
        Connection conexion = null;
        Statement sentenciaSQL = null;
        ResultSet rs;
        int resultado = 0;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
            sentenciaSQL = conexion.createStatement();
            sql = "insert into usuarios (mail,pwd,permiso) values ('" + mail + "','" + pwd + "','" + permiso + "')";
            resultado = sentenciaSQL.executeUpdate(sql);
            if (resultado >= 1) {
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Usuario agregado");
                mensaje.setContentText("Se ha insertado correctamente.");
                mensaje.show();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // System.out.println("Error");
        } finally {
            try {
                if (sentenciaSQL != null) {
                    sentenciaSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public boolean comparacionDatos(String mail, String pwd){
        Connection conexion = null;
        Statement sentenciaSQL = null;
        ResultSet rs;
        boolean resultado = false;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
            sentenciaSQL = conexion.createStatement();
            sql = "SELECT * FROM usuarios WHERE mail= '" + mail + "' AND pwd= '" + pwd + "'";
            ResultSet resul = sentenciaSQL.executeQuery(sql);
            resultado = resul.next();
            if (resultado == true) {
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Inicio de sesión");
                mensaje.setContentText("Se ha iniciado sesión correctamente correctamente.");
                mensaje.show();
            }else{
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Inicio de sesión fallido");
                mensaje.setContentText("No se ha iniciado sesión correctamente correctamente.");
                mensaje.show();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // System.out.println("Error");
        } finally {
            try {
                if (sentenciaSQL != null) {
                    sentenciaSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultado;
    }

   public boolean comprobarPermiso(String mail,String pwd){
       Connection conexion = null;
       Statement sentenciaSQL = null;
       ResultSet rs;
       boolean resultado = false;
       String sql = "";
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
           sentenciaSQL = conexion.createStatement();
           sql = "SELECT * FROM usuarios WHERE mail= '" + mail + "' AND pwd= '" + pwd + "' AND permiso = 1";
           ResultSet resul = sentenciaSQL.executeQuery(sql);
           resultado = resul.next();
       } catch (SQLException | ClassNotFoundException ex) {
           ex.printStackTrace();
           // System.out.println("Error");
       } finally {
           try {
               if (sentenciaSQL != null) {
                   sentenciaSQL.close();
               }
               if (conexion != null) {
                   conexion.close();
               }
           } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
       return resultado;
   }

}


