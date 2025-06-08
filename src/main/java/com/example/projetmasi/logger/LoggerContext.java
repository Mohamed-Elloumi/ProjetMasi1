package com.example.projetmasi.logger;

public class LoggerContext {
    private LoggerStrategy strategy;

    public LoggerContext(LoggerStrategy strategy) {
        this.strategy = strategy;
    }

    public void log(String message) {
        strategy.log(message);
    }
}
