from typing import List
from python.Pessoa import Pessoa
from python.Curso import Curso

class Leitura:
    @staticmethod
    def addvariaveis(caminho: str) -> List[Curso]:
        pessoas: List[Pessoa] = []
        cursos: List[Curso] = []

        try:
            with open(caminho, "r", encoding="utf-8") as input_file:
                next(input_file) # pular o cabeçalho

                for line in input_file:
                    parts = line.strip().split(",",5)
                    curso = Curso(parts[1],parts[2])

                    if curso not in cursos:
                        cursos.append(curso)
                    else:
                        curso = cursos[cursos.index(curso)]
                    a = Pessoa(
                        parts[0],
                        curso.getNomeCurso(),
                        curso.getCampus(),  
                        parts[3],
                        float(parts[4]),
                        int(parts[5])
                    )

                    curso.candidatos.append(a)
                    pessoas.append(a)
        except Exception as e:
            print("Erro, culpa de Migas", e)
        return cursos
