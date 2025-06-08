package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape implements ShapeDrawable {
    private double x, y, width, height;
    private Color color = Color.BLACK; // Couleur par défaut

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color); // Applique la couleur
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(double width, double height) {
        if (width < 0) {
            x += width;
            width = -width;
        }
        if (height < 0) {
            y += height;
            height = -height;
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
