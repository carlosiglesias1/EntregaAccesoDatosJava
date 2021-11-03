import java.sql.Connection;
import java.sql.SQLException;

import res.Conectar;

public class App {
    static final String url = "jdbc:mysql:///carlos";
    static final String user = "usuario";
    static final String password = "1234";

    public static void main(String[] args) throws Exception {

        try {
            Conectar connection = Conectar.create(url, user, password);
            Connection cnxn = connection.getConnection();
            System.out.println("Estoy conectado");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }
}
