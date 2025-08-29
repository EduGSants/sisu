package service;

import model.*;
import java.util.List;

public class EstatisticaCampi extends EstatisticaBase {
    private static final String[] CAMPUS = {
        "SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO", "LARANJEIRAS"
    };

    @Override
    public List<Double> calcular() {
        return calcularPorcentagens(CAMPUS, Pessoa::getCampus);
    }
}