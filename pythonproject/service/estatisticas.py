from typing import List, Dict
from model.Curso import Curso
from service.estatistica_base import EstatisticaBase, IExtratorDados
from service.estatistica_campi import EstatisticaCampi
from service.estatistica_delta import EstatisticaDelta
from service.estatistica_demandas import EstatisticaDemandas
from service.estatistica_medias import EstatisticaMedias
from service.estatistica_turnos import EstatisticaTurnos

class Estatisticas:
    cursos: List[Curso] = []
    
    estatisticas: Dict[str, EstatisticaBase] = {
        "medias": EstatisticaMedias(),
        "turnos": EstatisticaTurnos(),
        "demandas": EstatisticaDemandas(),
        "campi": EstatisticaCampi(),
        "delta": EstatisticaDelta()
    }
    
    @classmethod
    def set_cursos(cls, lista_cursos: List[Curso]):
        cls.cursos = lista_cursos
        for estatistica in cls.estatisticas.values():
            estatistica.set_cursos(lista_cursos)
    
    @classmethod
    def get_cursos(cls) -> List[Curso]:
        return cls.cursos
    
    @classmethod
    def medias(cls) -> List[float]:
        return cls.estatisticas["medias"].calcular()
    
    @classmethod
    def turnos(cls) -> List[float]:
        return cls.estatisticas["turnos"].calcular()
    
    @classmethod
    def demandas(cls) -> List[float]:
        return cls.estatisticas["demandas"].calcular()
    
    @classmethod
    def campi(cls) -> List[float]:
        return cls.estatisticas["campi"].calcular()
    
    @classmethod
    def delta(cls) -> List[float]:
        return cls.estatisticas["delta"].calcular()
    
    @classmethod
    def delta_especifico(cls, curso: Curso) -> float:
        return cls.estatisticas["delta"].delta_especifico(curso)

    @classmethod
    def calcular_estatistica(cls, nome_estatistica: str) -> List[float]:
        estatistica = cls.estatisticas.get(nome_estatistica.lower())
        if estatistica is not None:
            return estatistica.calcular()
        raise ValueError(f"Estatística não encontrada: {nome_estatistica}")

    @classmethod
    def get_notas_de_corte_por_cota(cls, curso: Curso) -> Dict[str, float]:
        if curso is None or not curso.get_candidatos():
            return {}
            
        notas_corte = {}
        for pessoa in curso.get_candidatos():
            tipo_vaga = pessoa.get_tipo_vaga()
            nota = pessoa.get_nota()
            
            if tipo_vaga not in notas_corte or nota < notas_corte[tipo_vaga]:
                notas_corte[tipo_vaga] = nota
                
        return notas_corte

    @classmethod
    def get_media_geral(cls) -> float:
        medias = cls.medias()
        if not medias:
            return 0.0
        return sum(medias) / len(medias)

    @classmethod
    def get_media_por_curso(cls, curso: Curso) -> float:
        medias = cls.medias()
        for i, c in enumerate(cls.cursos):
            if c == curso:
                return medias[i] if i < len(medias) else 0.0
        return 0.0

    @classmethod
    def get_notas_ordenadas(cls, curso: Curso) -> List[float]:
        if curso is None:
            return []
            
        notas = [p.get_nota() for p in curso.get_candidatos()]
        notas.sort()
        return notas

    @classmethod
    def grau(cls) -> List[float]:
        categorias = ["BAC", "LIC"]
        from collections import defaultdict
        contagem = defaultdict(int)
        
        for cat in categorias:
            contagem[cat] = 0

        total_pessoas = 0
        for c in cls.cursos:
            for pessoa in c.get_candidatos():
                grau_do_curso = c.get_grau()
                if grau_do_curso in contagem:
                    contagem[grau_do_curso] += 1
                total_pessoas += 1
                
        return cls._calculo_por_curso(categorias, contagem, total_pessoas)

    @classmethod
    def get_distribuicao_vagas(cls, curso: Curso, categorias: List[str]) -> List[float]:
        from collections import defaultdict
        contagem = defaultdict(int)
        
        for cat in categorias:
            contagem[cat] = 0
            
        if curso is None or not curso.get_candidatos():
            return [0.0] * len(categorias)
            
        total_pessoas_curso = len(curso.get_candidatos())
        for p in curso.get_candidatos():
            tipo_vaga = p.get_tipo_vaga()
            if tipo_vaga in contagem:
                contagem[tipo_vaga] += 1
                
        return cls._calculo_por_curso(categorias, contagem, total_pessoas_curso)

    @classmethod
    def _calculo_por_curso(cls, categorias: List[str], contagem: Dict[str, int], total: int) -> List[float]:
        if total == 0:
            return [0.0] * len(categorias)
            
        resultados = []
        for cat in categorias:
            resultados.append((contagem.get(cat, 0) * 100.0) / total)
            
        return resultados