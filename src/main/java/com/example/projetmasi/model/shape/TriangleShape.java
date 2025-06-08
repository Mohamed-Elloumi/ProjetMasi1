package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TriangleShape implements ShapeDrawable {
    private double startX, startY, endX, endY;
    private Color color = Color.BLACK; // ✅ couleur par défaut

    @Override
    public void draw(GraphicsContext gc) {
        double baseMidX = (startX + endX) / 2;

        double[] xPoints = { baseMidX, startX, endX };
        double[] yPoints = { startY, endY, endY };

        gc.setStroke(color); // ✅ appliquer la couleur
        gc.strokePolygon(xPoints, yPoints, 3);
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
}
