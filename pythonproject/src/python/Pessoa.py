class Pessoa:
    def __init__(self, nome: str, curso: str, campus: str, tipoVaga: str, nota: float, posicao: int):
        self.__nome = nome
        self.__curso = curso
        self.__campus = campus
        self.__tipoVaga = tipoVaga
        self.__nota = nota
        self.__posicao = posicao
    
    def getNome(self) -> str:
        return self.__nome
    
    def getCurso(self) -> str:
        return self.__curso
    
    def getCampus(self) -> str:
        return self.__campus
    
    def getNota(self) -> float:
        return self.__nota
    
    def getTipoVaga(self) -> str:
        return self.__tipoVaga
    
    def getPosicao(self) -> int:
        return self.__posicao
    
    def __str__(self) -> str:
        return f"{self.__nome} passou em {self.__curso}"
