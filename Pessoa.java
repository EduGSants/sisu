public class Pessoa {
    private final String nome;
    private final String curso;
    private final String campus;
    private final double nota;
    private final String tipoVaga;

    public Pessoa(String nome, String curso, String campus, double nota, String tipoVaga) {
        this.nome = nome;
        this.curso = curso;
        this.campus = campus;
        this.nota = nota;
        this.tipoVaga = tipoVaga;
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
}
