from PyQt6.QtWidgets import QWidget
from PyQt6.QtGui import QPainter, QColor, QFont
from PyQt6.QtCore import Qt
from typing import List

class GraficoPizza(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.valores: List[float] = []
        self.categorias: List[str] = []
        self.cores = [
            QColor(31, 119, 180),    # Azul
            QColor(255, 127, 14),    # Laranja
            QColor(44, 160, 44),     # Verde
            QColor(214, 39, 40),     # Vermelho
            QColor(148, 103, 189),   # Roxo
            QColor(140, 86, 75),     # Marrom
            QColor(227, 119, 194),   # Rosa
            QColor(127, 127, 127),   # Cinza
            QColor(188, 189, 34),    # Oliva
            QColor(23, 190, 207),    # Ciano
            QColor(174, 199, 232),   # Azul Claro
            QColor(255, 187, 120)    # Laranja Claro
        ]
        self.setMinimumSize(400, 300)
        
    def atualizar_dados(self, categorias: List[str], valores: List[float]):
        self.categorias = categorias.copy() if categorias else []
        self.valores = valores.copy() if valores else []
        self.update()
        
    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.RenderHint.Antialiasing)
        
        if not self.valores or not any(val > 0 for val in self.valores):
            painter.setFont(QFont("Arial", 10))
            painter.setPen(QColor(128, 128, 128))
            painter.drawText(20, 30, "Sem dados para exibir o gráfico.")
            return
            
        area_grafico_largura = int(self.width() * 0.60)
        area_legenda_x = area_grafico_largura + 10
        diametro = min(area_grafico_largura, self.height()) - 40
        x = (area_grafico_largura - diametro) // 2
        y = (self.height() - diametro) // 2
        
        total = 100.0
        angulo_inicial = 90.0

        for i, valor in enumerate(self.valores):
            if valor <= 0:
                continue
                
            angulo_arco = (valor / total) * 360.0
            painter.setBrush(self.cores[i % len(self.cores)])
            painter.setPen(QColor(0, 0, 0))
            painter.drawPie(x, y, diametro, diametro, int(angulo_inicial * 16), -int(angulo_arco * 16))
            angulo_inicial -= angulo_arco

        painter.setFont(QFont("Arial", 9))
        legenda_y = y
        
        for i, categoria in enumerate(self.categorias):
            if i < len(self.valores) and self.valores[i] > 0:
                painter.setBrush(self.cores[i % len(self.cores)])
                painter.drawRect(area_legenda_x, legenda_y, 12, 12)
                painter.setPen(QColor(0, 0, 0))
                texto_legenda = f"{categoria}: {self.valores[i]:.2f}%"
                painter.drawText(area_legenda_x + 20, legenda_y + 11, texto_legenda)
                legenda_y += 20