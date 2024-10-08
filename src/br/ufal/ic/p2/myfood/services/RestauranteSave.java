package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Empresa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RestauranteSave {

    private static final String FILE_PATH = "restaurantes.dat";

    public static void salvarRestaurantes(Map<Integer, Empresa> restaurantes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(restaurantes);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Empresa> carregarRestaurantes() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Empresa>) ois.readObject();
        }
    }
}