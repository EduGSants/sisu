from service.estatistica_base import EstatisticaBase
from model.Curso import Curso
from typing import List

class EstatisticaDelta(EstatisticaBase):
    def __init__(self):
        self.variacoes: List[float] = []
    
    def calcular(self) -> List[float]:
        self.variacoes.clear()
        for curso in self.cursos:
            notas = self.ordenar_notas(curso)
            if notas:
                self.variacoes.append(notas[-1] - notas[0])
            else:
                self.variacoes.append(0.0)
        return self.variacoes
    
    def ordenar_notas(self, curso: Curso) -> List[float]:
        notas = [candidato.get_nota() for candidato in curso.get_candidatos()]
        notas.sort()
        return notas
    
    def delta_especifico(self, curso_especifico: Curso) -> float:
        self.calcular()
        for i, curso in enumerate(self.cursos):
            if curso == curso_especifico:
                return self.variacoes[i] if i < len(self.variacoes) else 0.0
        return 0.0