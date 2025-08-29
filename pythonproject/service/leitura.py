from typing import List
from model.Curso import Curso
from model.Pessoa import Pessoa

class Leitura:
    @staticmethod
    def inserir_pessoas(caminho_arquivo: str) -> List[Curso]:
        pessoas = []
        cursos = []

        try:
            with open(caminho_arquivo, "r", encoding="utf-8") as input_file:
                next(input_file)  # Pular cabeçalho
                
                for line in input_file:
                    parts = line.strip().split(",", 5)
                    if len(parts) < 6:
                        continue
                        
                    curso = Curso(parts[1], parts[2])
                    
                    # Verificar se o curso já existe na lista
                    if curso not in cursos:
                        cursos.append(curso)
                    else:
                        curso = cursos[cursos.index(curso)]
                    
                    pessoa = Pessoa(
                        parts[0],
                        curso.get_nome_curso(),
                        curso.get_campus(),
                        parts[3],
                        float(parts[4]),
                        int(parts[5])
                    )
                    
                    curso.add_candidato(pessoa)
                    pessoas.append(pessoa)
                    
        except Exception as e:
            print(f"Erro ao ler arquivo: {e}")
            return []
            
        return cursos