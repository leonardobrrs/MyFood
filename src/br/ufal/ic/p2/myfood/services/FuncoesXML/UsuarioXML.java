package br.ufal.ic.p2.myfood.services.FuncoesXML;

import br.ufal.ic.p2.myfood.services.DataPersistence;
import br.ufal.ic.p2.myfood.Usuario;

import java.util.Map;

public class UsuarioXML {
    private static final String FILE_NAME = "usuarios.xml";
    private static final DataPersistence DataPersistence = new DataPersistence(FILE_NAME);

    public static void save(Map<String, Usuario> usuarios) {
        DataPersistence.save(usuarios);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Usuario> load() {
        return (Map<String, Usuario>) DataPersistence.load();
    }
}
