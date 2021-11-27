package controller;

import java.sql.Connection;
import java.util.List;

import model.Departamento;
import model.DepartamentoDAO;
import model.Profesor;
import model.ProfesorDAO;
import view.Menu;

public class ProfesorController {
    private ProfesorController() {
        // Empty
    }

    
    /** 
     * Crea un profesor
     * 
     * @param conn
     * @param menu
     * @param profesorDAO
     */
    public static void crearProfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO) {
        Profesor profesor = menu.profesorFields();
        if (profesorDAO.insert(conn, profesor) != -1) {
            List<Profesor> profesors = profesorDAO.getAll(conn);
            menu.showProfes(profesors);
        }
    }

    
    /** 
     * Borra un profesor de la base de datos
     * 
     * @param conn
     * @param menu
     * @param profesorDAO
     */
    public static void borrarProfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO) {
        int index = menu.selectProfesor(profesorDAO.getAll(conn));
        if (profesorDAO.delete(conn, profesorDAO.getAll(conn).get(index)) != 1) {
            menu.showProfes(profesorDAO.getAll(conn));
        }
    }

    
    /** 
     * Edita el profesor seleccionado
     * 
     * @param conexion
     * @param menu
     * @param profesorDAO
     * @param departamentoDAO
     */
    public static void editarPorfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO,
            DepartamentoDAO departamentoDAO) {
        int index = menu.selectProfesor(profesorDAO.getAll(conn));
        Profesor profesor = menu.profesorFields();
        profesor.setCodProf(index);
        if (departamentoDAO.getAll(conn).isEmpty())
            profesorDAO.update(conn, profesor, new Departamento());
        else {
            Departamento dept = departamentoDAO.getAll(conn).get(menu.selectDept(departamentoDAO.getAll(conn))-1);
            profesorDAO.update(conn, profesor, dept);
        }
    }

    
    /** 
     * Imprime las asignaturas de cada profesor
     * 
     * @param conn
     * @param menu
     * @param profesorDAO
     */
    public static void verAsignaturasProfesor(Connection conn, Menu menu, ProfesorDAO profesorDAO) {
        menu.showAsignaturas(profesorDAO.getSubjects(conn,
                profesorDAO.getAll(conn).get(menu.selectProfesor(profesorDAO.getAll(conn)) - 1)));
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
        case 3:
            editarPorfesor(conn, menu, profesorDAO, new DepartamentoDAO());
            break;
        case 4:
            verAsignaturasProfesor(conn, menu, profesorDAO);
            break;
        default:
            menu.showProfes(profesorDAO.getAll(conn));
            break;
        }
    }
}
