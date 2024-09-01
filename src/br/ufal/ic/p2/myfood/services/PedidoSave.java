package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Pedido;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PedidoSave {

    private static final String FILE_PATH = "pedido.dat";

    public static void salvarPedidos(Map<Integer, Pedido> pedidos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(pedidos);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Pedido> carregarPedidos() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Pedido>) ois.readObject();
        }
    }
}