package Nebrija.Javafx1erTrimestre.dao;

import Nebrija.Javafx1erTrimestre.PrimaryController;
import Nebrija.Javafx1erTrimestre.javafxconexion.SQLconnection;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Producto implements Serializable {

    private SQLconnection conexion = new SQLconnection();


    private static final long serialVersionUID = 1L;
    private String nombre, talla, descripcion, marca, categoria, estado, medidaAncho, medidaAlto, precio;
    private int id;

    public Producto() {
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.estado = estado;
        this.medidaAncho = medidaAncho;
        this.medidaAlto = medidaAlto;
        this.precio = precio;
    }

    public Producto(int id, String nombre, String talla, String descripcion, String marca, String categoria, String estado,
                    String medidaAncho, String medidaAlto, String precio) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.estado = estado;
        this.medidaAncho = medidaAncho;
        this.medidaAlto = medidaAlto;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedidaAncho() {
        return medidaAncho;
    }

    public void setMedidaAncho(String medidaAncho) {
        this.medidaAncho = medidaAncho;
    }

    public String getMedidaAlto() {
        return medidaAlto;
    }

    public void setMedidaAlto(String medidaAlto) {
        this.medidaAlto = medidaAlto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static void llenarDatosProducto(Connection connection,ObservableList<Producto> lista){
        String  sql = "";
        int id=1;
        try {
            Statement instruccion = connection.createStatement();
            sql = "SELECT idNumero,idNombre,idTalla,idDescripcion,idMarca,idCategoria,idEstado,idAncho,idAlto,idPrecio from productos ";
            ResultSet resultado = instruccion.executeQuery(sql);

            while (resultado.next()) {
                lista.add(new Producto(resultado.getInt("idNumero"),resultado.getString("idNombre"), resultado.getString("idTalla"), resultado.getString("idDescripcion"), resultado.getString("idMarca"),resultado.getString("idCategoria"),resultado.getString("idEstado"),resultado.getString("idAncho"),resultado.getString("idAlto"),resultado.getString("idPrecio")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void grabar(String idNombre, String idTalla, String idDescripcion, String idMarca, String idCategoria, String idEstado, String idAncho, String idAlto, String idPrecio) {
        Connection conexion = null;
        Statement sentenciaSQL = null;
        ResultSet rs;
        int resultado = 0;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
            sentenciaSQL = conexion.createStatement();
            sql = "insert into productos (idNombre,idTalla,idDescripcion,idMarca,idCategoria,idEstado,idAncho,idAlto,idPrecio) values ('" + idNombre + "','" + idTalla + "','" + idDescripcion + "','" + idMarca + "','" + idCategoria + "','" + idEstado + "','" + idAncho + "','" + idAlto + "','" + idPrecio + "')";
            resultado = sentenciaSQL.executeUpdate(sql);
            if (resultado >= 1) {
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro agregado");
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

    public void actualizar(int id,String idNombre, String idTalla, String idDescripcion, String idMarca, String idCategoria, String idEstado, String idAncho, String idAlto, String idPrecio) {
        Connection conexion = null;
        Statement sentenciaSQL = null;
        ResultSet rs;
        int resultado = 0;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
            sentenciaSQL = conexion.createStatement();
            sql = "UPDATE productos SET idNombre = '" + idNombre + "', idTalla = '" + idTalla + "', idDescripcion = '" + idDescripcion + "', idMarca = '" + idMarca + "', idCategoria = '" + idCategoria + "', idEstado = '" + idEstado + "', idAncho = '" + idAncho + "', idAlto = '" + idAlto + "', idPrecio = '" + idPrecio + "' WHERE idNumero = " + id + ";";
            //sql = "update productos set (idNombre,idTalla,idDescripcion,idMarca,idCategoria,idEstado,idAncho,idAlto,idPrecio) values ('" + idNombre + "','" + idTalla + "','" + idDescripcion + "','" + idMarca + "','" + idCategoria + "','" + idEstado + "','" + idAncho + "','" + idAlto + "','" + idPrecio + "') WHERE idNumero= " + id;
            resultado = sentenciaSQL.executeUpdate(sql);
            if (resultado >= 1) {
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro agregado");
                mensaje.setContentText("Se ha actualizado correctamente.");
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

    public void eliminar(int id) {
        Connection conexion = null;
        Statement sentenciaSQL = null;
        ResultSet rs;
        int resultado = 0;
        String sql = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "productos_javafx", "root", "");
            sentenciaSQL = conexion.createStatement();
            sql = " delete from productos where idNumero='" + id + "';";
            resultado = sentenciaSQL.executeUpdate(sql);
            if (resultado >= 1) {
                Alert mensaje =  new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro agregado");
                mensaje.setContentText("Se ha eliminado correctamente.");
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

}
