package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO implements DAO<Departamento> {
    @Override
    public Departamento get(long id) {
        return new Departamento();
    }

    @Override
    public List<Departamento> getAll(Connection conn) {
        List<Departamento> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM DEPT;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<>(totalRows);
            while (rs.next()) {
                int codDEpt = rs.getInt(1);
                String nombre = rs.getString(2);
                List<Profesor> profesors = new ArrayList<>();

                lista.add(new Departamento(codDEpt, nombre, profesors));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
