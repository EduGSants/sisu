package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

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

        //Cálculo da Nota de Corte
    public static Map<String, Double> getNotasDeCortePorCota(Curso curso) {
        if (curso == null || curso.getCandidatos().isEmpty()) {
            return Collections.emptyMap();
        }

        return curso.getCandidatos().stream()
            .collect(Collectors.groupingBy(
                Pessoa::getTipoVaga,
                Collectors.minBy(Comparator.comparingDouble(Pessoa::getNota))
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().get().getNota()
            ));
    }

    public static double getMediaGeral() {
        List<Double> medias = medias();
        Double mediaGeral = 0.0;
        for(Double n : medias) {
            mediaGeral += n;
        }
        mediaGeral = mediaGeral / medias.size();
        return mediaGeral;
    }

    public static double getMediaPorCurso(Curso c) {
        int i = 0;
        for(Curso x : cursos) {
            if(x.equals(c)) {
                return medias().get(i);
            }
            i++;
        }
        return 0.0;
    }

    public static List<Double> getNotasOrdenadas(Curso curso) {
        List<Double> notas = new ArrayList<>();
        if (curso != null) {
            for (Pessoa p : curso.getCandidatos()) {
                notas.add(p.getNota());
            }
        }
        Collections.sort(notas);
        return notas;
    }

    public static List<Double> grau(List<Curso> cursos) {
        String[] categorias = { "BAC", "LIC" };
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);

        int totalPessoas = 0;
        for (Curso c : cursos) {
            for (int i = 0; i < c.getCandidatos().size(); i++) {
                String grauDoCurso = c.getGrau();
                if (contagem.containsKey(grauDoCurso)) {
                    contagem.put(grauDoCurso, contagem.get(grauDoCurso) + 1);
                }
                totalPessoas++;
            }
        }
        return calculoPerCurso(categorias, contagem, totalPessoas);
    }

    public static List<Double> getDistribuicaoVagas(Curso curso, String[] categorias) {
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);
        
        if (curso == null || curso.getCandidatos().isEmpty()) {
            return Collections.nCopies(categorias.length, 0.0);
        }
        
        int totalPessoasCurso = curso.getCandidatos().size();
        for (Pessoa p : curso.getCandidatos()) {
            String tipoVaga = p.getTipoVaga();
            if (contagem.containsKey(tipoVaga)) {
                contagem.put(tipoVaga, contagem.get(tipoVaga) + 1);
            }
        }
        return calculoPerCurso(categorias, contagem, totalPessoasCurso);
    }

    private static List<Double> calculoPerCurso(String[] categorias, Map<String, Integer> contagem, int total) {
        List<Double> resultados = new ArrayList<>();
        if (total == 0) {
            return Collections.nCopies(categorias.length, 0.0);
        }
        for (String cat : categorias) {
            resultados.add((double) contagem.get(cat) * 100.0 / total);
        }
        return resultados;
    }
}