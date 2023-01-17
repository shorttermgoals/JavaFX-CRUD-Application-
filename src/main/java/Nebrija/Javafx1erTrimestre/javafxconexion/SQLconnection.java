package Nebrija.Javafx1erTrimestre.javafxconexion;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class SQLconnection {
        private Connection cx;
        private String bd = "productos_javafx";
        private String url = "jdbc:mysql://localhost:3306/";
         private String user = "root";
        private String password = "";
        private String driver = "com.mysql.cj.jdbc.Driver";


        public SQLconnection(){

        }

        public Connection getCx() {
                return cx;
        }

        public void setCx(Connection cx) {
                this.cx = cx;
        }

        public void conectar(){

            // metodo para establecer conexion a la base de datos

                try {
                    Class.forName(driver);
                    cx = DriverManager.getConnection(url + bd, user, password);
                    System.out.println("SE A CONECTADO A BD: " + bd);
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("NO SE A CONECTADO A BD: " + bd);
                    // Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, e);
                }

        }

        public void cerrarConexion(){
                try{
                        cx.close();
                }catch (SQLException e){
                        e.printStackTrace();
                }
        }


}