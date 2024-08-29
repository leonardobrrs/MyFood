package scr.br.ufal.poo;

public abstract class Usuario {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;

    public Usuario(String nome, String email, String senha, String endereco) {
        this.id = contadorId++;
        this.nome = validarNome(nome);
        this.email = validarEmail(email);
        this.senha = validarSenha(senha);
        this.endereco = validarEndereco(endereco);
    }

    // Validações gerais
    protected String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        return nome;
    }

    protected String validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalido");
        }
        return email;
    }

    protected String validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha invalido");
        }
        return senha;
    }

    protected String validarEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco invalido");
        }
        return endereco;
    }

    // Getters
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

    public String getEndereco() {
        return endereco;
    }

    // Método polimórfico para obter atributo
    public String getAtributo(String atributo) {
        switch (atributo.toLowerCase()) {
            case "nome":
                return getNome();
            case "email":
                return getEmail();
            case "endereco":
                return getEndereco();
            default:
                throw new IllegalArgumentException("Atributo nao encontrado.");
        }
    }
}
