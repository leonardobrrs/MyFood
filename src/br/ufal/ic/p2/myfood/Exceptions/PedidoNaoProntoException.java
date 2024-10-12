package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoNaoProntoException extends Exception{
    public PedidoNaoProntoException(){
        super("Pedido nao esta pronto para entrega");
    }
}
