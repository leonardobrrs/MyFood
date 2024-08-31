package br.ufal.ic.p2.myfood.Exceptions;

public class ProdutoNaoPertenceEmpresaException extends Exception{
    public ProdutoNaoPertenceEmpresaException(){
        super("O produto nao pertence a essa empresa");
    }
}
