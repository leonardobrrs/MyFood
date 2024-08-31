package br.ufal.ic.p2.myfood.Exceptions;

public class EnderecoDuplicadoException extends Exception{
    public EnderecoDuplicadoException(){
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
