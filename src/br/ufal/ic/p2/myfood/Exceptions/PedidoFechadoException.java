package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoFechadoException extends Exception{
    public PedidoFechadoException(){
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
