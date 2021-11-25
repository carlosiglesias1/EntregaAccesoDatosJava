package controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import model.AlumnoDAO;
import model.Asignatura;
import model.Alumno;
import model.AsignaturaDAO;
import model.Matricula;
import model.MatriculaDAO;
import model.Profesor;
import model.ProfesorDAO;
import view.Errores;
import view.Menu;

public class AlumnoController {
    private AlumnoController() {
        // Empty
    }

    /**
     * @param conn
     * @param menu
     * @param alumnoDAO
     */
    private static void crearAlumno(Connection conn, Menu menu, AlumnoDAO alumnoDAO) {
        Alumno newAlumno = menu.inputAlumnoFields();
        if (alumnoDAO.insert(conn, newAlumno) != -1) {
            List<Alumno> alumnos = alumnoDAO.getAll(conn);
            menu.showAlumnos(alumnos);
        }
    }

    /**
     * @param conn
     * @param menu
     * @param alumnoDAO
     */
    private static void borrarAlumno(Connection conn, Menu menu, AlumnoDAO alumnoDAO) {
        List<Alumno> alumnos = alumnoDAO.getAll(conn);
        int index = menu.selectAlumno(alumnos);
        alumnoDAO.delete(conn, alumnos.get(index));
    }

    /**
     * @param conn
     * @param menu
     * @param alumnoDAO
     * @param alumnos
     */
    private static void actualizarAlumno(Connection conn, Menu menu, AlumnoDAO alumnoDAO, List<Alumno> alumnos) {
        int index = menu.selectAlumno(alumnos);
        Alumno alumno = menu.inputAlumnoFields();
        alumno.setIdAlumno(index);
        alumnoDAO.update(conn, alumno);
    }

    /**
     * @param conn
     * @param menu
     * @param alumnos
     * @param asignaturas
     */
    private static void matricularAlumno(Connection conn, Menu menu, List<Alumno> alumnos, List<Asignatura> asignaturas,
            List<Profesor> profesors) {
        int index = menu.selectAlumno(alumnos);
        if (!asignaturas.isEmpty() && !profesors.isEmpty()) {
            int index2 = menu.selectAsignatura(asignaturas);
            MatriculaDAO mDao = new MatriculaDAO();
            Matricula[] matriculas = new Matricula[menu.setNumMatriculas()];
            for (int i = 0; i < matriculas.length; i++) {
                matriculas[i] = new Matricula(-1, alumnos.get(index), asignaturas.get(index2),
                        profesors.get(menu.selectProfesor(profesors)));
            }
            int[] result = mDao.insert(conn, matriculas);
            for (int i = 0; i < result.length; i++) {
                if (result[i] == Statement.EXECUTE_FAILED) {
                    Errores.ShowError(Errores.ErrorTypes.DEFAULT.ordinal());
                    break;
                }
                alumnos.get(i).getAsignaturas().add(matriculas[i].getAsignatura());
            }
        } else {
            if (Errores.noAsignaturas()) {
                AsignaturaController.crearAsignatura(conn, menu, new AsignaturaDAO());
                ProfesorController.crearProfesor(conn, menu, new ProfesorDAO());
                matricularAlumno(conn, menu, alumnos, asignaturas, profesors);
            }
        }
    }

    /**
     * @param conn
     */
    public static void gestionarAlumnos(Connection conn) {
        Menu menu = new Menu();
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        switch (menu.alumnoOPtions()) {
        case 1:
            crearAlumno(conn, menu, alumnoDAO);
            break;
        case 2:
            borrarAlumno(conn, menu, alumnoDAO);
            break;
        case 3:
            actualizarAlumno(conn, menu, alumnoDAO, alumnoDAO.getAll(conn));
            break;
        case 4:
            matricularAlumno(conn, menu, alumnoDAO.getAll(conn), new AsignaturaDAO().getAll(conn),
                    new ProfesorDAO().getAll(conn));
            break;
        case 5:
            int index = menu.selectAlumno(alumnoDAO.getAll(conn));
            menu.showAsignaturasAlumno(alumnoDAO.getAll(conn).get(index));
            break;
        default:
            menu.showAlumnos(alumnoDAO.getAll(conn));
            break;
        }
    }
}
