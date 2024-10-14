package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Entrega;
import br.ufal.ic.p2.myfood.Usuario;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class EntregaSave {

    private static final String FILE_PATH = "entregas.dat";

    public static void salvarEntregas(Map<Integer, Entrega> entregas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(entregas);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Entrega> carregarEntregas() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Entrega>) ois.readObject();
        }
    }
}
