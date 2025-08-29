package service;

import model.*;
import java.util.List;
import java.util.ArrayList;

public class EstatisticaMedias extends EstatisticaBase {
    @Override
    public List<Double> calcular() {
        List<Double> medias = new ArrayList<>();
        Double mediaGeral = 0.0;
        
        for(Curso curso : cursos) {
            Double notaTotal = 0.0;
            for(Pessoa candidato : curso.getCandidatos()) {
                notaTotal += candidato.getNota();
            }
            Double mediaCurso = notaTotal / curso.getCandidatos().size();
            medias.add(mediaCurso);
            mediaGeral += mediaCurso;
        }
        
        mediaGeral = mediaGeral / cursos.size();
        medias.add(mediaGeral);
        
        return medias;
    }
}