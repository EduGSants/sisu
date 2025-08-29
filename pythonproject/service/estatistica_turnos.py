from service.estatistica_base import EstatisticaBase
from typing import List

class EstatisticaTurnos(EstatisticaBase):
    TURNOS = ["MATUTINO", "VESPERTINO", "NOTURNO", "INTEGRAL"]

    def calcular(self) -> List[float]:
        return self.calcular_porcentagens(self.TURNOS, self.extrair_turno)
    
    def extrair_turno(self, pessoa):
        for curso in self.cursos:
            if pessoa in curso.get_candidatos():
                return curso.get_turno()
        return ""