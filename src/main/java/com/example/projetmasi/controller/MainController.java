package com.example.projetmasi.controller;

import com.example.projetmasi.AppConfig;
import com.example.projetmasi.logger.LoggerContext;
import com.example.projetmasi.model.shape.CircleShape;
import com.example.projetmasi.model.shape.LineShape;
import com.example.projetmasi.model.shape.RectangleShape;
import com.example.projetmasi.model.shape.ShapeFactory;
import com.example.projetmasi.view.DrawingCanvas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.File;

public class MainController {
    private final DrawingCanvas canvas;
    private final LoggerContext logger;

    public MainController(BorderPane root) {
        this.canvas = new DrawingCanvas();
        this.logger = new LoggerContext(AppConfig.getLogger());

        // Zone centrale
        root.setCenter(canvas);

        // Création de la palette
        HBox palette = new HBox(20);
        palette.setPadding(new Insets(10));
        palette.setStyle("-fx-background-color: yellow;");
        palette.setPrefHeight(50);
        palette.setAlignment(Pos.CENTER); // ✅ Centrage horizontal

        // Boutons
        Button btnRectangle = new Button("Rectangle");
        Button btnCircle = new Button("Cercle");

        btnRectangle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.RECTANGLE);
            logger.log("Mode sélectionné : RECTANGLE");
        });

        btnCircle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.CIRCLE);
            logger.log("Mode sélectionné : CERCLE");
        });
        Button btnLine = new Button("Ligne");

        btnLine.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.LINE);
            logger.log("Mode sélectionné : LIGNE");
        });
        Button btnTriangle = new Button("Triangle");

        btnTriangle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.TRIANGLE);
            logger.log("Mode sélectionné : TRIANGLE");
        });
        Button btnClear = new Button("Effacer tout");

        btnClear.setOnAction(e -> {
            canvas.clear();
            logger.log("Canvas effacé.");
        });
        Button btnExport = new Button("Exporter PNG");
        btnExport.setOnAction(e -> {
            File file = new File("dessin_exporté.png");
            canvas.exportToImage(file);
            logger.log("Canvas exporté en PNG : " + file.getAbsolutePath());
        });
        Button btnUndo = new Button("Undo");
        Button btnRedo = new Button("Redo");
        btnUndo.setOnAction(e -> canvas.undo());
        btnRedo.setOnAction(e -> canvas.redo());

        // Ajout des boutons uniquement (sans spacer)
        palette.getChildren().addAll(btnRectangle, btnCircle, btnLine, btnTriangle,btnClear,btnExport,btnUndo, btnRedo);
        root.setBottom(palette);

        // Logging des formes
        canvas.setOnShapeCreated(shape -> {
            if (shape instanceof RectangleShape r) {
                logger.log("Shape drawn: RECTANGLE at (" + r.getX() + ", " + r.getY() + ")");
            } else if (shape instanceof CircleShape c) {
                logger.log("Shape drawn: CIRCLE at (" + c.getX() + ", " + c.getY() + ")");
            }
            else if (shape instanceof LineShape l) {
                logger.log("Shape drawn: LINE from (" + l.getStartX() + ", " + l.getStartY() + ")");
            }

        });
    }
}
