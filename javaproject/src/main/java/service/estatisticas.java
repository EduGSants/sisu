package service;

// -------------------------------------------------
import model.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
// -------------------------------------------------

public class estatisticas {

    // Métodos de Cálculo Geral

    public static List<Double> campi(List<Curso> cursos) {
        String[] categorias = {"SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO", "LARANJEIRAS"};
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);
        
        int totalPessoas = 0;
        for (Curso c : cursos) {
            for (Pessoa p : c.getCandidatos()) {
                String campusDoCandidato = p.getCampus();
                if (contagem.containsKey(campusDoCandidato)) {
                    contagem.put(campusDoCandidato, contagem.get(campusDoCandidato) + 1);
                }
                totalPessoas++;
            }
        }
        return calcularPorcentagens(categorias, contagem, totalPessoas);
    }
    
    public static List<Double> turnos(List<Curso> cursos) {
        String[] categorias = { "MATUTINO", "NOTURNO", "VESPERTINO", "INTEGRAL" };
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);

        int totalPessoas = 0;
        for (Curso c : cursos) {
            for (Pessoa p : c.getCandidatos()) {
                String turnoDoCurso = c.getTurno().toUpperCase();
                 if (contagem.containsKey(turnoDoCurso)) {
                    contagem.put(turnoDoCurso, contagem.get(turnoDoCurso) + 1);
                }
                totalPessoas++;
            }
        }
        return calcularPorcentagens(categorias, contagem, totalPessoas);
    }
    
    public static List<Double> grau(List<Curso> cursos) {
        String[] categorias = { "BAC", "LIC" };
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);

        int totalPessoas = 0;
        for (Curso c : cursos) {
            for (Pessoa p : c.getCandidatos()) {
                String grauDoCurso = c.getGrau();
                if (contagem.containsKey(grauDoCurso)) {
                    contagem.put(grauDoCurso, contagem.get(grauDoCurso) + 1);
                }
                totalPessoas++;
            }
        }
        return calcularPorcentagens(categorias, contagem, totalPessoas);
    }
    
    //Cálculo por Tipo de Vaga
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
        return calcularPorcentagens(categorias, contagem, totalPessoasCurso);
    }
    
    // Método de Cálculo Específico
    public static double getMediaGeral(List<Curso> cursos) {
        double somaTotal = 0;
        int totalPessoas = 0;
        for (Curso c : cursos) {
            for (Pessoa p : c.getCandidatos()) {
                somaTotal += p.getNota();
                totalPessoas++;
            }
        }
        if (totalPessoas == 0) return 0.0;
        return somaTotal / totalPessoas;
    }

    public static double getMediaPorCurso(Curso curso) {
        if (curso == null || curso.getCandidatos().isEmpty()) {
            return 0.0;
        }
        double somaCurso = 0;
        for (Pessoa p : curso.getCandidatos()) {
            somaCurso += p.getNota();
        }
        return somaCurso / curso.getCandidatos().size();
    }
    
    public static List<Double> demandas(Curso curso) {
        String[] categorias = {"AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP", "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"};
        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) contagem.put(cat, 0);
        
        int totalPessoasCurso = curso.getCandidatos().size();
        for (Pessoa p : curso.getCandidatos()) {
            String tipoVaga = p.getTipoVaga();
            if (contagem.containsKey(tipoVaga)) {
                contagem.put(tipoVaga, contagem.get(tipoVaga) + 1);
            }
        }
        return calcularPorcentagens(categorias, contagem, totalPessoasCurso);
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

    private static List<Double> calcularPorcentagens(String[] categorias, Map<String, Integer> contagem, int total) {
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
