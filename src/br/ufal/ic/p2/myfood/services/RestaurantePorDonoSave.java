package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Empresa;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantePorDonoSave {

    private static final String FILE_PATH = "restaurantesPorDono.dat";

    public static void salvarRestaurantesPorDono(Map<Integer, List<Empresa>> restaurantesPorDono) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(restaurantesPorDono);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Empresa>> carregarRestaurantesPorDono() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, List<Empresa>>) ois.readObject();
        }
    }
}