package br.ufal.ic.p2.myfood.Exceptions;

public class DonoNaoPodePedidoException extends Exception{
    public DonoNaoPodePedidoException(){
        super("Dono de empresa nao pode fazer um pedido");
    }
}
