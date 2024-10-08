package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Empresa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EmpresasSave {

    private static final String FILE_PATH = "empresas.dat";

    public static void salvarEmpresas(Map<Integer, Empresa> empresas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(empresas);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Empresa> carregarEmpresas() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, Empresa>) ois.readObject();
        }
    }
}