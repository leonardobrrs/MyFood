package br.ufal.ic.p2.myfood.Exceptions;

public class PedidoJaLiberadoException extends Exception{
    public PedidoJaLiberadoException(){
        super("Pedido ja liberado");
    }
}
