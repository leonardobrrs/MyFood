package br.ufal.ic.p2.myfood.Exceptions;

public class AtributoNaoExisteException extends Exception{
    public AtributoNaoExisteException(){
        super("Atributo nao existe");
    }
}
