package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Empresa;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresasPorDonoSave {

    private static final String FILE_PATH = "empresaPorDono.dat";

    public static void salvarEmpresaPorDono(Map<Integer, List<Empresa>> empresaPorDono) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(empresaPorDono);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Empresa>> carregarEmpresaPorDono() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, List<Empresa>>) ois.readObject();
        }
    }
}