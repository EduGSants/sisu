from service.estatistica_base import EstatisticaBase
from model.Pessoa import Pessoa
from typing import List

class EstatisticaDemandas(EstatisticaBase):
    CATEGORIAS = [
        "AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP",
        "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"
    ]

    def calcular(self) -> List[float]:
        return self.calcular_porcentagens(self.CATEGORIAS, Pessoa.get_tipo_vaga)