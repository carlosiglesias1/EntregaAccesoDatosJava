package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import model.AlumnoDAO;
import model.ProfesorDAO;
import res.Conectar;
import view.Menu;

public class App {
    static final String URL = "jdbc:mysql:///proyecto";
    static final String USER = "usuario";
    static final String PASSWORD = "1234";

    private static boolean crearTablas(Connection cnxn) {
        try {
            AlumnoDAO tablaAlumno = new AlumnoDAO();
            ProfesorDAO tablaProfesor = new ProfesorDAO();
            return tablaAlumno.createTable(cnxn) && tablaProfesor.createTable(cnxn) ;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        try {
            Conectar connection = Conectar.create(URL, USER, PASSWORD);
            Connection cnxn = connection.getConnection();
            System.out.println("Estoy conectado");
            Menu.printPrompt();
            int option = Menu.selectMenu(Integer.parseInt(teclado.nextLine()));
            switch (option) {
            case 1:
                if (crearTablas(cnxn)) {
                    System.out.println("Tablas creada con éxito");
                } else {
                    System.out.println("Las tablas ya existen");
                }
                break;
            case 2:
                    
                break;
            default:
                System.out.println("Opción incorrecta");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        teclado.close();
    }
}
