package Matriculas.Modelo;

public class Estudiante extends Personas{
    private String codigoEstudiante;
    private int semestre;
    private String carrera;
    private String estado; // activo, inactivo

    public Estudiante(String documento, String nombre, String apellido, String correo, String codigoEstudiante, int semestre, String carrera, String estado) {
        super(documento, nombre, apellido, correo);
        this.codigoEstudiante = codigoEstudiante;
        this.semestre = semestre;
        this.carrera = carrera;
        this.estado = estado;
    }
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }
    public int getSemestre() {
        return semestre;
    }
    @Override
    public String toString() {
        return "Estudiante{" +
                "codigoEstudiante='" + codigoEstudiante + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", semestre=" + semestre +
                ", carrera='" + carrera + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
