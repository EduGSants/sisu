from service.estatistica_base import EstatisticaBase
from model.Pessoa import Pessoa
from typing import List

class EstatisticaCampi(EstatisticaBase):
    CAMPUS = [
        "SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO", "LARANJEIRAS"
    ]

    def calcular(self) -> List[float]:
        return self.calcular_porcentagens(self.CAMPUS, Pessoa.get_campus)