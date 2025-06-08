package com.example.projetmasi.logger;

import java.sql.*;

public class MySQLLogger implements LoggerStrategy {
    private final Connection conn;

    public MySQLLogger(String url, String user, String password) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("❌ Connexion JDBC échouée : " + e.getMessage());
            e.printStackTrace(); // 🧠 Affiche la vraie raison (host introuvable, driver manquant, etc.)
            throw e; // pour propager l'erreur au contrôleur
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void log(String message) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO logs (message) VALUES (?)")) {
            stmt.setString(1, message);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur MySQL log : " + e.getMessage());
        }
    }
}
