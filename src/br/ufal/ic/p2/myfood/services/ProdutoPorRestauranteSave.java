package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Produto;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoPorRestauranteSave {

    private static final String FILE_PATH = "produtoPorRestaurante.dat";

    public static void salvarProdutoPorRestaurante(Map<Integer, List<Produto>> produtoPorRestaurante) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(produtoPorRestaurante);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Produto>> carregarProdutoPorRestaurante() throws IOException,
            ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, List<Produto>>) ois.readObject();
        }
    }
}