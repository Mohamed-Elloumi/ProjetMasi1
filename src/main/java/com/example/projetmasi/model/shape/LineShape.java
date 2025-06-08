package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineShape implements ShapeDrawable {
    private double startX, startY, endX, endY;
    private Color color = Color.BLACK; // ✅ couleur par défaut

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color); // ✅ appliquer la couleur
        gc.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public void setPosition(double x, double y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void setSize(double dx, double dy) {
        this.endX = startX + dx;
        this.endY = startY + dy;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
}
