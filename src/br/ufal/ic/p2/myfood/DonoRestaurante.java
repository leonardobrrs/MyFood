package br.ufal.ic.p2.myfood;

public class DonoRestaurante extends Usuario{
    private String cpf;

    public DonoRestaurante(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha, endereco);
        this.cpf = cpf;
    }

    public String getCpf() { return cpf; }

    @Override
    public String getAtributo(String atributo) {
        if ("cpf".equalsIgnoreCase(atributo)) {
            return getCpf();
        }
        return super.getAtributo(atributo);  // Chamando o método da classe pai
    }
}
