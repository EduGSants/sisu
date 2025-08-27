import service.*;
import model.*;
import java.util.List;

public class App {
    public static void main (String[] args) {
        List<Curso> cursos = leitura.inserirPessoas("/workspaces/sisu/javaproject/src/main/resources/dados.csv");
        for(Curso s : cursos) {
            System.out.println("Curso: " + s.toString() + ", Campus: " + s.getCampus());   // Não vamos utilizar isso, é somente para teste
        }
    }
}
