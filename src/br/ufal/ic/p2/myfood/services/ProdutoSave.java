package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Produto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProdutoSave {

    private static final String FILE_PATH = "produtos.dat";

    public static void salvarProdutos(Map<Integer, Produto> produtos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(produtos);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Produto> carregarProdutos() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Produto>) ois.readObject();
        }
    }
}