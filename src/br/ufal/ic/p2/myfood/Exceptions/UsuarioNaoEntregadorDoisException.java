package br.ufal.ic.p2.myfood.Exceptions;

public class UsuarioNaoEntregadorDoisException extends Exception{
    public UsuarioNaoEntregadorDoisException(){
        super("Usuario nao e um entregador");
    }
}
