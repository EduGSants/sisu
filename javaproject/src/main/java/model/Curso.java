package model;

import java.util.ArrayList;
import java.util.Objects;

public class Curso {
    private final String nomeCurso;
    private final String campus;
    public ArrayList<Pessoa> candidatos;

    public Curso(String nomeCurso, String campus) {
        this.nomeCurso = nomeCurso;
        this.campus = campus;
        this.candidatos = new ArrayList<Pessoa>();
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public String getCampus() {
        return campus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(nomeCurso, curso.nomeCurso) &&
               Objects.equals(campus, curso.campus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCurso, campus);
    }
}
