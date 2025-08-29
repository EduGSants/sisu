package service;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
// -------------------------------------------------

public class Leitura {
    public static List<Curso> inserirPessoas(String caminhoArquivo) {
        List<Pessoa> pessoas = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(caminhoArquivo))) {
            input.readLine();
            String line;
            while ((line = input.readLine()) != null) {
                String[] parts = line.split(",", 6);
                Curso curso = new Curso(parts[1], parts[2]);
                if (!cursos.contains(curso)) {
                    cursos.add(curso);
                } else {
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

                curso.addCandidato(a);
                pessoas.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return cursos;
    }
}
