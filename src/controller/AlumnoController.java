package controller;

import java.sql.Connection;
import java.sql.SQLException;
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
     * Llama al método insert del DAO para generar un nuevo alumno
     * 
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
     * Llama al método delete del DAO para borrar un alumno
     * 
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
     * Llama al método actualizar del DAO para sobreescribir un alumno en una
     * posición
     * 
     * @param Conexion
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
     * Inserta una matricula 
     * 
     * @param Conexion
     * @param matriculas
     * @param alumnos
     */
    private static void insertMatricula(Connection conn, Matricula[] matriculas, List<Alumno> alumnos) {
        try {
            conn.setAutoCommit(false);
            int[] result = new MatriculaDAO().insert(conn, matriculas);
            for (int i = 0; i < result.length; i++) {
                if (result[i] == Statement.EXECUTE_FAILED) {
                    Errores.showError(Errores.ErrorTypes.DEFAULT.ordinal());
                    conn.rollback();
                    break;
                }
                conn.commit();
                conn.setAutoCommit(true);
                alumnos.get(i).getAsignaturas().add(matriculas[i].getAsignatura());
            }
        } catch (SQLException e) {
            Errores.sqlError(e);
        }
    }

    /**
     * Matricula un alumno en una o varias asignaturas, para esto requiere de la
     * materia, un alumno y un profesor; sino hay profesores, el sistema recomendará
     * crear uno
     * 
     * @param Conexion
     * @param menu
     * @param alumnos
     * @param asignaturas
     */
    private static void matricularAlumno(Connection conn, Menu menu, List<Alumno> alumnos, List<Asignatura> asignaturas,
            List<Profesor> profesors) {
        int index = menu.selectAlumno(alumnos);
        if (!asignaturas.isEmpty() && !profesors.isEmpty()) {
            Matricula[] matriculas = new Matricula[menu.setNumMatriculas()];
            for (int i = 0; i < matriculas.length; i++) {
                int index2 = menu.selectAsignatura(asignaturas);
                matriculas[i] = new Matricula(-1, alumnos.get(index - 1), asignaturas.get(index2 - 1),
                        profesors.get(menu.selectProfesor(profesors) - 1));
            }
            insertMatricula(conn, matriculas, alumnos);
        } else {
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
            ProfesorDAO profesorDAO = new ProfesorDAO();
            if (Errores.noAsignaturas(menu.getTeclado())) {
                AsignaturaController.crearAsignatura(conn, menu, asignaturaDAO);
                if (Errores.noProfes(menu.getTeclado()))
                    ProfesorController.crearProfesor(conn, menu, profesorDAO);
                matricularAlumno(conn, menu, alumnos, asignaturaDAO.getAll(conn), profesorDAO.getAll(conn));
            }
        }
    }

    /**
     * Menu para gestionar los alumnos de la bd
     * 
     * @param Conexion
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
            menu.showAsignaturasAlumno(alumnoDAO.getAll(conn).get(index-1));
            break;
        default:
            menu.showAlumnos(alumnoDAO.getAll(conn));
            break;
        }
    }
}
