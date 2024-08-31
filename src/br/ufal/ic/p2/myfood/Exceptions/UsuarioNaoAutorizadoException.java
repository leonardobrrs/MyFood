package br.ufal.ic.p2.myfood.Exceptions;

public class UsuarioNaoAutorizadoException extends Exception{
    public UsuarioNaoAutorizadoException(){
        super("Usuario nao pode criar uma empresa");
    }
}
