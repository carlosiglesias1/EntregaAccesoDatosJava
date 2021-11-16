package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Alumno;
import model.AlumnoDAO;
import model.tables.Tables;
import res.Conectar;
import view.Menu;

public class App {
    static final String URL = "jdbc:mysql:///proyecto";
    static final String USER = "usuario";
    static final String PASSWORD = "1234";

    private static void selectTable(Connection cnxn, Scanner teclado) {
        try (Statement st = cnxn.createStatement()) {
            String sql = "SHOW TABLES;";
            ResultSet rs = st.executeQuery(sql);
            Menu.printTables(rs);
            switch (Integer.parseInt(teclado.nextLine())) {
            case 1:
                gestionarAlumnos(cnxn, teclado);
                break;
            case 2:
                printDeptOPtions();
                break;
            case 3:
                printProfesorOptions();
                break;
            default:
                System.out.println("Fuck");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    private static void printDeptOPtions() {
    }

    private static void printProfesorOptions() {

    }

    private static void gestionarAlumnos(Connection conn, Scanner teclado) {
        AlumnoDAO alumnos = new AlumnoDAO();
        Menu.alumnoOPtions();
        switch (Integer.parseInt(teclado.nextLine())) {
        case 1:
            String dni = teclado.nextLine();
            String nombre = teclado.nextLine();
            String apellidos = teclado.nextLine();
            LocalDate fechaNac = LocalDate.parse(teclado.nextLine());
            alumnos.getAll(conn)
                    .add(new Alumno(alumnos.getAll(conn).size(), dni, nombre, apellidos, fechaNac, new ArrayList<>()));
            for (Alumno alumno : alumnos.getAll(conn)) {
                System.out.println(alumno.getDni());
            }
            break;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        try {
            Conectar connection = Conectar.create(URL, USER, PASSWORD);
            Connection cnxn = connection.getConnection();
            System.out.println("Estoy conectado");
            Menu.printPrompt();
            int option = Menu.selectMenu(Integer.parseInt(teclado.nextLine()));
            switch (option) {
            case 1:
                int[] crearTablas = Tables.createTables(cnxn);
                for (int i = 0; i < crearTablas.length; i++) {
                    if (crearTablas[i] == Statement.EXECUTE_FAILED) {
                        System.out.println("Algo no ha ido como debería: " + i);
                        break;
                    }
                }
                break;
            case 2:
                selectTable(cnxn, teclado);
                break;
            case 3:
                int[] dropTables = Tables.dropTables(cnxn);
                for (int i = 0; i < dropTables.length; i++) {
                    if (dropTables[i] == Statement.EXECUTE_FAILED) {
                        System.out.println("Algo no salió como debería: " + i);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Opción incorrecta");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
        }
        teclado.close();
    }
}
