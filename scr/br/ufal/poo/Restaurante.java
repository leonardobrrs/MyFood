package scr.br.ufal.poo;

public class Restaurante {
    private static int contador = 0;
    private int id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private int donoId;  // Novo campo para armazenar o ID do dono

    // Construtor original (sem donoId)
    public Restaurante(String nome, String endereco, String tipoCozinha) {
        this.id = ++contador; // Incrementa o contador para garantir IDs únicos
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }

    // Construtor atualizado para incluir o donoId
    public Restaurante(String nome, String endereco, String tipoCozinha, int donoId) {
        this(nome, endereco, tipoCozinha); // Chama o construtor acima
        this.donoId = donoId; // Armazena o ID do dono
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    // Novo método para retornar o ID do dono
    public int getDonoId() {
        return donoId;
    }

    @Override
    public String toString() {
        return "Restaurante{id=" + id + ", nome='" + nome + "', endereco='" + endereco + "', tipoCozinha='" + tipoCozinha + "', donoId=" + donoId + "}";
    }
}
