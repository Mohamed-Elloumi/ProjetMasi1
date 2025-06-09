package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;


public class LineShape implements ShapeDrawable {
    private double startX, startY, endX, endY;


    @Override
    public void draw(GraphicsContext gc) {

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


    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
}
