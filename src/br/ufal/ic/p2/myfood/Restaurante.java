package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class Restaurante extends Empresa {
    private String tipoCozinha;

    public Restaurante(String nome, String endereco, String tipoCozinha) {
        super(nome, endereco);
        this.tipoCozinha = tipoCozinha;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    @Override
    public void setAtributo(String atributo) throws AtributoInvalidoException {

    }

    public String getAtributo(String atributo) throws AtributoInvalidoException {
        if ("tipoCozinha".equalsIgnoreCase(atributo)) {
            return getTipoCozinha();
        }
        return super.getAtributo(atributo);
    }
}