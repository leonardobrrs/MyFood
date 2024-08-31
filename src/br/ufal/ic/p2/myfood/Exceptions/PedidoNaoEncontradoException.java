package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoNaoEncontradoException extends Exception{
    public PedidoNaoEncontradoException(){
        super("Nao existe pedido em aberto");
    }
}
