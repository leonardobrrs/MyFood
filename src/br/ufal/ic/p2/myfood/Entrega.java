package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;
import br.ufal.ic.p2.myfood.Exceptions.AtributoNaoExisteException;
import br.ufal.ic.p2.myfood.services.PedidoSave;
import br.ufal.ic.p2.myfood.services.UsuarioSave;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Entrega implements Serializable {
    private int id; // ID único da entrega
    private int idPedido; // ID do pedido
    private int idEntregador; // ID do entregador
    private String destino; // Endereço de entrega

    // Construtor
    public Entrega(int id, int idPedido, int idEntregador, String destino) {
        this.id = id;
        this.idPedido = idPedido;
        this.idEntregador = idEntregador;
        this.destino = destino;
    }

    // Getters para acessar as informações
    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public String getDestino() {
        return destino;
    }

    // Método getAtributo modificado para buscar informações de Pedido e Entregador
    public String getAtributo(String atributo) throws AtributoInvalidoException, IOException, ClassNotFoundException, AtributoNaoExisteException {

        Map<Integer, Pedido> pedidos = PedidoSave.carregarPedidos();
        Pedido pedido = pedidos.get(idPedido);
        Map<Integer, Usuario> usuarios = UsuarioSave.carregarUsuarios();// Assuming there's a map of pedidos
        Usuario entregador = usuarios.get(idEntregador); // Assuming there's a map of usuarios

        switch (atributo.toLowerCase()) {
            case "pedido":
                return String.valueOf(getIdPedido());
            case "entregador":
                if (entregador != null) {
                    return entregador.getNome(); // Get the name of the delivery person
                }
                throw new AtributoInvalidoException();
            case "cliente":
                if (pedido != null) {
                    return pedido.getCliente(); // Get the client's name
                }
                throw new AtributoInvalidoException();
            case "empresa":
                if (pedido != null) {
                    return pedido.getEmpresa(); // Get the name of the company
                }
                throw new AtributoInvalidoException();
            case "destino":
                return getDestino();
            default:
                throw new AtributoNaoExisteException();
        }
    }
}
