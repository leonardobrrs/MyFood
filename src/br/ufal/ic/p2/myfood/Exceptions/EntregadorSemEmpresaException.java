package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorSemEmpresaException extends Exception{
    public EntregadorSemEmpresaException(){
        super("Entregador nao esta em nenhuma empresa.");
    }
}
