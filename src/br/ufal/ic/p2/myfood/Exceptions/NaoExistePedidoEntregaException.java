package br.ufal.ic.p2.myfood.Exceptions;

public class NaoExistePedidoEntregaException extends Exception {
    public NaoExistePedidoEntregaException() {
        super("Nao existe pedido para entrega");
    }
}
