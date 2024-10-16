package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorSemEmpresaException extends Exception{
    public EntregadorSemEmpresaException(){
        super("Entregador nao estar em nenhuma empresa.");
    }
}
