package model;

import java.time.LocalDate;
import java.util.List;

public class Alumno {
    private int idAlumno;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate birthDate;
    private List<Asignatura> asignaturas;

    public Alumno(int idAlumno, String dni, String nombre, String apellidos, LocalDate nacimiento,
            List<Asignatura> asignaturas) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.birthDate = nacimiento;
        this.asignaturas = asignaturas;
    }

    public Alumno() {
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
