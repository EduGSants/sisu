# Trabalho Final

1. Integrantes:
  -  Edu Gabriel Andrade Santos;
  -  Paulo Guilherme Barbosa Ribeiro;

2. Link para o repositório: https://github.com/EduGSants/sisu/

3. Descrição do tema do trabalho:

4. O que conseguimos desenvolver:

5. OO na segunda linguagem adotada (Python):
   Para a segunda linguagem do projeto adotamos o Python, onde tem suporte de Orientação à Objetos, que utilizamos bastante para promover o reuso de métodos, melhorar a organização, segregando em classes e objetos. Podemos perceber diversos pilares da OO no código, como por exemplo:
   -  Abstração: Houve-se a representação de classes abstratas como a EstatisticaBase.py que define uma interface em comum para as demais estatísticas específicas, também tivemos as classes Curso.py e Pessoa.py que representam elementos da realidade abstraídas no código;
   -  Encapsulamento: Utilizamos atributos privados como por exemplo na classe Curso.py onde usamos bastante "__" o que indicava um atributo privado (Exemplo: self.__nome_curso = resultado[0]), e juntamente a isso métodos getters que retornavam seus respectivos valores, assim promovendo o acesso controlado;
   -  Herança: Este pilar foi um dos mais usados no trabalho, como citado anteriormente, a classe abstrata EstatisticaBase.py definiu uma interface em comum para as outras classes no padrão Estatistica. Pode-se perceber que cada uma dessas demais classes, a exemplo da classe EstatisticaCampi(EstatisticaBase), herda de EstatisticaBase;
   -  Polimorfismo: Um outro pilar importante que foi essencial no trabalho, reutilizamos muitos códigos que poderiam ser repetitivos, promovendo uma grande organização e melhora de código. Pode-se perceber o uso deste pilar por exemplo a presença do método calcular() que é polimórfico nas subclasses de EstatisticaBase, ou seja, o mesmo método possui implementações em classes distintas.
   
    Por fim, a OO é de suma importância para diversos fatores como introduzirmos à projeto de maior escala, cooperatividade, leitura e entre outros.
