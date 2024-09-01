package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Usuario;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class UsuarioSave {

    private static final String FILE_PATH = "usuarios.dat";

    public static void salvarUsuarios(Map<Integer, Usuario> usuarios) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(usuarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Usuario> carregarUsuarios() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Usuario>) ois.readObject();
        }
    }
}
