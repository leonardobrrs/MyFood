package br.ufal.ic.p2.myfood.Exceptions;

import java.rmi.server.ExportException;

public class HorarioInvalidoException extends Exception {
    public HorarioInvalidoException() {super("Horario invalido");}
}
