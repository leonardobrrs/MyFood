package br.ufal.ic.p2.myfood.Exceptions;

public class MercadoInvalidoException extends Exception{
    public MercadoInvalidoException(){
        super("Nao e um mercado valido");
    }
}
