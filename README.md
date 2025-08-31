# Trabalho Final

1. Integrantes:
  -  Edu Gabriel Andrade Santos;
  -  Paulo Guilherme Barbosa Ribeiro;

//----------------------------------------------------------------------------------------------------------------------------

2. Link para o repositório: https://github.com/EduGSants/sisu/

//----------------------------------------------------------------------------------------------------------------------------

3. Descrição do tema do trabalho:
A Análise Exploratória de Dados é a etapa inicial de qualquer estudo de dados. Nela, o objetivo não é chegar a conclusões definitivas, mas entender os padrões, tendências, distribuições e relações que os dados apresentam.
O SISU (Sistema de Seleção Unificada) é a plataforma nacional pela qual estudantes entram em universidades públicas no Brasil usando a nota do ENEM.

o tema "análise exploratória dos dados do SISU da UFS" significa olhar para os dados da seleção, descobrir padrões e entendê-los melhor com interfaces gráficas em Java, que têm o papel de transformar esses dados em algo visual, interativo e acessível, facilitando a exploração para o usuário.

//----------------------------------------------------------------------------------------------------------------------------

4. O que conseguimos desenvolver:
O projeto, até o dado momento, lê as informações de candidatos de um arquivo .csv, calcula diversas estatísticas (como médias de notas, distribuição por campus, turno e tipo de vaga) e exibe esses dados em uma interface gráfica com textos e gráficos de pizza e linha.

A classe "Leitura" tem a função de abrir o arquivo "dados.csv", um formato de texto simples, e processa cada linha, dessa forma, cada candidato se torna um objeto do tipo Pessoa, carregando seus atributos e cada curso se torna um objeto "Curso", que não só contém suas próprias informações, mas também é ligado a uma lista de todos os candidatos que se inscreveram para ele.

Agora que os dados estão organizados em uma estrutura de objetos coesa, entra em cena a análise estatística:
	- A "EstatisticaMedias" se dedica a calcular a média das notas, tanto para cursos individuais quanto para o processo seletivo como um todo.
	- A "EstatisticaDelta" investiga a competitividade, calculando a diferença entre a maior e a menor nota de cada curso. 
	- Outras classes, como a "EstatisticaCampi" e a "EstatisticaTurnos", fazem um quadro demográfico, revelando a distribuição percentual de candidatos entre os diferentes campi e turnos.
	- A "EstatisticaDemandas" analisa a concorrência sob a ótica das políticas de ação afirmativa, mostrando a proporção de candidatos por tipo de vaga (ampla concorrência, cotas, etc). 

Tudo isso foi organizado pela classe "Estatisticas" que permite que análises complexas sejam realizadas de forma organizada e eficiente.

O sistema materializa os dados processados através de uma interface gráfica construída com a biblioteca Swing. A janela principal, definida pela classe "Inicio1", serve como um painel de controle analítico. Nela, o usuário encontra, de um lado, as estatísticas gerais do processo seletivo dispostas em caixas de texto e do outro, um menu suspenso que aborda os detalhes de um curso específico.

Ao selecionar um curso e acionar a análise, a aplicação começa. Ela exibe números, mas também os traduz em representações visuais. Um "GraficoPizza" é desenhado para mostrar, de forma intuitiva, a proporção de candidatos por tipo de vaga naquele curso, onde cada "fatia" colorida representa uma cota. Simultaneamente, um "GraficoLinha" traça a distribuição das notas dos candidatos daquele curso, permitindo uma visualização clara da performance geral dos concorrentes.

OBS: Para executar o programa, é recomendado que abra o projeto no netbeans e execute "Inicio1.java" que está na pasta ui.

//----------------------------------------------------------------------------------------------------------------------------

5. OO na segunda linguagem adotada (Python):
   Para a segunda linguagem do projeto adotamos o Python, onde tem suporte de Orientação à Objetos, que utilizamos bastante para promover o reuso de métodos, melhorar a organização, segregando em classes e objetos. Podemos perceber diversos pilares da OO no código, como por exemplo:
   -  Abstração: Houve-se a representação de classes abstratas como a EstatisticaBase.py que define uma interface em comum para as demais estatísticas específicas, também tivemos as classes Curso.py e Pessoa.py que representam elementos da realidade abstraídas no código;
   -  Encapsulamento: Utilizamos atributos privados como por exemplo na classe Curso.py onde usamos bastante "__" o que indicava um atributo privado (Exemplo: self.__nome_curso = resultado[0]), e juntamente a isso métodos getters que retornavam seus respectivos valores, assim promovendo o acesso controlado;
   -  Herança: Este pilar foi um dos mais usados no trabalho, como citado anteriormente, a classe abstrata EstatisticaBase.py definiu uma interface em comum para as outras classes no padrão Estatistica. Pode-se perceber que cada uma dessas demais classes, a exemplo da classe EstatisticaCampi(EstatisticaBase), herda de EstatisticaBase;
   -  Polimorfismo: Um outro pilar importante que foi essencial no trabalho, reutilizamos muitos códigos que poderiam ser repetitivos, promovendo uma grande organização e melhora de código. Pode-se perceber o uso deste pilar por exemplo a presença do método calcular() que é polimórfico nas subclasses de EstatisticaBase, ou seja, o mesmo método possui implementações em classes distintas.
   
    Por fim, a OO é de suma importância para diversos fatores como introduzirmos à projeto de maior escala, cooperatividade, leitura e entre outros.
