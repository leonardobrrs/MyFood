package br.ufal.ic.p2.myfood.Exceptions;

public class NomeEmpresaNaoExisteException extends Exception{
    public NomeEmpresaNaoExisteException(){
        super("Nao existe empresa com esse nome");
    }
}
