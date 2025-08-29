from typing import List
from model.Pessoa import Pessoa

class Curso:
    def __init__(self, crs: str, campus: str):
        resultado = self.definir_curso(crs)
        self.__nome_curso = resultado[0]
        self.__grau = resultado[1]
        self.__turno = resultado[2]
        self.__campus = campus
        self.candidatos: List[Pessoa] = []

    def add_candidato(self, p: Pessoa):
        self.candidatos.append(p)

    def get_candidatos(self) -> List[Pessoa]:
        return self.candidatos.copy()

    def get_nome_curso(self) -> str:
        return self.__nome_curso
    
    def get_grau(self) -> str:
        return self.__grau
    
    def get_turno(self) -> str:
        return self.__turno
    
    def get_campus(self) -> str:
        return self.__campus
    
    def definir_curso(self, crs: str):
        resultado = ["", "", ""]
        partes = crs.split(" - ", 1)
        resultado[0] = partes[0].strip()

        if len(partes) > 1:
            resto = partes[1]
            grau_turno = resto.split("(")

            resultado[1] = grau_turno[0].strip()

            if len(grau_turno) > 1:
                resultado[2] = grau_turno[1].replace(")", "").strip()
            else:
                resultado[2] = ""
        else:
            resultado[1] = ""
            resultado[2] = ""

        return resultado
    
    def __eq__(self, other) -> bool:
        if not isinstance(other, Curso):
            return False
        return (
            self.__nome_curso == other.__nome_curso and
            self.__campus == other.__campus and
            self.__grau == other.__grau and
            self.__turno == other.__turno
        )

    def __hash__(self):
        return hash((self.__nome_curso, self.__campus, self.__grau, self.__turno))

    def __str__(self) -> str:
        return f"{self.__nome_curso} ({self.__grau}) - {self.__campus} [{self.__turno}]"