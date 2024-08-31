package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class Restaurante {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String endereco;
    private String tipoCozinha;

    public Restaurante(String nome, String endereco, String tipoCozinha) {
        this.id = contadorId++;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() { return endereco; }

    public String getTipoCozinha() { return tipoCozinha; }


    public String getAtributo(String atributo) throws AtributoInvalidoException {
        switch (atributo) {
            case "nome":
                return getNome();
            case "endereco":
                return getEndereco();
            case "tipoCozinha":
                return getTipoCozinha();
            default:
                throw new AtributoInvalidoException();
        }
    }

}
