package service;

import model.*;
import java.util.*;

public class EstatisticaDelta extends EstatisticaBase {
    private static List<Double> variacoes = new ArrayList<>();
    
    @Override
    public List<Double> calcular() {
        variacoes.clear();
        for(Curso curso : cursos) {
            List<Double> notas = ordenarNotas(curso);
            variacoes.add(notas.get(notas.size() - 1) - notas.get(0));
        }
        return variacoes;
    }
    
    private List<Double> ordenarNotas(Curso curso) {
        List<Double> notas = new ArrayList<>();
        for(Pessoa candidato : curso.candidatos) {
            notas.add(candidato.getNota());
        }
        Collections.sort(notas);
        return notas;
    }
    
    public Double deltaEspecifico(Curso cursoEspecifico) {
        calcular();
        for(int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).equals(cursoEspecifico)) {
                return variacoes.get(i);
            }
        }
        return 0.0;
    }
}