package br.ufal.ic.p2.myfood.Exceptions;

public class NomeProdutoExisteException extends Exception{
    public NomeProdutoExisteException(){
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
