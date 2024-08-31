package br.ufal.ic.p2.myfood.Exceptions;

public class ProdutoNaoCadastradoException extends Exception {
    public ProdutoNaoCadastradoException() {
        super("Produto nao cadastrado");
    }
}
