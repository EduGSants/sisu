package service;

import model.*;
import java.util.List;

public class EstatisticaDemandas extends EstatisticaBase {
    private static final String[] CATEGORIAS = {
        "AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP",
        "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"
    };

    @Override
    public List<Double> calcular() {
        return calcularPorcentagens(CATEGORIAS, Pessoa::getTipoVaga);
    }
}