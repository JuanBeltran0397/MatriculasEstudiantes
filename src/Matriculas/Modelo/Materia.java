package Matriculas.Modelo;

public class Materia {
    protected String idMateria;
    protected String nombreMateria;
    protected int creditos;

    public Materia(String idMateria, String nombreMateria, int creditos) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.creditos = creditos;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }
    public int getCreditos() {
        return creditos;
    }
    @Override
    public String toString() {
        return "Materia{" +
                "idMateria='" + idMateria + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}
