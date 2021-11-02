import java.sql.Connection;
import java.sql.SQLException;

import res.Conectar;

public class App {
    public static void main(String[] args) throws Exception {
        try (Connection connection = Conectar.getConnect()) {
            System.out.println("Estoy conectado");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }
}
