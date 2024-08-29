package scr.br.ufal.poo;

public abstract class Empresa {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String endereco;
    private String tipoCozinha; // Usado para restaurantes, pode ser adaptado para outros tipos de empresas

    public Empresa(String nome, String endereco, String tipoCozinha) {
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

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getAtributo(String atributo) {
        switch (atributo.toLowerCase()) {
            case "nome":
                return getNome();
            case "endereco":
                return getEndereco();
            case "tipocozinha":
                return getTipoCozinha();
            default:
                throw new IllegalArgumentException("Atributo invalido");
        }
    }
}
