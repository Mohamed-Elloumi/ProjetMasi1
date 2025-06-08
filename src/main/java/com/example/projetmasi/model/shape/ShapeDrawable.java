package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
public interface ShapeDrawable {
    void draw(GraphicsContext gc);
    void setPosition(double x, double y);
    void setSize(double width, double height);
}
