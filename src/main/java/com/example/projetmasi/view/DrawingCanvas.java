package com.example.projetmasi.view;

import com.example.projetmasi.command.Command;
import com.example.projetmasi.command.DrawShapeCommand;
import com.example.projetmasi.model.shape.ColoredShape;
import com.example.projetmasi.model.shape.ShapeDrawable;
import com.example.projetmasi.model.shape.ShapeFactory;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class DrawingCanvas extends StackPane {
    private final Canvas canvas;
    private ShapeFactory.ShapeType currentShapeType = ShapeFactory.ShapeType.RECTANGLE;
    private Consumer<ShapeDrawable> onShapeCreated;
    private double startX, startY;
    private Color currentColor = Color.BLACK;

    private final List<ShapeDrawable> shapes = new ArrayList<>();
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public DrawingCanvas() {
        canvas = new Canvas(1000, 600);
        this.getChildren().add(canvas);
        this.setStyle("-fx-background-color: lightyellow;");

        canvas.setOnMousePressed(event -> {
            startX = event.getX();
            startY = event.getY();
        });

        canvas.setOnMouseReleased(event -> {
            double endX = event.getX();
            double endY = event.getY();

            ShapeDrawable shape = ShapeFactory.createShape(currentShapeType.name());
            if (shape == null) return;

            shape.setPosition(startX, startY);
            shape.setSize(endX - startX, endY - startY);

            // ✅ Appliquer le décorateur de couleur
            ShapeDrawable coloredShape = new ColoredShape(shape, currentColor);

            executeCommand(new DrawShapeCommand(coloredShape, this));

            if (onShapeCreated != null) onShapeCreated.accept(coloredShape);
        });
    }

    public void drawShape(ShapeDrawable shape) {
        shapes.add(shape);
        redraw();
    }

    public void removeShape(ShapeDrawable shape) {
        shapes.remove(shape);
        redraw();
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (ShapeDrawable s : shapes) {
            s.draw(gc);
        }
    }

    public void clear() {
        shapes.clear();
        redraw();
        undoStack.clear();
        redoStack.clear();
    }

    public void exportToImage(File file) {
        WritableImage image = canvas.snapshot(null, null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            System.err.println("Erreur lors de l’export PNG : " + e.getMessage());
        }
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
        }
    }

    public void setCurrentShape(ShapeFactory.ShapeType type) {
        this.currentShapeType = type;
    }

    public void setOnShapeCreated(Consumer<ShapeDrawable> callback) {
        this.onShapeCreated = callback;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
}
