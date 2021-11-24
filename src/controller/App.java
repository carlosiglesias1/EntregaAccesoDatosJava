package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Alumno;
import model.AlumnoDAO;
import model.Asignatura;
import model.AsignaturaDAO;
import model.Matricula;
import model.MatriculaDAO;
import model.Profesor;
import model.tables.Tables;
import res.Conectar;
import view.Errores;
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
                gestionarAsignaturas(cnxn);
                break;
            case 3:
                gestionarDepartamentos(cnxn);
                break;
            case 4:
                gestionarProfesores(cnxn);
                break;
            default:
                System.out.println("Fuck");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    private static void gestionarDepartamentos(Connection cnxn) {
    }

    private static void gestionarAsignaturas(Connection conn) {
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
        List<Asignatura> asignaturas = asignaturaDAO.getAll(conn);
        switch (menu.asignaturaOtions()) {
        case 1:

        }
    }

    private static void gestionarProfesores(Connection cnxn) {

    }

    private static void gestionarAlumnos(Connection conn) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        AsignaturaDAO asignaturas = new AsignaturaDAO();
        List<Alumno> alumnos = alumnoDAO.getAll(conn);
        int index;
        int index2;
        switch (menu.alumnoOPtions()) {
        case 1:
            Alumno newAlumno = menu.inputAlumnoFields();
            if (alumnoDAO.insert(conn, newAlumno)) {
                for (Alumno alumno : alumnos) {
                    System.out.println(alumno.getDni());
                }
            }
            break;
        case 2:
            index = menu.selectAlumno(alumnos);
            Alumno alumno = menu.inputAlumnoFields();
            alumno.setIdAlumno(index);
            alumnoDAO.update(conn, alumno);
            break;
        case 3:
            index = menu.selectAlumno(alumnos);
            if (!asignaturas.getAll(conn).isEmpty()) {
                index2 = menu.selectAsignatura(asignaturas.getAll(conn));
                MatriculaDAO mDao = new MatriculaDAO();
                Matricula[] matriculas = new Matricula[menu.setNumMatriculas()];
                for (int i = 0; i < matriculas.length; i++) {
                    matriculas[i] = new Matricula(-1, alumnos.get(index), asignaturas.getAll(conn).get(index2),
                            new Profesor());
                }
                int[] result = mDao.insert(conn, matriculas);
                for (int i = 0; i < result.length; i++) {
                    if (result[i] == Statement.EXECUTE_FAILED) {
                        Errores.ShowError();
                        break;
                    }
                    alumnos.get(i).getAsignaturas().add(matriculas[i].getAsignatura());
                }
            } else {
                Errores.noAsignaturas();
                // añadir asignatura
            }
            break;
        case 4:
            menu.showAlumnos(alumnos);
            break;
        case 5:
            index = menu.selectAlumno(alumnos);
            menu.showAsignaturasAlumno(alumnos.get(index));
            break;
        }
    }

}