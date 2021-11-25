package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Departamento;
import model.DepartamentoDAO;
import model.tables.Tables;
import res.Conectar;
import view.Errores;
import view.Menu;

public class App {
    static final String URL = "jdbc:mysql:///proyecto";
    static final String USER = "usuario";
    static final String PASSWORD = "1234";
    static Menu menu = new Menu();

    /**
     * @param cnxn
     */
    private static void selectTable(Connection cnxn) {
        try (Statement st = cnxn.createStatement()) {
            String sql = "SHOW TABLES;";
            ResultSet rs = st.executeQuery(sql);
            switch (menu.selectTable(rs)) {
            case 1:
                AlumnoController.gestionarAlumnos(cnxn);
                break;
            case 2:
                AsignaturaController.gestionarAsignaturas(cnxn);
                break;
            case 3:
                gestionarDepartamentos(cnxn);
                break;
            case 4:
                ProfesorController.gestionarProfesores(cnxn);
                break;
            default:
                System.out.println("Fuck");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    private static void createTables(Connection cnxn) {
        try {
            int[] crearTablas = Tables.createTables(cnxn);
            for (int i = 0; i < crearTablas.length; i++) {
                if (crearTablas[i] == Statement.EXECUTE_FAILED) {
                    System.out.println("Algo no ha ido como debería: " + i);
                    break;
                }
            }
        } catch (SQLException ex) {
            Errores.sqlError(ex);
        }
    }

    /**
     * @param args
     * @throws Exception
     */
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
                    createTables(cnxn);
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
            Errores.sqlError(e);
        }
    }

    /**
     * @param conn
     */
    private static void gestionarDepartamentos(Connection conn) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        List<Departamento> depts;
        switch (menu.departamentoOptions()) {
        case 1:
            Departamento dept = menu.inputDepartamento();
            if (departamentoDAO.insert(conn, dept) != -1) {
                depts = departamentoDAO.getAll(conn);
                menu.insertSuccess();
                menu.showDepts(depts);
            }
            break;
        case 2:
            depts = departamentoDAO.getAll(conn);
            Departamento departamento = depts.get(menu.selectDept(depts) - 1);
            departamentoDAO.delete(conn, departamento);
            break;
        case 3:
            depts = departamentoDAO.getAll(conn);
            Departamento selectedDept = depts.get(menu.selectDept(depts) - 1);
            menu.showProfes(selectedDept.getProfesors());
            break;
        default:
            depts = departamentoDAO.getAll(conn);
            menu.showDepts(depts);
            break;
        }
    }

}
