package model;

public class Pessoa {
    private final String nome;
    private final String curso;
    private final String campus;
    private final double nota;
    private final String tipoVaga;
    private final int posicao;

    public Pessoa(String nome, String curso, String campus, double nota, String tipoVaga, int posicao) {
        this.nome = nome;
        this.curso = curso;
        this.campus = campus;
        this.nota = nota;
        this.tipoVaga = tipoVaga;
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getCampus() {
        return campus;
    }

    public double getNota() {
        return nota;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public int getPosicao() {
        return posicao;
    }
}
