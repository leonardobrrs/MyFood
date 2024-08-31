package br.ufal.ic.p2.myfood.Exceptions;

public class ProdutoNaoEncontrado extends Exception {
    public ProdutoNaoEncontrado() {
        super("Produto nao encontrado");
    }
}
