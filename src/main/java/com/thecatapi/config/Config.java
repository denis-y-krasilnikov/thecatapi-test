package com.thecatapi.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static Dotenv config;

    public static String get(String key) {
        if (config == null) {
            config = Dotenv.load();
        }
        return config.get(key);
    }
}
