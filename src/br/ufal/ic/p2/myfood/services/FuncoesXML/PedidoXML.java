package br.ufal.ic.p2.myfood.services.FuncoesXML;

import br.ufal.ic.p2.myfood.services.DataPersistence;
import br.ufal.ic.p2.myfood.Pedido;

import java.util.List;
import java.util.Map;

public class PedidoXML {

    private static final String FILE_NAME = "pedidos.xml";
    private static final DataPersistence DataPersistenceManager = new DataPersistence(FILE_NAME);

    public static void save(Map<Integer, List<Pedido>> pedidos) {
        DataPersistenceManager.save(pedidos);
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, List<Pedido>> load() {
        return (Map<Integer, List<Pedido>>) DataPersistenceManager.load();
    }
}
