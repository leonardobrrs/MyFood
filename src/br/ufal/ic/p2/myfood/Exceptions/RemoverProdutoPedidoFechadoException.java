package br.ufal.ic.p2.myfood.Exceptions;

public class RemoverProdutoPedidoFechadoException extends Exception{
    public RemoverProdutoPedidoFechadoException(){
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
