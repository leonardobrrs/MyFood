package br.ufal.ic.p2.myfood;

public class Usuario {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;

    public Usuario(String nome, String email, String senha, String endereco) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() { return endereco; }

    public String getAtributo(String atributo) {
        switch (atributo.toLowerCase()) {
            case "nome":
                return getNome();
            case "email":
                return getEmail();
            case "senha":
                return getSenha();
            case "endereco":
                return getEndereco();
            default:
                throw new IllegalArgumentException("Atributo nao encontrado.");
        }
    }
}
