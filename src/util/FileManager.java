package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {

    // Salvar lista em arquivo
    public static void salvar(String caminho, List<String> linhas) {
        try {
            Files.write(Paths.get(caminho), linhas);
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    // Ler arquivo e retornar lista de linhas
    public static List<String> carregar(String caminho) {
        try {
            Path path = Paths.get(caminho);
            if (!Files.exists(path)) {
                return new ArrayList<>();
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
