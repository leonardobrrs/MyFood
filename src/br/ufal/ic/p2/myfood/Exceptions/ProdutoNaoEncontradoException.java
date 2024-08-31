package br.ufal.ic.p2.myfood.Exceptions;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException() {
        super("Produto nao encontrado");
    }
}
