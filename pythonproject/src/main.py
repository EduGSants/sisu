from python import Leitura

def main():
    cursos = Leitura.addvariaveis("entrada")
    for s in cursos:
        print(f"Curso: {s}, Campus: {s.getCampus()}")
# Apenas para testes
    for s in cursos:
        for p in s.candidatos:
            print(f"{p.getNome()}")

if __name__ == "__main__":
    main()
