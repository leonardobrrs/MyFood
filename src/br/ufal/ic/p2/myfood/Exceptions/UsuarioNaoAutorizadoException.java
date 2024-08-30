package br.ufal.ic.p2.myfood.Exceptions;

public class UsuarioNaoAutorizadoException extends Exception{
    public UsuarioNaoAutorizadoException(String message){
        super(message);
    }
}
