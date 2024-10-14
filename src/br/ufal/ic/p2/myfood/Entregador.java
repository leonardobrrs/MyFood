package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.AtributoInvalidoException;

public class Entregador extends Usuario{
    private String veiculo;
    private String placa;

    public Entregador(String nome, String email, String senha, String endereco, String veiculo, String placa) {
        super(nome, email, senha, endereco);
        this.veiculo = veiculo;
        this.placa = placa;
    }

    // Getters e Setters
    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getAtributo(String atributo) throws AtributoInvalidoException {
        switch (atributo.toLowerCase()) {
            case "placa":
                return getPlaca();
            case "veiculo":
                return getVeiculo();
            default:
                return super.getAtributo(atributo);
        }
    }

    public boolean podeCriarEmpresa() {
    return false; // Entregador não pode criar empresas
}
    public boolean ehEntregador(){ return true;}
}
