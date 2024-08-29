package scr.br.ufal.poo;

public class DonoRestaurante extends Usuario {
    private String endereco;
    private String cpf;

    public DonoRestaurante(String nome, String email, String senha, String endereco, String cpf) {
        super(nome, email, senha);
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }
}
