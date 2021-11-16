package view;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private Menu() {

    }

    public static void printPrompt() {
        System.out.println("Selecciona una opción: \n1.-Genera las tablas\n2.-Operar sobre las tablas\n3.-Borrar las tablas");
    }

    public static void printTables(ResultSet rs) throws SQLException {
        int count = 1;
        while (rs.next()) {
            System.out.println(count + ".- " + rs.getString(1));
            count++;
        }
    }

    public static int selectMenu(int option) {
        switch (option) {
        case 1:
            System.out.println("Creando tablas...");
            return 1;
        case 2:
            System.out.println("Escoje la tabla: ");
            return 2;
        case 3:
            System.out.println("Borrando tablas...");
            return 3;
        default:
            return -1;

        }
    }

    public static void alumnoOPtions(){
        System.out.println("\t1.-Crear un alumno\n2.-Editar un alumno\n");
    }
}