package controller;

import java.sql.Connection;
import java.util.List;
import model.Profesor;
import model.ProfesorDAO;
import view.Menu;

public class ProfesorController {
    private ProfesorController() {
        // Empty
    }

    public static void crearProfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO) {
        Profesor profesor = menu.profesorFields();
        if (profesorDAO.insert(conn, profesor) != -1) {
            List<Profesor> profesors = profesorDAO.getAll(conn);
            menu.showProfes(profesors);
        }
    }

    public static void borrarProfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO) {
        int index = menu.selectProfesor(profesorDAO.getAll(conn));
        if (profesorDAO.delete(conn, profesorDAO.getAll(conn).get(index)) != 1) {
            menu.showProfes(profesorDAO.getAll(conn));
        }
    }

    /**
     * Gestiona todas las interacciones con la tabla de profesores
     * 
     * @param conn
     */
    public static void gestionarProfesores(Connection conn) {
        Menu menu = new Menu();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        switch (menu.profesorOptions()) {
        case 1:
            crearProfesor(conn, menu, profesorDAO);
            break;
        case 2:
            borrarProfesor(conn, menu, profesorDAO);
            break;
        default:
            break;
        }
    }
}
