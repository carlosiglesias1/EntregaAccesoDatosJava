package view;

public class Menu {
    public static void printPrompt() {
        System.out.println("Selecciona una opción: \n1.-Genera las tablas");
    }

    public static int selectMenu(int option) {
        int result;
        switch (option) {
        case 1:
            return 1;

        default:
            result = -1;
            break;
        }
        return result;
    }
}
