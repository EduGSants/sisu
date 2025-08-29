class Pessoa:
    def __init__(self, nome: str, curso: str, campus: str, tipo_vaga: str, nota: float, posicao: int):
        self.__nome = nome
        self.__curso = curso
        self.__campus = campus
        self.__tipo_vaga = tipo_vaga
        self.__nota = nota
        self.__posicao = posicao
    
    def get_nome(self) -> str:
        return self.__nome
    
    def get_curso(self) -> str:
        return self.__curso
    
    def get_campus(self) -> str:
        return self.__campus
    
    def get_nota(self) -> float:
        return self.__nota
    
    def get_tipo_vaga(self) -> str:
        return self.__tipo_vaga
    
    def get_posicao(self) -> int:
        return self.__posicao
    
    def __str__(self) -> str:
        return f"{self.__nome} passou em {self.__curso}"