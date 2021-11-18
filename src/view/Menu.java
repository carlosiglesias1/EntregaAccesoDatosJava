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
            System.out.println("\t" + count + ".- " + rs.getString(1));
            count++;
        }
        return Integer.parseInt(teclado.nextLine());
    }

    public int alumnoOPtions() {
        System.out.println(
                "\t1.-Crear un alumno\n\t2.-Editar un alumno\n\t3.-Matricular en una asignatura\n\t4.-Ver alumnos");
        return Integer.parseInt(this.teclado.nextLine());

    }

    public Alumno inputAlumnoFields() {
        Alumno alumnoNuevo = new Alumno();
        alumnoNuevo.setDni(teclado.nextLine());
        alumnoNuevo.setNombre(teclado.nextLine());
        alumnoNuevo.setApellidos(teclado.nextLine());
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
}