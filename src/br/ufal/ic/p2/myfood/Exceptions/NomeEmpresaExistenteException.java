package br.ufal.ic.p2.myfood.Exceptions;

public class NomeEmpresaExistenteException extends Exception{
    public NomeEmpresaExistenteException(){
        super("Empresa com esse nome ja existe");
    }
}
