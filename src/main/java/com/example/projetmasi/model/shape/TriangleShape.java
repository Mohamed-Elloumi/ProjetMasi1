package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;


public class TriangleShape implements ShapeDrawable {
    private double startX, startY, endX, endY;


    @Override
    public void draw(GraphicsContext gc) {
        double baseMidX = (startX + endX) / 2;

        double[] xPoints = { baseMidX, startX, endX };
        double[] yPoints = { startY, endY, endY };


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



    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
}
