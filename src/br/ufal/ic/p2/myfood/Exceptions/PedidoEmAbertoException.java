package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoEmAbertoException extends Exception{
    public PedidoEmAbertoException(){
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
