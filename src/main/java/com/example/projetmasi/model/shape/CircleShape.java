package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements ShapeDrawable {
    private double x, y, radius = 40;
    private Color color = Color.BLACK; // ✅ couleur par défaut

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color); // ✅ appliquer la couleur
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(double width, double height) {
        double dx = Math.abs(width);
        double dy = Math.abs(height);
        this.radius = Math.min(dx, dy) / 2;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
