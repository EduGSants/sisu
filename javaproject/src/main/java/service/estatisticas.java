package service;

import model.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

public class estatisticas {
    static List<BigDecimal> variacao = new ArrayList<BigDecimal>();
    public static List<Curso> cursos = new ArrayList<Curso>();
    public static List<BigDecimal> medias() {
        // O último elemento do arraylist será a média geral!!!!
        List<Double> x = new ArrayList<Double>();
        for(Curso c : cursos) {
            Double nota = 0.0;
            for(Pessoa a : c.candidatos) {
                nota += a.getNota();
            }
            nota = nota / c.candidatos.size();
            x.add(nota);
        }
        // Após aqui fez a média de todas os cursos, a seguir é a média geral:
        Double mediaGeral = 0.0;
        for(Double n : x) {
            mediaGeral += n;
        }
        mediaGeral = mediaGeral / x.size();
        x.add(mediaGeral);
        List<BigDecimal> y = new ArrayList<BigDecimal>();
        for(Double n : x) {
            y.add((new BigDecimal(n).setScale(1, RoundingMode.HALF_EVEN)));
        }
        return y;
    }

    public static List<BigDecimal> turnos() {
        String[] turnos = { "MATUTINO", "VESPERTINO", "NOTURNO", "INTEGRAL" };

        // Permanece na ordem acima a lista

        Map<String, Integer> contagem = new HashMap<>();
        for (String t : turnos) {
            contagem.put(t, 0);
        }

        int totalCandidatos = 0;

        for (Curso c : cursos) {
            String turno = c.getTurno();
            int qtd = c.candidatos.size();
            if (contagem.containsKey(turno)) {
                contagem.put(turno, contagem.get(turno) + qtd);
            }
            totalCandidatos += qtd;
        }

        List<BigDecimal> x = new ArrayList<>();
        for (String t : turnos) {
            int qtd = contagem.get(t);
            BigDecimal perc = BigDecimal.valueOf((qtd * 100.0) / totalCandidatos)
                    .setScale(1, RoundingMode.HALF_EVEN);
            x.add(perc);
        }

        return x;
    }


    public static List<BigDecimal> demandas() {
        String[] categorias = {
            "AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP",
            "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"
        };

        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) {
            contagem.put(cat, 0);
        }

        int totalPessoas = 0;

        for (Curso c : cursos) {
            for (Pessoa p : c.candidatos) {
                String tipo = p.getTipoVaga();
                if (contagem.containsKey(tipo)) {
                    contagem.put(tipo, contagem.get(tipo) + 1);
                }
                totalPessoas++;
            }
        }

        List<BigDecimal> x = new ArrayList<>();
        for (String cat : categorias) {
            int qtd = contagem.get(cat);
            BigDecimal perc = BigDecimal.valueOf((qtd * 100.0) / totalPessoas).setScale(1, RoundingMode.HALF_EVEN);
            x.add(perc);
        }

        return x;
    }

    public static List<BigDecimal> campi() {
        String[] categorias = {
            "SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO"
            , "LARANJEIRAS"
        };

        Map<String, Integer> contagem = new HashMap<>();
        for (String cat : categorias) {
            contagem.put(cat, 0);
        }

        int totalPessoas = 0;

        for (Curso c : cursos) {
            for (Pessoa p : c.candidatos) {
                String tipo = p.getCampus();
                if (contagem.containsKey(tipo)) {
                    contagem.put(tipo, contagem.get(tipo) + 1);
                }
                totalPessoas++;
            }
        }

        List<BigDecimal> x = new ArrayList<>();
        for (String cat : categorias) {
            int qtd = contagem.get(cat);
            BigDecimal perc = BigDecimal.valueOf((qtd * 100.0) / totalPessoas).setScale(1, RoundingMode.HALF_EVEN);
            x.add(perc);
        }

        return x;
    }

    public static List<Double> mySort(Curso x) {
        List<Double> notas = new ArrayList<Double>();
        for(Pessoa p : x.candidatos) {
            notas.add(p.getNota());
        }
        Collections.sort(notas);
        return notas;
    }

    public static List<BigDecimal> delta() {
        for(Curso c : cursos) {
            List<Double> a = mySort(c);
            variacao.add(BigDecimal.valueOf(a.get(a.size() - 1) - a.get(0)).setScale(1, RoundingMode.HALF_EVEN));
        }
        return variacao;
    }

    public static BigDecimal deltaEspecifico(Curso c) {
        delta();
        int i = 0;
        for(Curso p : cursos) {
            if (p.equals(c)) {
                return variacao.get(i);
            }
            i++;
        }
        return new BigDecimal(0.0);
    }
}
