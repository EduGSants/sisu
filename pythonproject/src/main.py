from python.Leitura import Leitura
from python.estatisticas import estatisticas

def main():
    cursos = Leitura.addvariaveis("/workspaces/sisu/pythonproject/src/docs/dados.csv")
# Apenas para testes
    result = estatisticas.deltaEsp(cursos,cursos[4])

    print(f"{result}")

if __name__ == "__main__":
    main()
