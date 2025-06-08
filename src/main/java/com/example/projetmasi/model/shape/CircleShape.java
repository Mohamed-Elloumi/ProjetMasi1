package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;

public class CircleShape implements ShapeDrawable {
    private double x, y, radius = 40;

    @Override
    public void draw(GraphicsContext gc) {
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

    public double getX() { return x; }
    public double getY() { return y; }
}
