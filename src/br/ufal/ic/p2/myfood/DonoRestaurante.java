package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class DonoRestaurante extends Usuario{
    private String cpf;

    public DonoRestaurante(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf = cpf;
    }

    public String getCpf() { return cpf; }

    @Override
    public String getAtributo(String atributo) throws AtributoInvalidoException {
        if ("cpf".equalsIgnoreCase(atributo)) {
            return getCpf();
        }
        return super.getAtributo(atributo);
    }

    @Override
    public boolean podeCriarEmpresa() {
        return true; // DonoRestaurante pode criar empresas
    }
    public boolean ehEntregador(){ return false;}
}
