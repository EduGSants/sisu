from abc import ABC, abstractmethod
from typing import List, Callable
from model.Curso import Curso
from model.Pessoa import Pessoa

class IExtratorDados(ABC):
    @abstractmethod
    def extrair(self, pessoa: Pessoa) -> str:
        pass

class EstatisticaBase(ABC):
    cursos: List[Curso] = []
    
    @classmethod
    def set_cursos(cls, lista_cursos: List[Curso]):
        cls.cursos = lista_cursos
    
    @classmethod
    def get_cursos(cls) -> List[Curso]:
        return cls.cursos
    
    @abstractmethod
    def calcular(self) -> List[float]:
        pass
    
    def calcular_porcentagens(self, categorias: List[str], extrator: Callable[[Pessoa], str]) -> List[float]:
        from collections import defaultdict
        contagem = defaultdict(int)
        
        for categoria in categorias:
            contagem[categoria] = 0

        total = 0

        for curso in self.cursos:
            for pessoa in curso.get_candidatos():
                valor = extrator(pessoa)
                if valor in contagem:
                    contagem[valor] += 1
                total += 1

        resultados = []
        for categoria in categorias:
            quantidade = contagem[categoria]
            porcentagem = (quantidade * 100.0) / total if total > 0 else 0.0
            resultados.append(porcentagem)

        return resultados