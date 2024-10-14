package br.ufal.ic.p2.myfood.Exceptions;

public class EntregadorNaoValidoException extends Exception {
    public EntregadorNaoValidoException() {
        super("Nao e um entregador valido");
    }
}
