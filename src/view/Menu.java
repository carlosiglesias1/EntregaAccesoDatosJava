package view;

public class Menu {
    private Menu() {

    }

    public static void printPrompt() {
        System.out.println("Selecciona una opción: \n1.-Genera las tablas\n2.-Operar sobre las tablas");
    }

    public static int selectMenu(int option) {
        int result;
        switch (option) {
        case 1:
            System.out.println("Creando tablas...");
            return 1;
        case 2:
            System.out.println("Escoje la tabla: ");
            return 2;
        default:
            result = -1;
            break;
        }
        return result;
    }
}
