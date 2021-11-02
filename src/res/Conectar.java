package res;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private static final Conectar CONNECTION = new Conectar();
    private Connection conexion;

    private Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // variables
            final String url = "jdbc:mysql:///proyecto";
            final String user = "usuario";
            final String password = "1234";
            // establish the connection

            this.conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public static Connection getConnect() {
        return CONNECTION.conexion;
    }

    public void close() {
        try {
            this.CONNECTION.conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
