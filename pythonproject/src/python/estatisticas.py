from typing import List
from python.Curso import Curso

class estatisticas:

    @staticmethod
    def medias(cursos: List[Curso]) -> List[float]:
        medias_cursos = []

        for c in cursos:
            if c.candidatos:
                soma = sum(a.getNota() for a in c.candidatos)
                media = soma / len(c.candidatos)
            else:
                media = 0.0
            medias_cursos.append(media)

        # Média geral
        media_geral = sum(medias_cursos) / len(medias_cursos) if medias_cursos else 0.0
        medias_cursos.append(media_geral)

        return medias_cursos

    @staticmethod
    def turnos(cursos: List[Curso]) -> List[float]:
        turnos = ["MATUTINO", "VESPERTINO", "NOTURNO", "INTEGRAL"]
        contagem = {t: 0 for t in turnos}
        total_candidatos = 0

        for c in cursos:
            qtd = len(c.candidatos)
            turno = c.getTurno()
            if turno in contagem:
                contagem[turno] += qtd
            total_candidatos += qtd

        return [
            float((contagem[t] * 100) / total_candidatos)
            if total_candidatos > 0 else float("0.0")
            for t in turnos
        ]

    @staticmethod
    def demandas(cursos: List[Curso]) -> List[float]:
        categorias = [
            "AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP",
            "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"
        ]
        contagem = {cat: 0 for cat in categorias}
        total_pessoas = 0

        for c in cursos:
            for p in c.candidatos:
                tipo = p.getTipoVaga()
                if tipo in contagem:
                    contagem[tipo] += 1
                total_pessoas += 1

        return [
            float((contagem[cat] * 100) / total_pessoas)
            if total_pessoas > 0 else float("0.0")
            for cat in categorias
        ]

    @staticmethod
    def campi(cursos: List[Curso]) -> List[float]:
        categorias = ["SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO", "LARANJEIRAS"]
        contagem = {cat: 0 for cat in categorias}
        total_pessoas = 0

        for c in cursos:
            for p in c.candidatos:
                campus = p.getCampus()
                if campus in contagem:
                    contagem[campus] += 1
                total_pessoas += 1

        return [
            float((contagem[cat] * 100) / total_pessoas)
            if total_pessoas > 0 else float("0.0")
            for cat in categorias
        ]
    
    @staticmethod
    def delta(cursos: List[Curso]) -> List[float]:
        variacoes = []
        for c in cursos:
            if c.candidatos:
                maior = max(float(a.getNota()) for a in c.candidatos)
                menor = min(float(a.getNota()) for a in c.candidatos)
                variacoes.append(maior - menor)

        return variacoes

    @staticmethod
    def deltaEsp(cursos: List[Curso], c: Curso) -> float:
        variacoes = estatisticas.delta(cursos)
        i = 0
        for m in cursos:
            if (m.__eq__(c)):
                return variacoes[i]
            else:
                i += 1
        
        
