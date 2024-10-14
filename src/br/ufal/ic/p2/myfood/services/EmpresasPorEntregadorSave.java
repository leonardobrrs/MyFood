package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.Empresa;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresasPorEntregadorSave {

    private static final String FILE_PATH = "empresaPorEntregador.dat";

    public static void salvarEmpresaPorEntregador(Map<Integer, List<Empresa>> empresaPorEntregador) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(empresaPorEntregador);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Empresa>> carregarEmpresaPorEntregador() throws IOException,
            ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<Integer, List<Empresa>>) ois.readObject();
        }
    }
}