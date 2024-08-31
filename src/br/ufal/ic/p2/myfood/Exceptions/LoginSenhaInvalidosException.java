package br.ufal.ic.p2.myfood.Exceptions;

public class LoginSenhaInvalidosException extends Exception {
    public LoginSenhaInvalidosException(){
        super("Login ou senha invalidos");
    }
}
