package service;

import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class EstatisticaBase {
    protected static List<Curso> cursos = new ArrayList<>();
    
    public static void setCursos(List<Curso> listaCursos) {
        cursos = listaCursos;
    }
    
    public static List<Curso> getCursos() {
        return cursos;
    }
    
    public abstract List<Double> calcular();
    
    protected List<Double> calcularPorcentagens(String[] categorias, IExtratorDados extrator) {
        Map<String, Integer> contagem = new HashMap<>();
        for (String categoria : categorias) {
            contagem.put(categoria, 0);
        }

        int total = 0;

        for (Curso curso : cursos) {
            for (Pessoa pessoa : curso.candidatos) {
                String valor = extrator.extrair(pessoa);
                if (contagem.containsKey(valor)) {
                    contagem.put(valor, contagem.get(valor) + 1);
                }
                total++;
            }
        }

        List<Double> resultados = new ArrayList<>();
        for (String categoria : categorias) {
            int quantidade = contagem.get(categoria);
            Double porcentagem = (quantidade * 100.0) / total;
            resultados.add(porcentagem);
        }

        return resultados;
    }
}