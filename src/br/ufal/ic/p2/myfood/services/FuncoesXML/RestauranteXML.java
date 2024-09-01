package br.ufal.ic.p2.myfood.services.FuncoesXML;

import br.ufal.ic.p2.myfood.Pedido;
import br.ufal.ic.p2.myfood.Restaurante;
import br.ufal.ic.p2.myfood.services.DataPersistence;

import java.util.List;
import java.util.Map;

public class RestauranteXML {
    private static final String FILE_NAME = "empresas.xml";
    private static final DataPersistence DataPersistence = new DataPersistence(FILE_NAME);

    public static void save(Map<Integer, Restaurante> empresas) {
        DataPersistence.save(empresas);
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Restaurante> load() {
        return (Map<Integer, Restaurante>) DataPersistence.load();
    }
}
