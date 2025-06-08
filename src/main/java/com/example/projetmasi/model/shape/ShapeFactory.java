package com.example.projetmasi.model.shape;

public class ShapeFactory {
    public enum ShapeType { RECTANGLE, CIRCLE , LINE ,  TRIANGLE }

    public static ShapeDrawable createShape(String type) {
        return switch (type.toUpperCase()) {
            case "RECTANGLE" -> new RectangleShape();
            case "CIRCLE" -> new CircleShape();
            case "LINE" -> new LineShape();
            case "TRIANGLE" -> new TriangleShape();

            default -> null;
        };
    }
}

