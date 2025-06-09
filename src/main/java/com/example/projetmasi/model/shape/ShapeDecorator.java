package com.example.projetmasi.model.shape;

import javafx.scene.canvas.GraphicsContext;

public abstract class ShapeDecorator implements ShapeDrawable {
    protected ShapeDrawable decoratedShape;

    public ShapeDecorator(ShapeDrawable shape) {
        this.decoratedShape = shape;
    }

    @Override
    public void draw(GraphicsContext gc) {
        decoratedShape.draw(gc);
    }
}
