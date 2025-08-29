from PyQt6.QtWidgets import QWidget
from PyQt6.QtGui import QPainter, QColor, QFont
from PyQt6.QtCore import Qt
from typing import List

class GraficoLinha(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.notas: List[float] = []
        self.PADDING = 40
        self.setMinimumSize(400, 300)
        
    def set_notas(self, notas: List[float]):
        self.notas = notas.copy() if notas else []
        if self.notas:
            self.notas.sort()
        self.update()
        
    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.RenderHint.Antialiasing)
        
        if not self.notas or len(self.notas) < 2:
            painter.setFont(QFont("Arial", 10))
            painter.setPen(QColor(128, 128, 128))
            painter.drawText(20, 30, "Selecione um curso para exibir o gráfico de notas.")
            return

        nota_minima = self.notas[0]
        nota_maxima = self.notas[-1]
        range_notas = (nota_maxima - nota_minima) if (nota_maxima - nota_minima) > 0 else 1
        
        largura_desenhavel = self.width() - 2 * self.PADDING
        altura_desenhavel = self.height() - 2 * self.PADDING

        painter.setPen(QColor(0, 0, 0))
        painter.drawLine(self.PADDING, self.height() - self.PADDING, self.PADDING, self.PADDING)  # Eixo Y
        painter.drawLine(self.PADDING, self.height() - self.PADDING, self.width() - self.PADDING, self.height() - self.PADDING)  # Eixo X

        painter.setPen(QColor(255, 0, 0))

        
        for i in range(len(self.notas) - 1):
            x1 = self.PADDING + int(i * (largura_desenhavel / (len(self.notas) - 1)))
            y1 = self.height() - self.PADDING - int(((self.notas[i] - nota_minima) / range_notas) * altura_desenhavel)
            x2 = self.PADDING + int((i + 1) * (largura_desenhavel / (len(self.notas) - 1)))
            y2 = self.height() - self.PADDING - int(((self.notas[i + 1] - nota_minima) / range_notas) * altura_desenhavel)
            
            painter.drawLine(x1, y1, x2, y2)

        painter.setPen(QColor(0, 0, 0))
        painter.setFont(QFont("Arial", 8))
        painter.drawText(self.PADDING - 35, self.height() - self.PADDING + 15, f"{nota_minima:.2f}")
        painter.drawText(self.PADDING - 35, self.PADDING, f"{nota_maxima:.2f}")