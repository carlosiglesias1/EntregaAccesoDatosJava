package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Alumno;
import model.Asignatura;

public class Menu {
    Scanner teclado;

    public Menu() {
        this.teclado = new Scanner(System.in);
    }

    public void goodBye() {
        System.out.println("Bye bye");
    }

    public int selectMenu() {
        System.out.println(
                "Selecciona una opción: \n1.-Genera las tablas\n2.-Operar sobre las tablas\n3.-Borrar las tablas\n0.-Cierra el programa");
        int option = Integer.parseInt(this.teclado.nextLine());
        switch (option) {
        case 1:
            System.out.println("Creando tablas...");
            return 1;
        case 2:
            System.out.println("Escoje la tabla: ");
            return 2;
        case 3:
            System.out.println("Borrando tablas...");
            return 3;
        case 0:
            return 0;
        default:
            return -1;

        }
    }

    public int selectTable(ResultSet rs) throws SQLException {
        int count = 1;
        while (rs.next()) {
            if (!rs.getString(1).equalsIgnoreCase("matricula") && !rs.getString(1).equalsIgnoreCase("viewAll")) {
                System.out.println("\t" + count + ".- " + rs.getString(1));
                count++;
            }
        }
        return Integer.parseInt(teclado.nextLine());
    }

    public int alumnoOPtions() {
        System.out.println(
                "\t1.-Crear un alumno\n\t2.-Editar un alumno\n\t3.-Matricular en una o varias asignaturas\n\t4.-Ver alumnos");
        return Integer.parseInt(this.teclado.nextLine());

    }

    public Alumno inputAlumnoFields() {
        Alumno alumnoNuevo = new Alumno();
        System.out.println("\nIntroduce el dni");
        alumnoNuevo.setDni(teclado.nextLine());
        System.out.println("\nintroduce el nombre");
        alumnoNuevo.setNombre(teclado.nextLine());
        System.out.println("\nIntroduce los apellidos");
        alumnoNuevo.setApellidos(teclado.nextLine());
        System.out.println("\nIntroduce la fecha de nacimiento (AAAA-mm-dd)");
        alumnoNuevo.setBirthDate(LocalDate.parse(teclado.nextLine()));
        return alumnoNuevo;
    }

    public int selectAlumno(List<Alumno> alumnos) {
        this.showAlumnos(alumnos);
        System.out.println("Introduce el ID del alumno");
        return Integer.parseInt(teclado.nextLine());
    }

    public void showAlumnos(List<Alumno> alumnos) {
        System.out.println(
                "ID\t|\tDNI\t|\tNombre\t|\tApellidos\t|\tFechaNac\n______________________________________________________________________");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.toString());
        }
        System.out.println();
    }

    public void showAsignaturasAlumno(Alumno alumno) {
        for (Asignatura asignatura : alumno.getAsignaturas()) {
            System.out.println(asignatura.toString());
        }
    }

    public int setNumMatriculas() {
        System.out.println("¿En cuántas asignaturas se va a matricular?");
        return Integer.parseInt(teclado.nextLine());
    }

    public int selectAsignatura(List<Asignatura> asignaturas) {
        this.showAsignaturas(asignaturas);
        System.out.println("Introduce una asignatura: ");
        return Integer.parseInt(teclado.nextLine());
    }

    public void showAsignaturas(List<Asignatura> asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura.toString());
        }
        System.out.println();
    }

    public int asignaturaOtions() {
        System.out.println("\t1.-Dar de alta una asignatura\n\t2.-Borrar asignatura");
        return Integer.parseInt(this.teclado.nextLine());
    }

    public Asignatura inputAsignatura() {
        System.out.println("Introduce un nombre");
        String nombre = teclado.nextLine();
        System.out.println("Escoje un curso: \n\t1.-ESO1\t4.-ESO4\n\t2.-ESO2\t5.-BAC1\n\t3.-ESO 3\t6.-BAC2");
        switch (Integer.parseInt(teclado.nextLine())) {
        case 1:
            return new Asignatura(-1, nombre, "ESO1");
        case 2:
            return new Asignatura(-1, nombre, "ESO2");
        case 3:
            return new Asignatura(-1, nombre, "ESO3");
        case 4:
            return new Asignatura(-1, nombre, "ESO4");
        case 5:
            return new Asignatura(-1, nombre, "BAC1");
        default:
            return new Asignatura(-1, nombre, "BAC2");
        }
    }
}