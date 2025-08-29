from decimal import Decimal, ROUND_HALF_EVEN
from typing import List
from python.Curso import Curso

class estatisticas:

    @staticmethod
    def medias(cursos: List[Curso]) -> List[Decimal]:
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

        # Converter para Decimal com 1 casa decimal
        return [Decimal(n).quantize(Decimal("0.0"), rounding=ROUND_HALF_EVEN) for n in medias_cursos]

    @staticmethod
    def turnos(cursos: List[Curso]) -> List[Decimal]:
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
            Decimal((contagem[t] * 100) / total_candidatos).quantize(Decimal("0.0"), rounding=ROUND_HALF_EVEN)
            if total_candidatos > 0 else Decimal("0.0")
            for t in turnos
        ]

    @staticmethod
    def demandas(cursos: List[Curso]) -> List[Decimal]:
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
            Decimal((contagem[cat] * 100) / total_pessoas).quantize(Decimal("0.0"), rounding=ROUND_HALF_EVEN)
            if total_pessoas > 0 else Decimal("0.0")
            for cat in categorias
        ]

    @staticmethod
    def campi(cursos: List[Curso]) -> List[Decimal]:
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
            Decimal((contagem[cat] * 100) / total_pessoas).quantize(Decimal("0.0"), rounding=ROUND_HALF_EVEN)
            if total_pessoas > 0 else Decimal("0.0")
            for cat in categorias
        ]
    
    @staticmethod
    def delta(cursos: List[Curso]) -> List[Decimal]:
        variacoes = []
        for c in cursos:
            if c.candidatos:
                maior = max(Decimal(a.getNota()) for a in c.candidatos)
                menor = min(Decimal(a.getNota()) for a in c.candidatos)
                diferenca = maior - menor
                arredondado = diferenca.quantize(Decimal("0.0"), rounding=ROUND_HALF_EVEN)
                variacoes.append(arredondado)

        return variacoes

    @staticmethod
    def deltaEsp(cursos: List[Curso], c: Curso) -> Decimal:
        variacoes = estatisticas.delta(cursos)
        i = 0
        for m in cursos:
            if (m.__eq__(c)):
                return variacoes[i]
            else:
                i += 1
        
        
