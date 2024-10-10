package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

import java.io.Serializable;

public abstract class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contadorId = 1;
    private int id;
    private String tipoEmpresa;
    private String nome;
    private String endereco;

    public Empresa(String tipoEmpresa, String nome, String endereco) {
        this.id = contadorId++;
        this.tipoEmpresa = tipoEmpresa;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() { return endereco; }

    public abstract boolean isMercado();


    public abstract void setAtributo(String atributo) throws AtributoInvalidoException;

    public String getAtributo(String atributo) throws AtributoInvalidoException {
        switch (atributo) {
            case "nome":
                return getNome();
            case "endereco":
                return getEndereco();
            default:
                throw new AtributoInvalidoException();
        }
    }

}
