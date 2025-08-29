package service;

import model.*;
import java.util.List;

public class EstatisticaTurnos extends EstatisticaBase {
    private static final String[] TURNOS = { "MATUTINO", "VESPERTINO", "NOTURNO", "INTEGRAL" };

    @Override
    public List<Double> calcular() {
        return calcularPorcentagens(TURNOS, pessoa -> {
            for (Curso curso : cursos) {
                if (curso.candidatos.contains(pessoa)) {
                    return curso.getTurno();
                }
            }
            return "";
        });
    }
}