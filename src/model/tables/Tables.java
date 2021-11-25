package model.tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    private Tables() {
    }

    
    /** 
     * @param conn
     * @return int[]
     * @throws SQLException
     */
    public static int[] createTables(Connection conn) throws SQLException {
        try (Statement s = conn.createStatement()) {
            s.addBatch(
                    "CREATE TABLE IF NOT EXISTS DEPT(CODDEPT INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(50) NOT NULL);");
            s.addBatch(
                    "CREATE TABLE IF NOT EXISTS PROFESOR(CODPROF INT AUTO_INCREMENT PRIMARY KEY, DNI CHAR (9) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, APELLIDOS VARCHAR (100), DEPARTAMENTO INT REFERENCES DEPT(CODDEPT));");

            s.addBatch(
                    "CREATE TABLE IF NOT EXISTS ALUMNO(IDALUMNO INT AUTO_INCREMENT PRIMARY KEY, DNI CHAR (9) NOT NULL, NOMBRE VARCHAR (50) NOT NULL, APELLIDOS VARCHAR (40), FECHA DATE);");
            s.addBatch(
                    "CREATE TABLE IF NOT EXISTS ASIGNATURA(CODASIGN INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(50), CURSO VARCHAR (5))");
            s.addBatch(
                    "CREATE TABLE IF NOT EXISTS MATRICULA(IDMATRICULA INT AUTO_INCREMENT PRIMARY KEY, ALUMNO INT REFERENCES ALUMNO(IDALUMNO) ON DELETE CASCADE ON UPDATE NO ACTION, ASIGNATURA INT REFERENCES ASIGNATURA(CODASIGN) ON DELETE CASCADE, PROFESOR INT REFERENCES PROFESOR(CODPROF) ON DELETE CASCADE);");
            s.addBatch(
                    "CREATE VIEW viewAll AS (   SELECT     idmatricula,     idAlumno,     a.dni as 'dni alumno',     a.nombre as 'Nombre alumno',     a.apellidos,     a.fecha,     codasign,     ag.nombre as 'Nombre asignatura',     curso,     codprof,     p.dni as 'profe dni',     p.nombre as 'Nombre profe',     p.apellidos as 'apellidos profe'   FROM     Matricula m     inner join alumno a on m.alumno = a.idAlumno     inner join asignatura ag on ag.codasign = m.asignatura     inner join profesor p on p.codprof = m.profesor );");
            return s.executeBatch();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    
    /** 
     * @param conn
     * @return int[]
     * @throws SQLException
     */
    public static int[] dropTables(Connection conn) throws SQLException {
        try (Statement s = conn.createStatement()) {
            s.addBatch("DROP TABLE DEPT;");
            s.addBatch("DROP TABLE PROFESOR;");
            s.addBatch("DROP TABLE ALUMNO;");
            s.addBatch("DROP TABLE ASIGNATURA;");
            s.addBatch("DROP TABLE MATRICULA;");
            s.addBatch("DROP VIEW VIEWALL;");
            return s.executeBatch();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
