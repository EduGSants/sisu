package service;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class leitura {
    public static List<Curso> inserirPessoas(String caminhoArquivo) {
        List<Pessoa> pessoas = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(caminhoArquivo))) {
            // pula cabeçalho
            input.readLine();

            String line;
            while ((line = input.readLine()) != null) {
                String[] parts = line.split(",", 6);

                Curso curso = new Curso(parts[1], parts[2]);

                // Se o curso não existe ainda, adiciona
                if (!cursos.contains(curso)) {
                    cursos.add(curso);
                } else {
                    // Se já existe, pega o curso real da lista
                    curso = cursos.get(cursos.indexOf(curso));
                }

                Pessoa a = new Pessoa(
                    parts[0],
                    curso.getNomeCurso(),
                    curso.getCampus(),
                    Double.parseDouble(parts[4]),
                    parts[3],
                    Integer.parseInt(parts[5])
                );

                curso.candidatos.add(a);
                pessoas.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }
}
