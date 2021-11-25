package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Alumno;
import model.Asignatura;
import model.Departamento;
import model.Profesor;

public class Menu {
    Scanner teclado;

    public Menu() {
        this.teclado = new Scanner(System.in);
    }

    public void insertSuccess() {
        System.out.println("Insertion succed");
    }

    public void goodBye() {
        System.out.println("Bye bye");
    }

    /**
     * @return int
     */
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

    /**
     * @param rs
     * @return int
     * @throws SQLException
     */
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

    /**
     * @return int
     */
    public int alumnoOPtions() {
        System.out.println(
                "\t1.-Crear un alumno\n\t2.-Borrar alumno\n\t3.-Editar un alumno\n\t4.-Matricular en una o varias asignaturas\n\t5.-Ver asignaturas de un alumno\n\t6.-Ver alumnos");
        return Integer.parseInt(this.teclado.nextLine());

    }

    /**
     * @return Alumno
     */
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

    /**
     * @param alumnos
     * @return int
     */
    public int selectAlumno(List<Alumno> alumnos) {
        this.showAlumnos(alumnos);
        System.out.println("Introduce el ID del alumno");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @param alumnos
     */
    public void showAlumnos(List<Alumno> alumnos) {
        System.out.println(
                "ID\t|\tDNI\t|\tNombre\t|\tApellidos\t|\tFechaNac\n______________________________________________________________________");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.toString());
        }
        System.out.println();
    }

    /**
     * @param alumno
     */
    public void showAsignaturasAlumno(Alumno alumno) {
        for (Asignatura asignatura : alumno.getAsignaturas()) {
            System.out.println(asignatura.toString());
        }
    }

    /**
     * @return int
     */
    public int setNumMatriculas() {
        System.out.println("¿En cuántas asignaturas se va a matricular?");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @param asignaturas
     */
    public void showAsignaturas(List<Asignatura> asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura.toString());
        }
        System.out.println();
    }

    /**
     * @return int
     */
    public int asignaturaOtions() {
        System.out.println("\t1.-Dar de alta una asignatura\n\t2.-Borrar asignatura");
        return Integer.parseInt(this.teclado.nextLine());
    }

    /**
     * @return Asignatura
     */
    public Asignatura inputAsignatura() {
        System.out.println("Introduce un nombre");
        String nombre = teclado.nextLine();
        System.out.println("Escoje un curso: \n\t1.-ESO1\t4.-ESO4\n\t2.-ESO2\t5.-BAC1\n\t3.-ESO3\t6.-BAC2");
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

    /**
     * @param asignaturas
     * @return int
     */
    public int selectAsignatura(List<Asignatura> asignaturas) {
        this.showAsignaturas(asignaturas);
        System.out.println("Introduce una asignatura: ");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @return int
     */
    public int departamentoOptions() {
        System.out.println(
                "1.-Crear Departamento\n2.-Borrar Departamento\n3.-Ver Profesores de un departamento\n4.-Ver Departamentos");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @return Departamento
     */
    public Departamento inputDepartamento() {
        System.out.println("Introduce el nombre");
        return new Departamento(-1, teclado.nextLine(), new ArrayList<>());
    }

    /**
     * @param depts
     */
    public void showDepts(List<Departamento> depts) {
        for (Departamento departamento : depts) {
            System.out.println(departamento.toString());
        }
    }

    /**
     * @param depts
     * @return int
     */
    public int selectDept(List<Departamento> depts) {
        this.showDepts(depts);
        System.out.println("Selecciona un departamento");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @param profesors
     */
    public void showProfes(List<Profesor> profesors) {
        for (Profesor profesor : profesors) {
            System.out.println(profesor.toString());
        }
    }

    /**
     * @return int
     */
    public int profesorOptions() {
        System.out.println("1.-Crear profesor\n2.-Eliminar profesor\n3.-Actualizar profesor");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * Instancia un nuevo profesor para poderlo insertar en la BBDD
     * 
     * @return Profesor
     */
    public Profesor profesorFields() {
        Profesor profesor = new Profesor();
        System.out.println("Introduce el dni");
        profesor.setDni(teclado.nextLine());
        System.out.println("Introduce el nombre");
        profesor.setNombre(teclado.nextLine());
        System.out.println("Introduce los apellidos");
        profesor.setApellidos(teclado.nextLine());
        return profesor;
    }

    public int selectProfesor(List<Profesor> profesors) {
        showProfes(profesors);
        System.out.println("Selecciona un profesor");
        return Integer.parseInt(teclado.nextLine());
    }
}