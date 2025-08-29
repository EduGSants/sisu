import service.*;
import model.*;
import java.util.List;

public class App {
    public static void main (String[] args) {
        List<Curso> cursos = leitura.inserirPessoas("/workspaces/sisu/javaproject/src/main/resources/dados.csv");
        estatisticas.cursos = cursos;
        Double varia = estatisticas.deltaEspecifico(cursos.get(4));
        // Não vamos utilizar o que vier abaixo, são apenas para testes
        /*
        for(Curso s : cursos) {
            System.out.println(s.toString() + "," + s.getCampus());
        }
        for(BigDecimal s : porcentagens) {
            System.out.println(s + "%");
        }
        porcentagens = estatisticas.medias();
        for(BigDecimal media : porcentagens) {
            System.out.println(media);
        }
        porcentagens = estatisticas.demandas();
        for(BigDecimal s : porcentagens) {
            System.out.println(s + "%");
        }
            */
        System.out.println(varia);
    }
}
