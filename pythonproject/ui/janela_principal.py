from PyQt6.QtWidgets import (QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QComboBox, 
                             QPushButton, QLabel, QTextEdit, QGroupBox, QScrollArea, QFrame,
                             QMessageBox)
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QFont
from typing import List

from model.Curso import Curso
from service.leitura import Leitura
from service.estatisticas import Estatisticas
from ui.grafico_linha import GraficoLinha
from ui.grafico_pizza import GraficoPizza

class JanelaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.lista_cursos: List[Curso] = []
        self.init_ui()
        self.carregar_dados()
        self.atualizar_estatisticas_gerais()
        
    def init_ui(self):
        self.setWindowTitle("Análise de Dados SISU")
        self.setGeometry(100, 100, 1200, 800)

        central_widget = QWidget()
        self.setCentralWidget(central_widget)

        main_layout = QVBoxLayout(central_widget)
        main_layout.setSpacing(10)
        main_layout.setContentsMargins(10, 10, 10, 10)

        selecao_group = QGroupBox("Seleção de Curso")
        selecao_layout = QHBoxLayout(selecao_group)
        
        self.combo_cursos = QComboBox()
        self.combo_cursos.setMinimumWidth(500)
        selecao_layout.addWidget(self.combo_cursos)
        
        self.btn_analisar = QPushButton("Analisar Curso Selecionado")
        self.btn_analisar.clicked.connect(self.analisar_curso)
        selecao_layout.addWidget(self.btn_analisar)
        
        main_layout.addWidget(selecao_group)

        graficos_group = QGroupBox("Visualização Gráfica")
        graficos_layout = QHBoxLayout(graficos_group)
        
        self.grafico_pizza = GraficoPizza()
        graficos_layout.addWidget(self.grafico_pizza)
        
        self.grafico_linha = GraficoLinha()
        graficos_layout.addWidget(self.grafico_linha)
        
        main_layout.addWidget(graficos_group, 1)

        estatisticas_group = QGroupBox("Estatísticas")
        estatisticas_layout = QHBoxLayout(estatisticas_group)

        analise_curso_frame = QFrame()
        analise_curso_frame.setFrameStyle(QFrame.Shape.Box)
        analise_curso_layout = QVBoxLayout(analise_curso_frame)
        
        analise_curso_layout.addWidget(QLabel("Análise do Curso Selecionado"))
        
        medias_layout = QHBoxLayout()
        medias_layout.addWidget(QLabel("Média Geral (Todos):"))
        self.lbl_media_geral = QLabel("N/A")
        medias_layout.addWidget(self.lbl_media_geral)
        medias_layout.addWidget(QLabel("Média do Curso:"))
        self.lbl_media_curso = QLabel("N/A")
        medias_layout.addWidget(self.lbl_media_curso)
        analise_curso_layout.addLayout(medias_layout)
        
        self.txt_notas_corte = QTextEdit()
        self.txt_notas_corte.setReadOnly(True)
        self.txt_notas_corte.setFont(QFont("Monospace", 9))
        analise_curso_layout.addWidget(QLabel("Notas de Corte por Cota:"))
        analise_curso_layout.addWidget(self.txt_notas_corte)
        
        estatisticas_layout.addWidget(analise_curso_frame, 1)

        estatisticas_gerais_frame = QFrame()
        estatisticas_gerais_frame.setFrameStyle(QFrame.Shape.Box)
        estatisticas_gerais_layout = QVBoxLayout(estatisticas_gerais_frame)
        
        estatisticas_gerais_layout.addWidget(QLabel("Estatísticas Gerais (Todos os Cursos)"))
        
        self.txt_campus = QTextEdit()
        self.txt_campus.setReadOnly(True)
        self.txt_campus.setFont(QFont("Monospace", 9))
        estatisticas_gerais_layout.addWidget(QLabel("Candidatos por Campus:"))
        estatisticas_gerais_layout.addWidget(self.txt_campus)
        
        self.txt_turnos = QTextEdit()
        self.txt_turnos.setReadOnly(True)
        self.txt_turnos.setFont(QFont("Monospace", 9))
        estatisticas_gerais_layout.addWidget(QLabel("Candidatos por Turno:"))
        estatisticas_gerais_layout.addWidget(self.txt_turnos)
        
        self.txt_grau = QTextEdit()
        self.txt_grau.setReadOnly(True)
        self.txt_grau.setFont(QFont("Monospace", 9))
        estatisticas_gerais_layout.addWidget(QLabel("Candidatos por Grau (BAC/LIC):"))
        estatisticas_gerais_layout.addWidget(self.txt_grau)
        
        estatisticas_layout.addWidget(estatisticas_gerais_frame, 1)
        
        main_layout.addWidget(estatisticas_group, 1)
        
    def carregar_dados(self):
        caminho_arquivo = "docs/dados.csv"
        self.lista_cursos = Leitura.inserir_pessoas(caminho_arquivo)
        
        if not self.lista_cursos:
            QMessageBox.critical(self, "Erro de Carregamento", 
                                f"Não foi possível carregar os dados do arquivo.\nVerifique o caminho: {caminho_arquivo}")
            return

        self.lista_cursos.sort(key=lambda c: str(c))
        for curso in self.lista_cursos:
            self.combo_cursos.addItem(str(curso), curso)
            
        Estatisticas.set_cursos(self.lista_cursos)
        
    def analisar_curso(self):
        index = self.combo_cursos.currentIndex()
        if index < 0:
            return
            
        curso_selecionado = self.combo_cursos.itemData(index)

        media_curso = Estatisticas.get_media_por_curso(curso_selecionado)
        self.lbl_media_curso.setText(f"{media_curso:.2f}")

        todas_cotas = ["AC", "LB_PPI", "LB_Q", "LB_PCD", "LB_EP", "LI_PPI", "LI_Q", "LI_PCD", "LI_EP", "V"]
        notas_corte = Estatisticas.get_notas_de_corte_por_cota(curso_selecionado)
        self.txt_notas_corte.setText(self.formatar_notas_corte(todas_cotas, notas_corte))

        porc_cotas = Estatisticas.get_distribuicao_vagas(curso_selecionado, todas_cotas)
        self.grafico_pizza.atualizar_dados(todas_cotas, porc_cotas)

        notas_ordenadas = Estatisticas.get_notas_ordenadas(curso_selecionado)
        self.grafico_linha.set_notas(notas_ordenadas)
        
    def atualizar_estatisticas_gerais(self):
        media_geral = Estatisticas.get_media_geral()
        self.lbl_media_geral.setText(f"{media_geral:.2f}")

        categorias_campus = ["SAO CRISTOVAO", "ARACAJU", "ITABAIANA", "CAMPUS DO SERTAO", "LARANJEIRAS"]
        porc_campus = Estatisticas.campi()
        self.txt_campus.setText(self.formatar_resultados_gerais(categorias_campus, porc_campus))

        categorias_turno = ["MATUTINO", "VESPERTINO", "NOTURNO", "INTEGRAL"]
        porc_turno = Estatisticas.turnos()
        self.txt_turnos.setText(self.formatar_resultados_gerais(categorias_turno, porc_turno))

        categorias_grau = ["BAC", "LIC"]
        porc_grau = Estatisticas.grau()
        self.txt_grau.setText(self.formatar_resultados_gerais(categorias_grau, porc_grau))
        
    def formatar_notas_corte(self, cotas: List[str], notas: dict) -> str:
        if not notas:
            return "Dados indisponíveis."
            
        texto = ""
        for cota in cotas:
            nota_corte = notas.get(cota, 0.0)
            texto += f"{cota:10}: {nota_corte:.2f}\n"
            
        return texto
        
    def formatar_resultados_gerais(self, categorias: List[str], valores: List[float]) -> str:
        if not valores:
            return "Dados indisponíveis."
            
        texto = ""
        for i, categoria in enumerate(categorias):
            if i < len(valores):
                texto += f"{categoria:18}: {valores[i]:.2f}%\n"
                
        return texto