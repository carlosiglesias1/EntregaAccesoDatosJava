package view;

import java.sql.SQLException;
import java.util.Scanner;

public class Errores {
    private Errores() {
        // Empty constructor
    }

    
    /** 
     * @param option
     */
    public static void ShowError(int option) {
        switch (option) {
        case 1:
            System.out.println("Algo no ha ido como debería");
            break;
        case 2:
            System.out.println("No he encontrado la asignatura");
            break;
        default:
            System.out.println("Algo no ha ido como debería");
            break;
        }
    }

    
    /** 
     * @return boolean
     */
    public static boolean noAsignaturas() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("No existen asignaturas\n\tQuieres dar una de alta?(S/N)");
        char opcion = teclado.nextLine().charAt(0);
        if (opcion == 'S' || opcion == 's') {
            teclado.close();
            return true;
        } else {
            teclado.close();
            return false;
        }
    }

    
    /** 
     * @param ex
     */
    public static void sqlError(SQLException ex) {
        System.out.println(ex.getSQLState() + ": " + ex.getMessage() + "-");
        ex.printStackTrace();
    }
    public enum ErrorTypes{
        DEFAULT,
        ERROR1,
        LOSTMATERIA
    }
}