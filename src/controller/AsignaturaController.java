package controller;

import java.sql.Connection;
import java.util.List;

import model.AsignaturaDAO;
import model.Asignatura;
import view.Errores;
import view.Menu;

public class AsignaturaController {
    private AsignaturaController() {
        // Empty
    }

    
    /** 
     * @param conn
     * @param menu
     * @param asignaturaDAO
     */
    static void crearAsignatura(Connection conn, Menu menu, AsignaturaDAO asignaturaDAO) {
        Asignatura asignatura = menu.inputAsignatura();
        asignaturaDAO.insert(conn, asignatura);
    }

    
    /** 
     * @param conn
     * @param menu
     * @param asignaturaDAO
     */
    static void borrarAsignatura(Connection conn, Menu menu, AsignaturaDAO asignaturaDAO) {
        List<Asignatura> asignaturas = asignaturaDAO.getAll(conn);
        try {
            Asignatura materia = asignaturas.get(menu.selectAsignatura(asignaturas) - 1);
            if (asignaturaDAO.delete(conn, materia) != -1) {
                asignaturas = asignaturaDAO.getAll(conn);
                menu.showAsignaturas(asignaturas);
            }
        } catch (IndexOutOfBoundsException ex) {
            Errores.showError(Errores.ErrorTypes.LOSTMATERIA.ordinal());
        }
    }

    
    /** 
     * @param conn
     * @param menu
     * @param asignaturaDAO
     */
    static void actualizarAsignatura(Connection conn, Menu menu, AsignaturaDAO asignaturaDAO) {
        List<Asignatura> asignaturas = asignaturaDAO.getAll(conn);
        try {
            Asignatura materia = asignaturas.get(menu.selectAsignatura(asignaturas) - 1);
            if (asignaturaDAO.update(conn, materia) != -1) {
                asignaturas = asignaturaDAO.getAll(conn);
                menu.showAsignaturas(asignaturas);
            }
        } catch (IndexOutOfBoundsException ex) {
            Errores.showError(Errores.ErrorTypes.LOSTMATERIA.ordinal());
        }
    }

    /**
     * @param conn
     */
    public static void gestionarAsignaturas(Connection conn) {
        Menu menu = new Menu();
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
        switch (menu.asignaturaOtions()) {
        case 1:
            crearAsignatura(conn, menu, asignaturaDAO);
            break;
        case 2:
            borrarAsignatura(conn, menu, asignaturaDAO);
            break;
        case 3:
            actualizarAsignatura(conn, menu, asignaturaDAO);
            break;
        default:
            List<Asignatura> asignaturas = asignaturaDAO.getAll(conn);
            menu.showAsignaturas(asignaturas);
            break;
        }
    }
}
