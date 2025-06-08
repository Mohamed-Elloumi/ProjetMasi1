package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape implements ShapeDrawable {
    private double x, y, width = 100, height = 60;

    public void draw(GraphicsContext gc) {
        gc.strokeRect(x, y, width, height);
    }

    public void setPosition(double x, double y) { this.x = x; this.y = y; }

    public void setSize(double width, double height) {
        if (width < 0) {
            x += width;  // déplacement du point de départ vers la gauche
            width = -width;
        }
        if (height < 0) {
            y += height;  // déplacement du point de départ vers le haut
            height = -height;
        }
        this.width = width;
        this.height = height;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}


