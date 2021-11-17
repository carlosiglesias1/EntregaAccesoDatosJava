package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Alumno;
import model.AlumnoDAO;
import model.tables.Tables;
import res.Conectar;
import view.Menu;

public class App {
    static final String URL = "jdbc:mysql:///proyecto";
    static final String USER = "usuario";
    static final String PASSWORD = "1234";
    static Menu menu = new Menu();

    public static void main(String[] args) throws Exception {
        try {
            Conectar connection = Conectar.create(URL, USER, PASSWORD);
            Connection cnxn = connection.getConnection();
            System.out.println("Estoy conectado");
            int option = 0;
            do {
                option = menu.selectMenu();
                switch (option) {
                case 0:
                    menu.goodBye();
                    break;
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
                    selectTable(cnxn);
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
            } while (option != 0);
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
        }
    }

    private static void selectTable(Connection cnxn) {
        try (Statement st = cnxn.createStatement()) {
            String sql = "SHOW TABLES;";
            ResultSet rs = st.executeQuery(sql);
            switch (menu.selectTable(rs)) {
            case 1:
                gestionarAlumnos(cnxn);
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

    private static void gestionarAlumnos(Connection conn) {
        AlumnoDAO alumnos = new AlumnoDAO();
        switch (menu.alumnoOPtions()) {
        case 1:
            Alumno newAlumno = menu.inputAlumnoFields();
            if (alumnos.insert(conn, newAlumno)) {
                for (Alumno alumno : alumnos.getAll(conn)) {
                    System.out.println(alumno.getDni());
                }
            }
            break;
        case 2:
            int index = menu.selectAlumno(alumnos.getAll(conn));
            
            break;
        case 3:
            break;
        case 4:
            menu.showAlumnos(alumnos.getAll(conn));
            break;
        }
    }

    private static void printDeptOPtions() {
    }

    private static void printProfesorOptions() {

    }

}