package com.example.projetmasi;

import com.example.projetmasi.logger.ConsoleLogger;
import com.example.projetmasi.logger.LoggerStrategy;

public class AppConfig {
    public static LoggerStrategy getLogger() {
        return new ConsoleLogger();
    }
}
