from service.estatistica_base import EstatisticaBase
from typing import List

class EstatisticaMedias(EstatisticaBase):
    def calcular(self) -> List[float]:
        medias = []
        media_geral = 0.0
        
        for curso in self.cursos:
            if curso.get_candidatos():
                nota_total = sum(candidato.get_nota() for candidato in curso.get_candidatos())
                media_curso = nota_total / len(curso.get_candidatos())
            else:
                media_curso = 0.0
            medias.append(media_curso)
            media_geral += media_curso
        
        if self.cursos:
            media_geral = media_geral / len(self.cursos)
        else:
            media_geral = 0.0
            
        medias.append(media_geral)
        
        return medias