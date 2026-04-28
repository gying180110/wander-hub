package com.gying.wander.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitInfoResolver {
    private GitInfoResolver() {
    }

    public static String currentCommit() {
        try {
            String env = System.getenv("GIT_COMMIT");
            if (env != null && !env.trim().isEmpty()) {
                return env.trim();
            }
            Process process = new ProcessBuilder("git", "rev-parse", "--short", "HEAD").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
        } catch (Exception ignored) {
        }
        return "unknown";
    }
}
