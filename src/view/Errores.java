package view;

import java.sql.SQLException;
import java.util.Scanner;

public class Errores {
    private Errores() {
        // Empty constructor
    }

    /**
     * Muestra el error según la opción introducida
     * 
     * @param Opción seleccionada
     */
    public static void showError(int option) {
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
     * Indica que no existen asignaturas y da la opcion de crear un o nuevo
     * 
     * @param un scanner
     * @return boolean: true en caso de querer crear una asignatura y false en caso de no querer
     */
    public static boolean noAsignaturas(Scanner teclado) {
        System.out.println("No existen asignaturas\n\tQuieres dar una de alta?(S/N)");
        char opcion = teclado.nextLine().charAt(0);
        if (opcion == 'S' || opcion == 's') {
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * Indica que no existen profesores y da la posibilidad de crear uno nuevo
     *  
     * @param teclado
     * @return boolean
     */
    public static boolean noProfes(Scanner teclado) {
        System.out.println("No existen profesores\n\tQuieres dar uno de alta?(S/N)");
        char opcion = teclado.nextLine().charAt(0);
        if (opcion == 'S' || opcion == 's')
            return true;
        else
            return false;
    }

    /**
     * Imprime un error sql por pantalla
     * 
     * @param ex
     */
    public static void sqlError(SQLException ex) {
        System.out.println(ex.getSQLState() + ": " + ex.getMessage() + "-");
        ex.printStackTrace();
    }

    public enum ErrorTypes {
        DEFAULT, ERROR1, LOSTMATERIA
    }
}