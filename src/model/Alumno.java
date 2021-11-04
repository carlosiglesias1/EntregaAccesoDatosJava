package model;

import java.time.LocalDate;

public class Alumno {
    private int idAlumno;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate birthDate;

    public Alumno(int idAlumno, String dni, String nombre, String apellido1, String apellido2, LocalDate nacimiento) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.birthDate = nacimiento;
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

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public LocalDate getBirthDate() {
        return birthDate;
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

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
