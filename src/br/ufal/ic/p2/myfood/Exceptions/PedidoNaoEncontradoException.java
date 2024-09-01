package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoNaoEncontradoException extends Exception{
    public PedidoNaoEncontradoException(){
        super("Pedido nao encontrado");
    }
}
