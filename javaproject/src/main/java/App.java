import service.*;
import model.*;

public class App {
    public static void main (String[] args) {
        Estatisticas.setCursos(Leitura.inserirPessoas("/workspaces/sisu/javaproject/src/main/resources/dados.csv"));
        Double varia = Estatisticas.deltaEspecifico(Estatisticas.getCursos().get(4));
        System.out.println(varia);
        for(Pessoa p : Estatisticas.getCursos().get(4).getCandidatos()) {
            System.out.println(p.getNome() + " " + p.getPosicao());
        }
    }
}
