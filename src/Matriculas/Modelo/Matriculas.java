package Matriculas.Modelo;
import java.util.ArrayList;
import java.util.List;

public class Matriculas {
    private Estudiante estudiante;
    private List<Materia> materias= new ArrayList<>();

    public Matriculas(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public int calcularTotalCreditos() {// calcula el total de creditos usando streams
        return materias.stream() //convierte la lista en un stream
                .mapToInt(Materia::getCreditos)// convierte cada materia en sus creditos
                .sum();// suma todos los creditos
    }
    public void mostrarMaterias(){
        materias.forEach(m-> System.out.println("- "+m));// imprime cada materia usanto expresion lambda
    }
}
