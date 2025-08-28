import service.*;
import model.*;

import java.util.List;
import java.math.BigDecimal;

public class App {
    public static void main (String[] args) {
        List<Curso> cursos = leitura.inserirPessoas("/workspaces/sisu/javaproject/src/main/resources/dados.csv");
        // Não vamos utilizar o que vier abaixo, são apenas para testes
        for(Curso s : cursos) {
            System.out.println(s.toString() + "," + s.getCampus());
        }
        List<BigDecimal> porcentagens = estatisticas.turnos(cursos);
        for(BigDecimal s : porcentagens) {
            System.out.println(s + "%");
        }
        porcentagens = estatisticas.medias(cursos);
        for(BigDecimal media : porcentagens) {
            System.out.println(media);
        }
        porcentagens = estatisticas.demandas(cursos);
        for(BigDecimal s : porcentagens) {
            System.out.println(s + "%");
        }
    }
}
