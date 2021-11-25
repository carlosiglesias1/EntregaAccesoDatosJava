package controller;

import java.sql.Connection;
import java.util.List;

import model.Departamento;
import model.DepartamentoDAO;
import view.Menu;

public class DeptController {
    private DeptController(){
        //Empty
    }
    /**
     * @param conn
     */
    public static void gestionarDepartamentos(Connection conn) {
        Menu menu = new Menu();
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
