package scr.br.ufal.poo;

public class Cliente extends Usuario {
    public Cliente(String nome, String email, String senha, String endereco) {
        super(nome, email, senha, endereco);
    }

    // Cliente não tem atributos adicionais além dos atributos gerais de Usuario
}
