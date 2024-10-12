package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorEmEntregaException extends Exception{
    public EntregadorEmEntregaException(){
        super("Entregador ainda em entrega");
    }
}
