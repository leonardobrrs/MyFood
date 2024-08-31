package br.ufal.ic.p2.myfood.Exceptions;

public class EmailExistenteException extends Exception {
    public EmailExistenteException() {
        super("Conta com esse email ja existe");
    }
}
