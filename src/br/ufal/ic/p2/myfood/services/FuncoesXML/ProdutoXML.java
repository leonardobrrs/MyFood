package br.ufal.ic.p2.myfood.services.FuncoesXML;

import br.ufal.ic.p2.myfood.services.DataPersistence;
import br.ufal.ic.p2.myfood.Produto;

import java.util.List;
import java.util.Map;

public class ProdutoXML {
    private static final String FILE_NAME = "produtos.xml";
    private static final DataPersistence DataPersistence = new DataPersistence(FILE_NAME);

    public static void save(Map<Integer, List<Produto>> produtos) {
        DataPersistence.save(produtos);
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Produto>> load() {
        return (Map<Integer, List<Produto>>) DataPersistence.load();
    }
}
