package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Curso {
    private final String nomeCurso;
    private final String campus;
    private final String grau;
    private final String turno;
    private final List<Pessoa> candidatos;

    public Curso(String crs, String campus) {
        String[] resultado = definirCurso(crs);
        this.nomeCurso = resultado[0];
        this.grau = resultado[1];
        this.turno = resultado[2];
        this.campus = campus;
        this.candidatos = new ArrayList<>();
    }

    // -------------------------------------------------
    public void addCandidato(Pessoa p) {
        this.candidatos.add(p);
    }

    public List<Pessoa> getCandidatos() {
        return Collections.unmodifiableList(candidatos);
    }
    
    // -------------------------------------------------
    public String getNomeCurso() { return nomeCurso; }
    public String getCampus() { return campus; }
    public String getTurno() { return turno; }
    public String getGrau() { return grau; }

    public String[] definirCurso(String crs) {
        String[] resultado = new String[3];
        String[] partes = crs.split(" - ", 2);
        resultado[0] = partes[0].trim();

        if (partes.length > 1) {
            String resto = partes[1];
            String[] grauTurno = resto.split("\\(");
            resultado[1] = grauTurno[0].trim();
            if (grauTurno.length > 1) {
                resultado[2] = grauTurno[1].replace(")", "").trim();
            } else {
                resultado[2] = "";
            }
        } else {
            resultado[1] = "";
            resultado[2] = "";
        }
        return resultado;
    }

    // -------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(nomeCurso, curso.nomeCurso) &&
               Objects.equals(campus, curso.campus) &&
               Objects.equals(grau, curso.grau) &&
               Objects.equals(turno, curso.turno);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nomeCurso, campus, grau, turno);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s [%s]", 
            this.nomeCurso, 
            this.grau, 
            this.campus, 
            this.turno
        );
    }
}
