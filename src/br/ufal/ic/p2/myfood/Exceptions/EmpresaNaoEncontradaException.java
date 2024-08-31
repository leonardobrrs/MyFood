package br.ufal.ic.p2.myfood.Exceptions;

public class EmpresaNaoEncontradaException extends Exception{
    public EmpresaNaoEncontradaException(){
        super("Empresa nao encontrada");
    }
}
