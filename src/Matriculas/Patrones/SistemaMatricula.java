package Matriculas.Patrones;
import Matriculas.Modelo.Materia;
import Matriculas.Modelo.Estudiante;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//se aplica el patron singleton para manejar la matricula de estudiantes
public class SistemaMatricula {
    private static SistemaMatricula instancia;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Materia> materias = new ArrayList<>();

    private SistemaMatricula() {// Constructor privado evita que se creen instancias fuera de la clase
    }

    public List<Estudiante> getEstudiantes() {// Metodo para obtener la lista de estudiantes
        return estudiantes;
    }
    public List<Materia> getMaterias() {// Metodo para obtener la lista de materias
        return materias;
    }

    public static SistemaMatricula getInstancia() {
        if (instancia == null) {// Si no existe una instancia, se crea una nueva
            instancia = new SistemaMatricula();
        }
        return instancia;// Se retorna la instancia unica
    }
    public void agregarEstudiante(Estudiante estudiante) {// Manejo de excepciones al agregar estudiante
        try{
            if (estudiante.getNombreCompleto().isEmpty()){// Validacion simple
                throw new IllegalArgumentException("El nombre del estudiante no puede estar vacio");}
            estudiantes.add(estudiante);// Agrega el estudiante a la lista
            System.out.println("Estudiante "+estudiante.getNombreCompleto()+" agregado exitosamente.");// Mensaje de exito
        }catch (Exception e){// Captura cualquier excepcion y muestra un mensaje de error
            System.out.println("Error al agregar estudiante: "+e.getMessage());
        }
    }
    public void agregarMateria(Materia materia) {// Agrega una materia a la lista
        materias.add(materia);
    }

    public void listarMateriasPorCreditos(int minCreditos){// Filtra y lista materias con al menos minCreditos
        List<Materia>filtradas = materias.stream()// Uso de streams para filtrar
            .filter(m -> m.getCreditos() >= minCreditos)// Filtra por creditos
            .collect(Collectors.toList());// Colecciona el resultado en una lista
        System.out.println("Materias con al menos "+minCreditos+" creditos:"+ filtradas);// Muestra las materias filtradas
    }
}
