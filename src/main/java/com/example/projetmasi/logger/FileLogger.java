package com.example.projetmasi.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements LoggerStrategy {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
            out.println("[LOG] " + message);
        } catch (IOException e) {
            System.err.println("Erreur écriture fichier log : " + e.getMessage());
        }
    }
}
