package com.example.projetmasi.controller;

import com.example.projetmasi.logger.ConsoleLogger;
import com.example.projetmasi.logger.FileLogger;
import com.example.projetmasi.logger.LoggerContext;
import com.example.projetmasi.logger.MySQLLogger;
import com.example.projetmasi.model.shape.*;
import com.example.projetmasi.view.DrawingCanvas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.sql.SQLException;

public class MainController {
    private final DrawingCanvas canvas;
    private final LoggerContext logger;

    public MainController(BorderPane root) {
        this.canvas = new DrawingCanvas();
        this.logger = new LoggerContext(new ConsoleLogger()); // fallback initial
        root.setCenter(canvas);

        // 🎨 Color picker
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> canvas.setCurrentColor(colorPicker.getValue()));

        // 🧩 Logger selector ComboBox
        ComboBox<String> loggerSelector = new ComboBox<>();
        loggerSelector.getItems().addAll("Console", "Fichier", "MySQL");
        loggerSelector.setValue("Console");

        loggerSelector.setOnAction(e -> {
            String selected = loggerSelector.getValue();
            switch (selected) {
                case "Fichier" -> {
                    logger.setStrategy(new FileLogger("logs.txt"));
                    logger.log("Logger changé : Fichier (logs.txt)");
                }
                case "MySQL" -> {
                    try {
                        MySQLLogger dbLogger = new MySQLLogger("jdbc:mysql://localhost:3306/masi", "root", "");
                        logger.setStrategy(dbLogger);
                        logger.log("Logger changé : MySQL (base masi)");
                    } catch (SQLException ex) {
                        logger.setStrategy(new ConsoleLogger());
                        logger.log("⚠️ Connexion MySQL échouée, fallback Console");
                    }
                }
                default -> {
                    logger.setStrategy(new ConsoleLogger());
                    logger.log("Logger changé : Console");
                }
            }
        });

        // 🧰 Barre de palette
        HBox palette = new HBox(15);
        palette.setPadding(new Insets(10));
        palette.setAlignment(Pos.CENTER);
        palette.setStyle("-fx-background-color: yellow;");

        Button btnRectangle = new Button("Rectangle");
        Button btnCircle = new Button("Cercle");
        Button btnLine = new Button("Ligne");
        Button btnTriangle = new Button("Triangle");
        Button btnClear = new Button("Effacer tout");
        Button btnExport = new Button("Exporter PNG");
        Button btnUndo = new Button("Undo");
        Button btnRedo = new Button("Redo");

        // Actions
        btnRectangle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.RECTANGLE);
            logger.log("Mode sélectionné : RECTANGLE");
        });

        btnCircle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.CIRCLE);
            logger.log("Mode sélectionné : CERCLE");
        });

        btnLine.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.LINE);
            logger.log("Mode sélectionné : LIGNE");
        });

        btnTriangle.setOnAction(e -> {
            canvas.setCurrentShape(ShapeFactory.ShapeType.TRIANGLE);
            logger.log("Mode sélectionné : TRIANGLE");
        });

        btnClear.setOnAction(e -> {
            canvas.clear();
            logger.log("Canvas effacé.");
        });

        btnExport.setOnAction(e -> {
            File file = new File("dessin_exporté.png");
            canvas.exportToImage(file);
            logger.log("Canvas exporté en PNG : " + file.getAbsolutePath());
        });

        btnUndo.setOnAction(e -> canvas.undo());
        btnRedo.setOnAction(e -> canvas.redo());

        // Ajout à la palette
        palette.getChildren().addAll(
                btnRectangle, btnCircle, btnLine, btnTriangle,
                btnClear, btnExport, btnUndo, btnRedo,
                colorPicker, loggerSelector
        );

        root.setBottom(palette);

        // 🔁 Logging forme créée
        canvas.setOnShapeCreated(shape -> {
            if (shape instanceof RectangleShape r) {
                logger.log("Shape drawn: RECTANGLE at (" + r.getX() + ", " + r.getY() + ")");
            } else if (shape instanceof CircleShape c) {
                logger.log("Shape drawn: CIRCLE at (" + c.getX() + ", " + c.getY() + ")");
            } else if (shape instanceof LineShape l) {
                logger.log("Shape drawn: LINE from (" + l.getStartX() + ", " + l.getStartY() + ")");
            } else if (shape instanceof TriangleShape t) {
                logger.log("Shape drawn: TRIANGLE from (" + t.getStartX() + ", " + t.getStartY() + ")");
            }
        });
    }
}
