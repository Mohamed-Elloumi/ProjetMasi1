package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface ShapeDrawable {
    void draw(GraphicsContext gc);
    void setPosition(double x, double y);
    void setSize(double width, double height);
    void setColor(Color color);
    Color getColor();


}
