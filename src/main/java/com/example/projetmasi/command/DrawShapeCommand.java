package com.example.projetmasi.command;

import com.example.projetmasi.model.shape.ShapeDrawable;
import com.example.projetmasi.view.DrawingCanvas;

public class DrawShapeCommand implements Command {
    private final ShapeDrawable shape;
    private final DrawingCanvas canvas;

    public DrawShapeCommand(ShapeDrawable shape, DrawingCanvas canvas) {
        this.shape = shape;
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.drawShape(shape);
    }

    @Override
    public void undo() {
        canvas.removeShape(shape);
    }
}
