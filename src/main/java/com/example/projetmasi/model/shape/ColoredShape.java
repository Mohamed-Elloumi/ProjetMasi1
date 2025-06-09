package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColoredShape extends ShapeDecorator {
    private Color color;

    public ColoredShape(ShapeDrawable shape, Color color) {
        super(shape);
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color); // applique la couleur
        super.draw(gc);      // puis dessine la forme décorée
    }

    @Override
    public void setPosition(double x, double y) {

    }

    @Override
    public void setSize(double width, double height) {

    }
}

