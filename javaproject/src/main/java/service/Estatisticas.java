package service;

import model.*;
import java.util.*;

public class Estatisticas {
    private static List<Curso> cursos = new ArrayList<>();
    
    private static final Map<String, EstatisticaBase> estatisticas = new HashMap<>();
    
    static {
        estatisticas.put("medias", new EstatisticaMedias());
        estatisticas.put("turnos", new EstatisticaTurnos());
        estatisticas.put("demandas", new EstatisticaDemandas());
        estatisticas.put("campi", new EstatisticaCampi());
        estatisticas.put("delta", new EstatisticaDelta());

        EstatisticaBase.setCursos(cursos);
    }
    
    public static void setCursos(List<Curso> listaCursos) {
        cursos = listaCursos;
        EstatisticaBase.setCursos(cursos);
    }
    
    public static List<Curso> getCursos() {
        return cursos;
    }
    
    public static List<Double> medias() {
        return estatisticas.get("medias").calcular();
    }
    
    public static List<Double> turnos() {
        return estatisticas.get("turnos").calcular();
    }
    
    public static List<Double> demandas() {
        return estatisticas.get("demandas").calcular();
    }
    
    public static List<Double> campi() {
        return estatisticas.get("campi").calcular();
    }
    
    public static List<Double> delta() {
        return estatisticas.get("delta").calcular();
    }
    
    public static Double deltaEspecifico(Curso curso) {
        EstatisticaDelta estatisticaDelta = (EstatisticaDelta) estatisticas.get("delta");
        return estatisticaDelta.deltaEspecifico(curso);
    }

    public static List<Double> calcularEstatistica(String nomeEstatistica) {
        EstatisticaBase estatistica = estatisticas.get(nomeEstatistica.toLowerCase());
        if (estatistica != null) {
            return estatistica.calcular();
        }
        throw new IllegalArgumentException("Estatística não encontrada: " + nomeEstatistica);
    }
}